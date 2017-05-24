package dataAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.utsav.androidflickrtest.R;

import java.util.ArrayList;
import pojo.Items;
import services.ImageDownloadService;

/**
 * Created by utsav on 24/05/2017.
 */

public class GridViewAdapter extends ArrayAdapter<Items> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Items> mImageData = new ArrayList<Items>();

    public GridViewAdapter(Context context, int resource, ArrayList<Items> imageData) {
        super(context, resource, imageData);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mImageData = imageData;
    }

    public void setGridData(ArrayList<Items> mGridData)
    {
        this.mImageData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.mImageData.size();
    }

    @Override
    public Items getItem(int position) {
        return this.mImageData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Items item = this.mImageData.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
        }

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.grid_item_image);
        new ImageDownloadService(imageView).execute(item.media.m);

        return convertView;
    }
}
