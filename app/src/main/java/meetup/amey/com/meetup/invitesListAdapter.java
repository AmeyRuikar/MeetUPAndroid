package meetup.amey.com.meetup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameyruikar on 11/11/16.
 */
public class invitesListAdapter extends ArrayAdapter<invitesListElement> {


    private Context mContext;
    private ArrayList<invitesListElement> localCopy;


    public invitesListAdapter(Context context, ArrayList<invitesListElement> objects) {
        super(context, R.layout.invitesrow, objects);

        this.mContext = context;
        this.localCopy = objects;

        //Log.i("array","len " + this.localCopy.size());

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflate= (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View rowV = inflate.inflate(R.layout.invitesrow, parent, false);
        TextView name = (TextView) rowV.findViewById(R.id.name);
        name.setText(localCopy.get(position).getName());
        return rowV;

    }


}
