package meetup.amey.com.meetup;

/**
 * Created by ameyruikar on 11/10/16.
 */
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import android.graphics.Bitmap;
import java.lang.Runnable;
import android.graphics.BitmapFactory;
import android.app.Activity;
import java.net.URL;
import java.lang.Runnable;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class cardsFragment extends Fragment {

    ArrayList<wonderClass> listitems = new ArrayList<>();
    Context mContext;
    Activity showEvents;
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu","Petra","Taj Mahal","Colosseum"};
    //int  Images[] = {R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2};
    final String Images[] = {
            "https://s3-media3.fl.yelpcdn.com/bphoto/sZ17Va53NSYwlbaEroL6eQ/ms.jpg",
            "https://s3-media4.fl.yelpcdn.com/bphoto/19WlSmfgfnqx6sewSMHtDw/ms.jpg",
            "https://s3-media4.fl.yelpcdn.com/bphoto/_spGtwkh-w0_6hub8siWVw/ms.jpg",
            "https://s3-media4.fl.yelpcdn.com/bphoto/6qmiXfiPFQpOqP84tS3Z-w/ms.jpg",
            "https://s3-media1.fl.yelpcdn.com/bphoto/3OQIShgHyPFjCM2ZKPh16w/ms.jpg",
            "https://s3-media2.fl.yelpcdn.com/bphoto/jldZ61576iBdP9_Sr2-z0g/ms.jpg",
            "https://s3-media3.fl.yelpcdn.com/bphoto/MXc4ricdumSS6yiLYPQyvg/ms.jpg"
    };

    public cardsFragment(){

    }
    public cardsFragment(Context applicationContext, Activity a) {
        mContext = applicationContext;
        showEvents = a;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();

        //getActivity().setTitle("7 Wonders of the Modern World");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<wonderClass> list;

        public MyAdapter(ArrayList<wonderClass> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.titleTextView.setText(list.get(position).getCardName());


            holder.likeImageView.setTag(R.drawable.ic_dots_black);

            new imageDownloader(holder.coverImageView).execute(Images[position]);


        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;


        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id = (int) likeImageView.getTag();
                    if (id == R.drawable.ic_maps) {

                        likeImageView.setTag(R.drawable.ic_maps);
                        likeImageView.setImageResource(R.drawable.ic_maps);

                        Toast.makeText(getActivity(), titleTextView.getText() + " added to favourites", Toast.LENGTH_SHORT).show();

                    } else {

                        likeImageView.setTag(R.drawable.ic_maps);
                        likeImageView.setImageResource(R.drawable.ic_maps);
                        Toast.makeText(getActivity(), titleTextView.getText() + " removed from favourites", Toast.LENGTH_SHORT).show();


                    }

                }
            });



            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Toast.makeText(getActivity(), "now sharing the event with others", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(showEvents);
                    builder.setTitle("Create this Event at " + titleTextView.getText());
                    builder.setMessage("Share with others?");


                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Toast.makeText(getActivity(), titleTextView.getText() + " just make the fragemnt now", Toast.LENGTH_SHORT).show();

                            //later move to async task on post execute
                            Intent i = new Intent(showEvents, fragment.class);
                            // set the new task and clear flags
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            dialog.dismiss();
                            startActivity(i);

                        }
                    });


                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();


                }
            });



        }
    }

    public void initializeList() {
        listitems.clear();

        for(int i =0;i<7;i++){


            wonderClass item = new wonderClass();
            item.setCardName(Wonders[i]);
            item.setImageResourceId(Images[i]);
            item.setIsfav(0);
            item.setIsturned(0);

            listitems.add(item);

        }
    }
}