package meetup.amey.com.meetup;


import android.widget.ImageView;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import android.graphics.BitmapFactory;
import android.util.Log;
/**
 * Created by ameyruikar on 11/14/16.
 */
class imageDownloader extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;

    public imageDownloader(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {


        URL url = null;
        Bitmap bmp = null;
        try {
            url = new URL(params[0]);
            Log.i("maps", params[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    //Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    //imageView.setImageDrawable(placeholder);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
