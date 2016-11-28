package meetup.amey.com.meetup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class proposeEvent extends AppCompatActivity {


    public void removeButtons(){

        FloatingActionButton action_button_decline = (FloatingActionButton)findViewById(R.id.action_button_decline);
        FloatingActionButton downVote = (FloatingActionButton)findViewById(R.id.downVote);
        FloatingActionButton voteup = (FloatingActionButton)findViewById(R.id.upVote);
        TextView v = (TextView)findViewById(R.id.lightBanner);
        v.setVisibility(View.GONE);

        action_button_decline.setVisibility(View.GONE);
        downVote.setVisibility(View.GONE);
        voteup.setVisibility(View.GONE);

        Toast.makeText(getApplicationContext(), "Your Response has been recorded!", Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_event);

        String eventName = (String) getIntent().getExtras().get("eventName");
        String dateTime = (String) getIntent().getExtras().get("dateTime");
        String eTime = (String) getIntent().getExtras().get("endTime");
        String people = (String) getIntent().getExtras().get("people");
        String location = (String) getIntent().getExtras().get("location");
        String crete = (String)getIntent().getExtras().get("creator");
        String X = (String)getIntent().getExtras().get("X");
        String Y = (String)getIntent().getExtras().get("Y");
        final int historyid = (int) getIntent().getExtras().get("historyid");
        int flag = (int) getIntent().getExtras().get("flag");

        ImageView imageView = (ImageView) findViewById(R.id.imgMap);
        Log.i("maps", "http://maps.google.com/maps/api/staticmap?center=" + X + "," + Y + "&zoom=18&size=1100x1100&markers=color:blue%7C%7C" + X + "," + Y +"&sensor=false");
        new imageDownloader(imageView).execute("http://maps.google.com/maps/api/staticmap?center=" + X + "," + Y + "&zoom=18&size=1100x1100&markers=color:blue%7C%7C" + X + "," + Y +"&sensor=false");



        if(flag == 1){
            removeButtons();
        }
        /*


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    //Your code goes here
                    URL url = new URL("http://maps.google.com/maps/api/staticmap?center="+X+","+Y+"&zoom=18&size=1100x1100&markers=color:blue%7C%7C"+X+","+Y+"&sensor=false");

                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ImageView imageView = (ImageView) findViewById(R.id.imgMap);
                    imageView.setImageBitmap(bmp);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        */


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


        FloatingActionButton voteup = (FloatingActionButton)findViewById(R.id.upVote);
        voteup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeButtons();
                new AsyncVote(getApplicationContext(), historyid, 1).execute();


            }
        });

        FloatingActionButton downVote = (FloatingActionButton)findViewById(R.id.downVote);
        downVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeButtons();
                new AsyncVote(getApplicationContext(), historyid, 0).execute();


            }
        });

        FloatingActionButton action_button_decline = (FloatingActionButton)findViewById(R.id.action_button_decline);
        action_button_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeButtons();

                new AsyncVote(getApplicationContext(), historyid, 2).execute();


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_propose_event, menu);
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
