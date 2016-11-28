package meetup.amey.com.meetup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ameyruikar on 11/26/16.
 */
public class AsyncCreatorUser extends AsyncTask<String, Void, String> {
    String fb;
    String username;
    Context mc;

    public AsyncCreatorUser(String fbID, String emailText, Context applicationContext) {
        this.fb = fbID;
        this.username = emailText;
        this.mc= applicationContext;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader br;
        String  res = null;
        String query = "";

        SharedPreferences p;
        p = mc.getSharedPreferences("meetup.amey.com.meetup", Context.MODE_PRIVATE);
        String userid = p.getString("thisMember", null);

        //TODO: dynamic members
        //https://meetup-server-150406.appspot.com/createuser?userid=12124&fbname=geo&username=ron&interests=food,music&trans=uber
        String parameters = "&userid="+this.fb+"&username="+this.username;
        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/createuser?fbname=geo&interests=food,music&trans=uber"+parameters);
            Log.i("markers", "https://meetup-server-150406.appspot.com/createuser?" + parameters);
            URLConnection urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            res = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("markers", res);



        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(mc, fragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mc.startActivity(intent);
    }
}
