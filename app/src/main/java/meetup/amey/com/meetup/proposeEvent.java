package meetup.amey.com.meetup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.*;
import android.widget.ImageView;
import android.widget.ListView;

public class proposeEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_event);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    //Your code goes here
                    URL url = new URL("http://maps.google.com/maps/api/staticmap?center=48.858235,2.294571&zoom=18&size=1100x1100&markers=color:blue%7C%7C48.858235,2.294571&sensor=false");

                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ImageView imageView = (ImageView) findViewById(R.id.imgMap);
                    imageView.setImageBitmap(bmp);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();



        ArrayList<invitesListElement> invites = new ArrayList<invitesListElement>();
        invites.add(new invitesListElement("@Ronald"));
        invites.add(new invitesListElement("@Amanda1"));
        invites.add(new invitesListElement("@Amanda2"));
        invites.add(new invitesListElement("@Amanda3"));
        ListView lv = (ListView) findViewById(R.id.userinvites);
        invitesListAdapter nla = new invitesListAdapter(getApplicationContext(), invites);
        lv.setAdapter(nla);

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
