package meetup.amey.com.meetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

/**
 * Created by ameyruikar on 11/26/16.
 */
public class AsyncUpdatePosition extends AsyncTask<String, Void, String> {

    int h;
    long sec;
    Context mc;
    TextView eta1;
    TextView eta2;
    TextView eta3;

    public AsyncUpdatePosition(long inSeconds, int historyid, Context context, TextView eta1, TextView eta2, TextView eta3) {
        this.h = historyid;
        this.sec = inSeconds;
        this.mc = context;
        this.eta1 = eta1;
        this.eta2 = eta2;
        this.eta3 = eta3;
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
        //https://meetup-server-150406.appspot.com/updateeta?userid=2&historyid=6&eta=7401
        String parameters = "userid="+userid+"&historyid="+this.h+"&eta="+this.sec;
        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/updateeta?"+parameters);
            Log.i("markers", "https://meetup-server-150406.appspot.com/updateeta?" + parameters);
            URLConnection urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            res = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("markers", res);
        return res;
    }


    @Override
    protected void onPostExecute(String result) {
        Log.i("markers", result);

        JSONObject data = null;
        try {
            data = new JSONObject(result);
            JSONArray etas = new JSONArray(data.getString("usereta"));

            if(etas.length() == 0){
                return;
            }

            int counter = 3;
            for(int i=0; i<etas.length(); i++){

                JSONObject obj = etas.getJSONObject(i);

                String name = obj.getString("name");
                String eta = obj.getString("eta");
                String retString ="";

                if(!eta.equals("Not yet started")){

                    long time = Long.parseLong(eta);


                    int hours = (int) time / 3600;
                    int remainder = (int) time - hours * 3600;
                    int mins = remainder / 60;
                    remainder = remainder - mins * 60;
                    int secs = remainder;

                    int[] ints = {hours , mins , secs};
                    retString = hours + " hour/s " + mins + " minutes";


                }
                else{
                    retString = "Not yet started";
                }


                if(i==0){
                    eta1.setVisibility(View.VISIBLE);
                    eta1.setText(name + " - "+retString);
                }
                else if (i==1){
                    eta2.setVisibility(View.VISIBLE);
                    eta2.setText(name + " - "+retString);
                }
                else if(i==2){
                    eta3.setVisibility(View.VISIBLE);
                    eta3.setText(name + " - "+retString);
                }
                counter--;
                if(counter == 0){
                    break;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
