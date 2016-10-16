package meetup.amey.com.meetup;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.net.*;
import java.util.Locale;
import android.util.*;


/**
 * Created by ameyruikar on 10/16/16.
 */
public class upComingEvents extends Fragment {


    public upComingEvents() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View   v = inflater.inflate(R.layout.fragment_upcoming_events, container, false);

        Display dp = getActivity().getWindowManager().getDefaultDisplay();
        Point pt = new Point();

        dp.getSize(pt);
        int width = pt.x;

        Context m = this.getContext();


        //new loadCurrentTab(v, m, width).execute();



        return v;

    }


}
