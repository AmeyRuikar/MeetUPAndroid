package meetup.amey.com.meetup;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by ameyruikar on 11/12/16.
 */
public class eventAdapter extends ArrayAdapter<eventObject> {


    private Context mContext;
    private ArrayList<eventObject> localCopy;

    public eventAdapter(Context context, ArrayList<eventObject> objects) {
        super(context, R.layout.event_row, objects);
        this.mContext = context;
        this.localCopy = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflate= (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View rowV = inflate.inflate(R.layout.event_row, parent, false);

        TextView name = (TextView) rowV.findViewById(R.id.nameEvent);
        TextView dateTime = (TextView) rowV.findViewById(R.id.date);
        TextView people = (TextView) rowV.findViewById(R.id.people);
        TextView local = (TextView) rowV.findViewById(R.id.local);

        name.setText(localCopy.get(position).getEventName());
        dateTime.setText(localCopy.get(position).getDateTime());
        people.setText(localCopy.get(position).getPeople());
        local.setText(localCopy.get(position).getLocation());

        int status = 1;

        if(status == 1){
            rowV.setAlpha((float) (0.25));
            rowV.setBackgroundColor(Color.parseColor("#1da1f2"));
        }

        return rowV;

    }


}
