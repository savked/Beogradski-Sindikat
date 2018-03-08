package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Muzika extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> imenaAlbuma;
    private HashMap<String, List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbor_pesama);

        listView = (ExpandableListView) findViewById(R.id.listViewExp);
        initData();
        listAdapter = new ExpandableListAdapter(this, imenaAlbuma, listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData(){
        imenaAlbuma = new ArrayList<>();
        listHash = new HashMap<>();

        imenaAlbuma.add("BSSST... Tišinčina");
        imenaAlbuma.add("Govedina");
        imenaAlbuma.add("Svi zajedno");
        imenaAlbuma.add("Oni su");
        imenaAlbuma.add("Diskretni heroji");

        List<String> bssttis = new ArrayList<>();
        bssttis.add("Dolazi Sindikat");
        bssttis.add("Duga je ulica");

        listHash.put(imenaAlbuma.get(0), bssttis);
    }
}
