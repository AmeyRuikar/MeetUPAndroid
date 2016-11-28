package meetup.amey.com.meetup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ameyruikar on 11/25/16.
 */
public class AsyncCreateEvent extends AsyncTask<String, Void, String> {
    Context mc;

    public AsyncCreateEvent(Context mCon){
        this.mc = mCon;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader br;
        String  res = null;
        String query = "";

        SharedPreferences p;
        p = mc.getSharedPreferences("meetup.amey.com.meetup", Context.MODE_PRIVATE);
        String userlist = p.getString("invites", null);
        String stime = p.getString("starttime", null);
        String etime = p.getString("endtime", null);
        String creator = p.getString("thisMember", null);

        //TODO: creator to make this dynamic

        String parameters = "eventid="+params[0]+"&userlist="+userlist+"&starttime="+stime+"&endtime="+etime+"&createdby="+creator;
        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/createevent?"+parameters);
            Log.i("markers", "https://meetup-server-150406.appspot.com/createevent?"+parameters);
            URLConnection urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            res = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {


        Intent i = new Intent(mc, fragment.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mc.startActivity(i);
    }
}
