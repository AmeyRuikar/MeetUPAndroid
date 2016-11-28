package meetup.amey.com.meetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ameyruikar on 11/26/16.
 */
public class AsyncAd extends AsyncTask<String, Void, String> {
    Context mc;
    Snackbar s;
    public AsyncAd(Context m, Snackbar snackBar) {
        this.mc = m;
        this.s = snackBar;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader br;
        String  res = null;
        String query = "";

        SharedPreferences p;
        p = mc.getSharedPreferences("meetup.amey.com.meetup", Context.MODE_PRIVATE);


        //TODO: creator to make this dynamic

        LocationManager locationManager = (LocationManager) mc.getSystemService(mc.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));


        try {
            URL url = new URL("https://meetup-server-150406.appspot.com/adsense?x="+ String.valueOf(location.getLatitude())+"&y="+String.valueOf(location.getLongitude()));
            Log.i("markers", "https://meetup-server-150406.appspot.com/createevent?");
            URLConnection urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            res = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("ad", result.toString());
        if(!result.toString().equals("")){
            this.s.setText(result.toString().replace("[", "").replace("]", "").replace("\"", "")).show();
        }

    }
}
