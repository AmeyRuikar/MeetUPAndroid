package meetup.amey.com.meetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by ameyruikar on 11/24/16.
 */
public class loadEventMarkers extends AsyncTask<String, Void, String> {

    private ArrayList<LatLng> localCopy;
    private GoogleMap localMap;
    private ArrayList<eventMarkerObject> localreturnedEvents;

    public loadEventMarkers(ArrayList<LatLng> pts, GoogleMap map, ArrayList<eventMarkerObject> returnedEvents){
        this.localCopy = pts;
        this.localMap = map;
        this.localreturnedEvents = returnedEvents;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader br;
        String  res = null;
        String query = "";

        for(int i=0; i<localCopy.size(); i++){
            query = query+","+localCopy.get(i).latitude+"%20"+localCopy.get(i).longitude;
        }


        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/spatial?points="+query.substring(1));
            Log.i("markers", "https://meetup-server-150406.appspot.com/spatial?points="+query.substring(1));
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
            //JSONObject array = new JSONObject(result);
            JSONArray array = new JSONArray(result);


            for(int i=0; i<array.length(); i++){

                JSONObject event = array.getJSONObject(i);
                double lat = event.getDouble("X");
                double longitude = event.getDouble("Y");


                String eventName = event.getString("eventName");
                String eventid = event.getString("eventid");
                String g = event.getString("genre");
                String subg = event.getString("subgenre");
                String url = event.getString("imgurl");



                localMap.addMarker(new MarkerOptions().position(new LatLng(lat, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
                localreturnedEvents.add(new eventMarkerObject(eventid, eventName, g, subg, new LatLng(lat, longitude), url));

            }



        }catch (Exception   e) {

        }



    }
}
