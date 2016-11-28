package meetup.amey.com.meetup;

import android.content.Context;
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
public class AsyncVote extends AsyncTask<String, Void, String> {
    Context mc;
    int vote;
    int historyid;
    public AsyncVote(Context mc, int history, int val){
        this.mc = mc;
        this.vote = val;
        this.historyid = history;
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
        String parameters = "userid="+userid+"&historyid="+this.historyid+"&vote="+this.vote;
        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/updatevote?"+parameters);
            Log.i("markers", "https://meetup-server-150406.appspot.com/updatevote?" + parameters);
            URLConnection urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            res = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }
}
