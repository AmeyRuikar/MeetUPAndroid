package meetup.amey.com.meetup;

/**
 * Created by ameyruikar on 11/10/16.
 */
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import java.util.concurrent.ExecutionException;


public class cardsFragment extends Fragment {

    ArrayList<wonderClass> listitems = new ArrayList<>();
    Context mContext;
    Activity showEvents;
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu","Petra","Taj Mahal","Colosseum"};
    //int  Images[] = {R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2,R.drawable.p2};
    final String Images[] = {
            "http://s1.ticketm.net/dam/a/be5/29311002-718a-407a-949c-7e9a4339cbe5_29181_EVENT_DETAIL_PAGE_16_9.jpg",
            "http://s1.ticketm.net/dam/c/25d/09139288-a226-487d-a98d-6136663e325d_106551_CUSTOM.jpg",
            "http://s1.ticketm.net/dam/c/25d/09139288-a226-487d-a98d-6136663e325d_106551_CUSTOM.jpg",
            "http://s1.ticketm.net/dam/c/ab4/6367448e-7474-4650-bd2d-02a8f7166ab4_106161_RECOMENDATION_16_9.jpg",
            "http://s1.ticketm.net/dam/c/ab4/6367448e-7474-4650-bd2d-02a8f7166ab4_106161_RECOMENDATION_16_9.jpg",
            "http://s1.ticketm.net/dam/a/f4a/10c1e9aa-5c55-4654-afb5-a2b15dfe0f4a_107841_ARTIST_PAGE_3_2.jpg",
            "http://s1.ticketm.net/dam/c/ab4/6367448e-7474-4650-bd2d-02a8f7166ab4_106161_RECOMENDATION_16_9.jpg"
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


            /*
            Thread thread = new Thread(new Runnable(URL curUrl) {
                @Override
                public void run(URL thisurl) {

                    try {

                        URL url = new URL(Images[position]);
                        Bitmap bmp = null;
                        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        holder.coverImageView.setImageBitmap(bmp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            */

            //thread.start();


            /*
            showEvents.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {

                        URL url = new URL(Images[position]);
                        Bitmap bmp = null;
                        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        holder.coverImageView.setImageBitmap(bmp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });

            */


           // holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            //holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.likeImageView.setTag(R.drawable.ic_dots_black);


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

                    /*

                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("image/jpeg");
                    //startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

                    */

                    Toast.makeText(getActivity(), "now sharing the event with others", Toast.LENGTH_SHORT).show();


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