package com.utsav.androidflickrtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import converter.JsonConverter;
import dataAdapter.GridViewAdapter;
import pojo.FlickrFeed;
import pojo.Items;
import services.FlickrApiService;

/**
 * Created by utsav on 24/05/2017.
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private GridViewAdapter mGridAdapter;
    private ArrayList<Items> mImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupGridView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupGridView()
    {
        this.mGridView = (GridView) findViewById(R.id.gridView);

        this.mImageData = new ArrayList<>();
        this.mGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, mImageData);
        this.mGridView.setAdapter(mGridAdapter);
        this.mGridView.setOnItemClickListener(this);

        this.fetchImages();
    }

    private void fetchImages()
    {
        try {
            String result = new FlickrApiService().execute("https://api.flickr.com/services/feeds/photos_public.gne?format=json&jsoncallback=?").get();
            FlickrFeed flickrFeed = new JsonConverter().convertJsonToObject(result, FlickrFeed.class);
            updateUi(flickrFeed);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void updateUi(FlickrFeed flickrFeed)
    {
        this.mGridAdapter.setGridData(new ArrayList<>(flickrFeed.items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ImageDetailsActivity.class);
        Items item = (Items)parent.getItemAtPosition(position);
        intent.putExtra("item", new JsonConverter().convertToJson(item));
        startActivity(intent);
    }
}
