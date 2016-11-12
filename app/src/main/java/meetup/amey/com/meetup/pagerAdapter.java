package meetup.amey.com.meetup;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;


/**
 * Created by ameyruikar on 10/16/16.
 */
public class pagerAdapter extends FragmentStatePagerAdapter {

    private String titles[] = {"Pending","Up Coming events", "History"};

    public pagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if(position == 0){
            f = new upComingEvents();
        }
        else if(position == 1){
            f = new upComingEvents();
        }
        else{
            f = new historyTab();
        }

        return f;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    public CharSequence getPageTitle(int position){
        return  titles[position];
    }
}
