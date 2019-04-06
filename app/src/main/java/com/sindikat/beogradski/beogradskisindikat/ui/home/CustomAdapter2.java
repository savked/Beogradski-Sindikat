package com.sindikat.beogradski.beogradskisindikat.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sindikat.beogradski.beogradskisindikat.R;

class CustomAdapter2 extends ArrayAdapter<String> {

    CustomAdapter2(Context context, String[] stavke) {
        super(context, R.layout.custom_row, stavke);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(getContext());
        View customView = inf.inflate(R.layout.custom_rowhome, parent, false);

        String single = getItem(position);
        TextView tv = (TextView) customView.findViewById(R.id.textViewCustom);
        ImageView iv = (ImageView) customView.findViewById(R.id.imageViewCustom);

        tv.setText(single);
        switch (position) {
            case 0:
                iv.setImageResource(R.drawable.clanovi);
                break;
            case 1:
                iv.setImageResource(R.drawable.muzika);
                break;
            case 2:
                iv.setImageResource(R.drawable.slike);
                break;
            case 3:
                iv.setImageResource(R.drawable.nastupi);
                break;
            case 4:
                iv.setImageResource(R.drawable.oaplikaciji);
                break;
        }
        return customView;

    }
}
