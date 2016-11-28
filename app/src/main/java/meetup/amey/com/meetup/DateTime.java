package meetup.amey.com.meetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ameyruikar on 11/3/16.
 */
public class DateTime extends FragmentActivity {

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");


    Button currentBtn = null;
    String which = null;
    Context m;

    public DateTime(Button cur, String whichButton, Context mContext){
        this.currentBtn = cur;
        this.which = whichButton;
        this.m = mContext;
    }

    public SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            /*
            Toast.makeText(DateTime.this,
                    mFormatter.format(date), Toast.LENGTH_SHORT).show();
             */



            if(currentBtn != null)
            {
                if(which.equals("START")){
                    //Toast.makeText(DateTime.this, "START", Toast.LENGTH_SHORT).show();
                    Log.i("datetime", "START");

                    SharedPreferences p;
                    p = m.getSharedPreferences("meetup.amey.com.meetup", Context.MODE_PRIVATE);
                    p.edit().putString("starttime", Long.toString((long) (date.getTime()/1000.0))).apply();

                }
                else if(which.equals("END")){
                   // Toast.makeText(DateTime.this, "END", Toast.LENGTH_SHORT).show();
                    Log.i("datetime", "END");

                    SharedPreferences p;
                    p = m.getSharedPreferences("meetup.amey.com.meetup", Context.MODE_PRIVATE);
                    p.edit().putString("endtime", Long.toString((long) (date.getTime()/1000.0))).apply();
                    //Long.toString((long) (date.getTime()/1000.0))

                }


                currentBtn.setText(mFormatter.format(date));
                //currentBtn.setGravity(Gravity.LEFT);
            }


        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            /*
            Toast.makeText(DateTime.this,
                    "Canceled", Toast.LENGTH_SHORT).show();\
                    */
        }
    };




}
