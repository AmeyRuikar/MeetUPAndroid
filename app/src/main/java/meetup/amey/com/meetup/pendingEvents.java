package meetup.amey.com.meetup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.Toast;

/**
 * Created by ameyruikar on 11/13/16.
 */
public class pendingEvents extends Fragment {

    ArrayList<eventObject> newEvents = new ArrayList<eventObject>();


    Context m;

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

        m = this.getContext();


        //new loadCurrentTab(v, m, width).execute();




        //newEvents.add(new eventObject(new String("USC vs UCLA"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald and 2 others"),  new String("at Los Angeles Colesium")));

        //newEvents.add(new eventObject(new String("USC vs UW"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald and 1 others"),  new String("at Los Angeles Colesium")));

        //newEvents.add(new eventObject(new String("USC vs Notre Dam"), new String("19 Nov 2016, 5:00 PM"), new String("with Ronald"), new String( "at Los Angeles Colesium")));


        ListView pending = (ListView) v.findViewById(R.id.pendingList);
        eventAdapter    adapter = new eventAdapter(m, newEvents);
        pending.setAdapter(adapter);

        new AsyncGetEvents(m, newEvents, adapter).execute("Pending");


        Snackbar snackBar = Snackbar.make(v, "Advertisement, Try coffee @ philzz", Snackbar.LENGTH_LONG).setDuration(100000);

        new AsyncAd(m, snackBar).execute();


        pending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),  newEvents.get(position).getEventName() + " started", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(m, proposeEvent.class);
                intent.putExtra("eventName", newEvents.get(position).getEventName());
                intent.putExtra("dateTime", newEvents.get(position).getDateTime());
                intent.putExtra("endTime", newEvents.get(position).getEndTime());
                intent.putExtra("people", newEvents.get(position).getPeople());
                intent.putExtra("location", "");
                intent.putExtra("creator", newEvents.get(position).getCreatedby());
                intent.putExtra("X", newEvents.get(position).getX());
                intent.putExtra("Y", newEvents.get(position).getY());
                intent.putExtra("historyid", newEvents.get(position).getHistoryid());
                intent.putExtra("flag", 0);

                startActivity(intent);


            }
        });




        return v;

    }


}
