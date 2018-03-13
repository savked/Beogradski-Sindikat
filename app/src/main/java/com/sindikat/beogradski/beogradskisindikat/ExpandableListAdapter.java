package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by savke on 08.03.2018..
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    public ExpandableListAdapter() {
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item, il = Child item
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);

        viewGroup.setClickable(false);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_album, null);
        }
        TextView ImeAlbuma = (TextView) view.findViewById(R.id.ImeAlbuma);
        ImeAlbuma.setText(headerTitle);

        ImageView slikaAlbuma = (ImageView) view.findViewById(R.id.SlikaAlbuma);

        if(i == 0){
            slikaAlbuma.setImageResource(R.drawable.album1);
            slikaAlbuma.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if(i == 1) slikaAlbuma.setImageResource(R.drawable.album2);
        if(i == 2){
            slikaAlbuma.setImageResource(R.drawable.album3);
            slikaAlbuma.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if(i == 3) slikaAlbuma.setImageResource(R.drawable.album4);
        if(i == 4) slikaAlbuma.setImageResource(R.drawable.album5);
        if(i == 5){
            slikaAlbuma.setImageResource(R.drawable.album6);
            slikaAlbuma.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        viewGroup.setClickable(false);
        final String imePesme = (String) getChild(i, i1);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_pesme, null);
        }
        TextView ImePesme = (TextView) view.findViewById(R.id.ImePesme);
        ImePesme.setText(imePesme);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}