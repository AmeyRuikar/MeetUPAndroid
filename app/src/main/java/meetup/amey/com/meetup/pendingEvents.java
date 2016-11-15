package meetup.amey.com.meetup;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ameyruikar on 11/13/16.
 */
public class pendingEvents extends Fragment {


    public pendingEvents() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View   v = inflater.inflate(R.layout.fragment_pending, container, false);

        Display dp = getActivity().getWindowManager().getDefaultDisplay();
        Point pt = new Point();

        dp.getSize(pt);
        int width = pt.x;

        Context m = this.getContext();


        //new loadCurrentTab(v, m, width).execute();

        ArrayList<eventObject> newEvents = new ArrayList<eventObject>();


        newEvents.add(new eventObject(new String("USC vs UCLA"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald and 2 others"),  new String("at Los Angeles Colesium")));

        newEvents.add(new eventObject(new String("USC vs UCLA"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald and 2 others"),  new String("at Los Angeles Colesium")));

        newEvents.add(new eventObject(new String("USC vs UCLA"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald and 2 others"), new String( "at Los Angeles Colesium")));


        ListView pending = (ListView) v.findViewById(R.id.pendingList);
        eventAdapter    adapter = new eventAdapter(m, newEvents);
        pending.setAdapter(adapter);



        Snackbar.make(v, "Advertisement, Try coffee @ philzz", Snackbar.LENGTH_LONG).show();




        return v;

    }


}
