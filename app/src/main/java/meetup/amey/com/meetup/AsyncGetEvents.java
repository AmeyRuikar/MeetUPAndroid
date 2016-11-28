package meetup.amey.com.meetup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by ameyruikar on 11/25/16.
 */
public class AsyncGetEvents extends AsyncTask<String, Void, String> {

    Context mc;
    ArrayList<eventObject> localCopy;
    eventAdapter localAdapter;

    public AsyncGetEvents(Context mCon, ArrayList<eventObject> list, eventAdapter e){
        this.mc = mCon;
        this.localCopy = list;
        this.localAdapter = e;
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
        String parameters = "userid="+userid+"&eventType="+params[0];
        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/getevents?"+parameters);
            Log.i("markers", "https://meetup-server-150406.appspot.com/getevents?" + parameters);
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

        try {
            JSONObject data = new JSONObject(result);
            JSONArray events = new JSONArray(data.getString("retrievedEvents"));

            if(events.length() == 0){
                return;
            }

            for(int i= events.length()-1; i>=0; i--){
                JSONObject obj = events.getJSONObject(i);

                String en = obj.getString("eventName");
                int st1 = obj.getInt("eventstarttime");
                int et1 = obj.getInt("eventendtime");
                String invites = obj.getString("invited");
                String creator = obj.getString("createdby");
                Double lat = obj.getDouble("eventlocationX");
                Double l = obj.getDouble("eventlocationY");
                int historyid = obj.getInt("historyid");

                long start = st1*1000L;
                long end = et1*1000L;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




                localCopy.add(new eventObject(en, sdf.format(start), sdf.format(end),invites, new String( "at Los Angeles Colesium"), new LatLng(lat,l),creator, Double.toString(lat), Double.toString(l), historyid));


            }

            localAdapter.notifyDataSetChanged();




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}



