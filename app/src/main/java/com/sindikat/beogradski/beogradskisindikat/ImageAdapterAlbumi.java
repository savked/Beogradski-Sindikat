package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;

public class ImageAdapterAlbumi extends BaseAdapter {
    private Context mContext;

    public ImageAdapterAlbumi(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
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

            imageView.setLayoutParams(new GridView.LayoutParams(360, 360));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.album1, R.drawable.album2,
            R.drawable.album3, R.drawable.album4,
            R.drawable.album5, R.drawable.album6
    };
}