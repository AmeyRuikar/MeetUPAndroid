package meetup.amey.com.meetup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.net.*;
import java.util.Locale;
import android.util.*;
import android.widget.Toast;


/**
 * Created by ameyruikar on 10/16/16.
 */
public class historyTab extends Fragment {


    ArrayList<eventObject> newEvents3 = new ArrayList<eventObject>();
    Context m;


    public historyTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View   v = inflater.inflate(R.layout.fragment_history, container, false);

        Display dp = getActivity().getWindowManager().getDefaultDisplay();
        Point pt = new Point();

        dp.getSize(pt);
        int width = pt.x;

        m = this.getContext();


        //new loadCurrentTab(v, m, width).execute();


        ListView pending = (ListView) v.findViewById(R.id.historyList);
        eventAdapter    adapter = new eventAdapter(m, newEvents3);
        pending.setAdapter(adapter);

        new AsyncGetEvents(m, newEvents3, adapter).execute("Completed");

       // Snackbar.make(v, "Advertisement, Try coffee @ philzz", Snackbar.LENGTH_LONG).show();



        pending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), newEvents3.get(position).getEventName() + " started", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(m, proposeEvent.class);
                intent.putExtra("eventName", newEvents3.get(position).getEventName());
                intent.putExtra("dateTime", newEvents3.get(position).getDateTime());
                intent.putExtra("endTime", newEvents3.get(position).getEndTime());
                intent.putExtra("people", newEvents3.get(position).getPeople());
                intent.putExtra("location", "");
                intent.putExtra("creator", newEvents3.get(position).getCreatedby());
                intent.putExtra("X", newEvents3.get(position).getX());
                intent.putExtra("Y", newEvents3.get(position).getY());
                intent.putExtra("historyid", newEvents3.get(position).getHistoryid());
                intent.putExtra("flag", 1);

                startActivity(intent);


            }
        });




        return v;

    }

}
