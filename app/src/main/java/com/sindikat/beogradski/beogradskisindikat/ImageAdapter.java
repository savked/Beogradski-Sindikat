package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    Integer[] imageIDs = {
            R.drawable.slika1, R.drawable.slika2,
            R.drawable.slika3, R.drawable.slika4
            // DODATI VISE SLIKA
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imageIDs.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            imageView.setLayoutParams(new GridView.LayoutParams(360, 640));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imageIDs[position]);

        return imageView;
    }
}