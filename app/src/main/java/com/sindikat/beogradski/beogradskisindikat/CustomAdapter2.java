package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter2 extends ArrayAdapter<String> {

    CustomAdapter2(Context context, String[] stavke) {
        super(context, R.layout.custom_row, stavke);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(getContext());
        View customView = inf.inflate(R.layout.custom_row, parent, false);

        String single = getItem(position);
        TextView tv = (TextView) customView.findViewById(R.id.textViewCustom);
        ImageView iv = (ImageView) customView.findViewById(R.id.imageViewCustom);

        tv.setText(single);
        switch (position) {
            case 0:
                iv.setImageResource(R.drawable.fedja);
                break;
            case 1:
                iv.setImageResource(R.drawable.ogi);
                break;
            case 2:
                iv.setImageResource(R.drawable.skabo);
                break;
            case 3:
                iv.setImageResource(R.drawable.sefsale);
                break;
            case 4:
                iv.setImageResource(R.drawable.marko);
                break;
            case 5:
                iv.setImageResource(R.drawable.dare);
                break;
        }
        return customView;

    }
}
