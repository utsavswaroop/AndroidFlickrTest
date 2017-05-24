package com.utsav.androidflickrtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import converter.JsonConverter;
import helpers.DateFormater;
import pojo.Items;
import services.ImageDownloadService;

/**
 * Created by utsav on 24/05/2017.
 */

public class ImageDetailsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTitleTextView;
    private TextView mDateTakentextView;
    private TextView mPublishedTextView;
    private TextView mAuthorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_details);

        this.mImageView = (ImageView) findViewById(R.id.imageView);
        this.mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        this.mDateTakentextView = (TextView) findViewById(R.id.dateTakentextView);
        this.mPublishedTextView = (TextView) findViewById(R.id.publishedTextView);
        this.mAuthorTextView = (TextView) findViewById(R.id.authorTextView);

        Bundle bundle = getIntent().getExtras();
        String itemString = bundle.getString("item");
        Items item = new JsonConverter().convertJsonToObject(itemString, Items.class);
        updateUi(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void updateUi(Items item)
    {
        new ImageDownloadService(mImageView).execute(item.media.m);
        this.mTitleTextView.setText(item.title);
        this.mDateTakentextView.setText(new DateFormater().formatDate(item.date_taken));
        this.mPublishedTextView.setText(new DateFormater().formatDate(item.published));
        this.mAuthorTextView.setText(item.author);
    }
}
