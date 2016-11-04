package meetup.amey.com.meetup;

import android.support.v4.app.FragmentActivity;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ameyruikar on 11/3/16.
 */
public class DateTime extends FragmentActivity {

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");


    Button currentBtn = null;

    public DateTime(Button cur){
        this.currentBtn = cur;
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
                currentBtn.setText(mFormatter.format(date));
                currentBtn.setGravity(Gravity.LEFT);
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
