package meetup.amey.com.meetup;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.text.style.ImageSpan;
import android.content.Context;

import android.text.SpannableStringBuilder;


/**
 * Created by ameyruikar on 10/16/16.
 */
public class pagerAdapter extends FragmentStatePagerAdapter {
    Context mContext;


    private String titles[] = {"Pending","Up Coming events", "History"};

    public pagerAdapter(FragmentManager fm, Context m) {
        super(fm);
        mContext = m;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if(position == 0){
            f = new pendingEvents();
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

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(titles[position]).
                append("", new ImageSpan(mContext, R.drawable.ic_dots_black), 0);

        return  builder;
    }
}
