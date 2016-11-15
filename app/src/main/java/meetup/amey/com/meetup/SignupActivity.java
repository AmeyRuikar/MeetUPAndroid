package meetup.amey.com.meetup;

/**
 * Created by ameyruikar on 10/22/16.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name_fb) EditText _nameText;
    @InjectView(R.id.input_username) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;


    String fbID;
    String fbName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

        fbID = (String) getIntent().getExtras().get("fbID");
        fbName = (String) getIntent().getExtras().get("name");

        Log.i("intent", "Name received: "+fbName);

        EditText editBox = (EditText) findViewById(R.id.input_name_fb);
        editBox.setText(fbName);
        editBox.setEnabled(false);


        //pills for interests
        TextView pill1 = (TextView) findViewById(R.id.pill1);
        TextView pill2 = (TextView) findViewById(R.id.pill2);
        TextView pill3 = (TextView) findViewById(R.id.pill3);
        TextView pill4 = (TextView) findViewById(R.id.pill4);

        pill1.setText("Theatre");
        pill1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cut_symbol, 0);

        pill2.setText("Food");
        pill2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cut_symbol, 0);

        pill3.setText("Sports");
        pill3.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cut_symbol, 0);

        pill4.setText("Music");
        pill4.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cut_symbol, 0);

        //eof interests

        /*
        Button add = (Button) findViewById(R.id.add_button);
        add.setText("");
        add.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.mipmap.add_button3, 0);
        */

        RadioButton r0 = (RadioButton) findViewById(R.id.radio0);
        RadioButton r1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton r2 = (RadioButton) findViewById(R.id.radio2);
        r0.setText("");
        r1.setText("");
        r0.setTextSize(20);
        r1.setTextSize(20);
        r0.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_stat_name2 , 0);
        r1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_action_name, 0);



    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        /*

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        */

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        /*
                        progressDialog.dismiss();
                        */
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        //setResult(RESULT_OK, null);
        //finish();

        //create new db entry- user


        Intent intent = new Intent(getApplicationContext(), fragment.class);
        startActivity(intent);


    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || email.length() < 3) {
            _emailText.setError("at least 3 characters");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        /*
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        */

        return valid;
    }
}