package com.sindikat.beogradski.beogradskisindikat.ui.muzika.pesme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sindikat.beogradski.beogradskisindikat.R;

/**
 * Created by savke on 22.03.2018..
 */

public class ListAdapterPesme extends ArrayAdapter<String>{

    public ListAdapterPesme(Context context, String[] imenaPesama) {
        super(context, 0, imenaPesama);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(getContext());
        View customView = inf.inflate(R.layout.custom_pesme, parent, false);

        String single = getItem(position);
        TextView imepesme = (TextView) customView.findViewById(R.id.ImePesme);
        imepesme.setText(single);

        return customView;
    }
}
