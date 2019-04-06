package com.sindikat.beogradski.beogradskisindikat.ui.muzika;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sindikat.beogradski.beogradskisindikat.R;

public class ImageAdapterAlbumi extends BaseAdapter {
    private Context mContext;

    public ImageAdapterAlbumi(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imenaAlbuma.length;
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

        //imageView.setImageResource(mThumbIds[position]);
        textView.setText(imenaAlbuma[position]);

        return customView;
    }
    private String[] imenaAlbuma = {
            "BSSST... Tišinčina (2001)", "Govedina (2002)",
            "Svi zajedno (2005)", "Oni su (2006)",
            "Diskretni heroji (2010)", "Singlovi (2015+)"
    };
}