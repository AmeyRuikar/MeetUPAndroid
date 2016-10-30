package meetup.amey.com.meetup;

/**
 * Created by ameyruikar on 10/22/16.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Base64;
import java.util.*;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class loginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity

                //facebook integration
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(
                            "com.example.vedant.facebooklogin",
                            PackageManager.GET_SIGNATURES);
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
                } catch (PackageManager.NameNotFoundException e) {

                } catch (NoSuchAlgorithmException e) {

                }



                LoginButton loginButton;
                CallbackManager callbackManager;


                loginButton = (LoginButton) findViewById(R.id.login_button);
                loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
                callbackManager = CallbackManager.Factory.create();


                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        //meetup.setVisibility(View.INVISIBLE);
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {


                                        try {
                                            String email = object.getString("email");
                                            String birthday = object.getString("birthday");
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                           // tv_profile_name.setText(name);


                                            String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";

                                            //Picasso.with(MainActivity.this).load(imageurl).into(iv_profile_pic);
                                            //iv_profile_pic.setVisibility(View.VISIBLE);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });


                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();


                        /**
                         * AccessTokenTracker to manage logout
                         */
                        AccessTokenTracker accessTokenTracker;
                        accessTokenTracker = new AccessTokenTracker() {
                            @Override
                            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                                       AccessToken currentAccessToken) {
                                if (currentAccessToken == null) {
                                    //tv_profile_name.setText("");
                                    //iv_profile_pic.setImageResource(R.drawable.maleicon);
                                }
                            }
                        };
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

                //eof FB integration
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(loginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
