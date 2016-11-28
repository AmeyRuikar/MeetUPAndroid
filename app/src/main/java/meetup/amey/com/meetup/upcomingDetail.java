package meetup.amey.com.meetup;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;
import android.widget.Button;
import android.widget.Toast;

import com.google.maps.*;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.util.Timer;
import java.util.TimerTask;

public class upcomingDetail extends AppCompatActivity {
    String X;
    String Y;
    int historyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_detail);


        String eventName = (String) getIntent().getExtras().get("eventName");
        String dateTime = (String) getIntent().getExtras().get("dateTime");
        String eTime = (String) getIntent().getExtras().get("endTime");
        String people = (String) getIntent().getExtras().get("people");
        String location = (String) getIntent().getExtras().get("location");
        String crete = (String)getIntent().getExtras().get("creator");
         X = (String)getIntent().getExtras().get("X");
         Y = (String)getIntent().getExtras().get("Y");
        historyid = (int) getIntent().getExtras().get("historyid");

        ImageView imageView = (ImageView) findViewById(R.id.imgMap);
        new imageDownloader(imageView).execute("http://maps.google.com/maps/api/staticmap?center=" + X + "," + Y + "&zoom=18&size=1100x1100&markers=color:blue%7C%7C" + X + "," + Y + "&sensor=false");

        TextView eN = (TextView) findViewById(R.id.eventName);
        TextView creator = (TextView) findViewById(R.id.organiser);
        TextView startTime = (TextView) findViewById(R.id.dtStart);
        TextView endTime = (TextView) findViewById(R.id.dtEnd);
        TextView latlong = (TextView) findViewById(R.id.location);

        eN.setText(eventName);
        creator.setText(crete);
        startTime.setText(dateTime);
        endTime.setText(eTime);
        latlong.setText(location);

        ArrayList<invitesListElement> invites = new ArrayList<invitesListElement>();

        String shortPeople = people.substring(1, people.length()-2);
        shortPeople = shortPeople.replace("\"", " ");
        shortPeople = shortPeople.replace(",", " ");
        String finalList[] = shortPeople.split(" ");

        for(int i=0; i < finalList.length; i++){

            if(!finalList[i].equals("")){
                invites.add(new invitesListElement("@"+finalList[i].trim()));
            }


        }



        /*

        invites.add(new invitesListElement("@Ronald"));
        invites.add(new invitesListElement("@Amanda1"));
        invites.add(new invitesListElement("@Amanda2"));
        invites.add(new invitesListElement("@Amanda3"));
        */
        ListView lv = (ListView) findViewById(R.id.userinvites);
        invitesListAdapter nla = new invitesListAdapter(getApplicationContext(), invites);
        lv.setAdapter(nla);

        final TextView eta1 = (TextView) findViewById(R.id.eta1);
        final TextView eta2 = (TextView) findViewById(R.id.eta2);
        final TextView eta3 = (TextView) findViewById(R.id.eta3);

       final Button start = (Button) findViewById(R.id.start_travel);
        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                start.setText("Mark this Event as Completed");

                TextView banner = (TextView) findViewById(R.id.ETAbanner);
                banner.setVisibility(View.VISIBLE);


                int minutes = 1;

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        GeoApiContext con = new GeoApiContext().setApiKey("AIzaSyCXpUMDiQ4_7NeQdUR-bL9ToVvYH2f64vU");
                        LocationManager locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
                        Criteria criteria = new Criteria();
                        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                        try {
                            DistanceMatrix matrix = DistanceMatrixApi.newRequest(con).origins(new LatLng(location.getLatitude(),location.getLongitude()).toString()).destinations(new LatLng(Double.parseDouble(X), Double.parseDouble(Y)).toString()).mode(TravelMode.DRIVING).units(Unit.METRIC).await();
                            //Toast.makeText(getApplicationContext(), "Time required "+ matrix.rows[0].elements[0].duration.inSeconds, Toast.LENGTH_SHORT).show();
                            Log.i("markers", "ETA: " + matrix.rows[0].elements[0].duration.inSeconds);

                            new AsyncUpdatePosition(matrix.rows[0].elements[0].duration.inSeconds, historyid, getApplicationContext(), eta1, eta2, eta3).execute();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, 0, 1000*20);



            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upcoming_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
