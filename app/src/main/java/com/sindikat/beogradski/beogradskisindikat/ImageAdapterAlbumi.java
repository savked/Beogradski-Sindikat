package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(mContext);
        View customView = inf.inflate(R.layout.custom_albumi, parent, false);

        ImageView imageView = customView.findViewById(R.id.albumiSlika);
        TextView textView = customView.findViewById(R.id.albumiIme);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setImageResource(mThumbIds[position]);
        textView.setText(imenaAlbuma[position]);

        return customView;
    }

    // references to images
    private Integer[] mThumbIds = {
            R.drawable.album1, R.drawable.album2,
            R.drawable.album3, R.drawable.album4,
            R.drawable.album5, R.drawable.album6
    };
    private String[] imenaAlbuma = {
            "BSSST... Tišinčina (2001)", "Govedina (2002)",
            "Svi zajedno (2005)", "Oni su (2006)",
            "Diskretni heroji (2010)", "Singlovi (2015+)"
    };
}