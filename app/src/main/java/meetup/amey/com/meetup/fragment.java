package meetup.amey.com.meetup;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.util.*;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.scalified.fab.ActionButton;

public class fragment extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        /*

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Meet UP");


        ab.setTitle("Meet UP");
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1da1f2")));

        */

        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);

        actionButton.moveLeft(100.0f);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Add", "new task can be added from here");

            }
        });

        //actionButton.moveRight(1000.0f);


        ViewPager   vp = (ViewPager)findViewById(R.id.view_pager);

        TabLayout   tl = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager fm = getSupportFragmentManager();


        pagerAdapter    pageAdapter = new pagerAdapter(fm);

        vp.setAdapter(pageAdapter);

        tl.setupWithViewPager(vp);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
        tl.setTabsFromPagerAdapter(pageAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
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
