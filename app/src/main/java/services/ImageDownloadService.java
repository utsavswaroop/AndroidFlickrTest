package services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by utsav on 24/05/2017.
 */

public class ImageDownloadService extends AsyncTask<String, Void, Bitmap> {

    final ImageView mImageView;

    public ImageDownloadService (ImageView imageView)
    {
        this.mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageUrl = params[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new URL(imageUrl).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        this.mImageView.setImageBitmap(result);
    }
}