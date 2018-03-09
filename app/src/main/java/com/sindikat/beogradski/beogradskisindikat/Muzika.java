package com.sindikat.beogradski.beogradskisindikat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
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

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(Muzika.this, Slusanje.class);

                intent.putExtra("songname", listHash.get(imenaAlbuma.get(i)).get(i1));
                startActivity(intent);

                return false;
            }
        });

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
        bssttis.add("Buđenje");
        bssttis.add("Dolazi Sindikat");
        bssttis.add("Duga je ulica");
        bssttis.add("Divljina");
        bssttis.add("Slatke male maloletnice");
        bssttis.add("Z.DZ.T.S. (Iz budzeta miks)");
        bssttis.add("Samo jedan život");
        bssttis.add("Škabo maestro");
        bssttis.add("Znaj");
        bssttis.add("Na bojnom polju (Viteška 2)");
        bssttis.add("Zalazak trećeg sunca");
        bssttis.add("Gorila u magli");
        bssttis.add("BS!BS!");
        bssttis.add("Plejersko ponašanje");
        bssttis.add("Misli o moći");
        bssttis.add("Nek nas ne zaborave");
        bssttis.add("Ko iz automata");

        List<String> gvd = new ArrayList<>();
        gvd.add("Govedina (Svi zajedno)");
        gvd.add("Goveđi mentalitet");
        gvd.add("Goveda repuje");
        gvd.add("Duga je muzika");
        gvd.add("Himna");
        gvd.add("Politička");

        List<String> svizaj = new ArrayList<>();
        svizaj.add("Krećemo u napad");
        svizaj.add("Ja u životu imama sve");
        svizaj.add("Zajedno");
        svizaj.add("SBS");
        svizaj.add("Deo prošlosti");
        svizaj.add("Ljiga");
        svizaj.add("Poroci Beograda");
        svizaj.add("Život je prevara");
        svizaj.add("Stotka");
        svizaj.add("Alal vera");
        svizaj.add("Samo za BGD");
        svizaj.add("Niko ne može da zna");
        svizaj.add("Od sranja se ne beži");
        svizaj.add("Jednostavnost i suština");
        svizaj.add("Pravo kroz vetar");
        svizaj.add("Povratak");
        svizaj.add("Mi smo ta ekipa");
        svizaj.add("Jebem li ti mater");
        svizaj.add("Živina");
        svizaj.add("Ale ale");
        svizaj.add("Govor");
        svizaj.add("Da se zna");
        svizaj.add("Samo jedan dan");

        List<String> oni = new ArrayList<>();
        oni.add("Oni su");
        oni.add("Pretorijanska garda");
        oni.add("Već viđeno");
        oni.add("Metalna prašina");
        oni.add("Mi smo ta ekipa");

        List<String> diskretni = new ArrayList<>();
        diskretni.add("Početak");
        diskretni.add("Za sve moje ljude");
        diskretni.add("Svim srcem");
        diskretni.add("Čovek");
        diskretni.add("Gala-socijala");
        diskretni.add("Glavom u zid");
        diskretni.add("Zajebi");
        diskretni.add("Tu sam ja");
        diskretni.add("Pazi, pazi");
        diskretni.add("Iskustvo");
        diskretni.add("Osveta");
        diskretni.add("Svedok (saradnik)");
        diskretni.add("Welcome to Srbija");
        diskretni.add("Olovni vojnici");
        diskretni.add("Nema povlačenja, nema predaje");
        diskretni.add("Balada disidenta");
        diskretni.add("Još ovu noć");

        listHash.put(imenaAlbuma.get(0), bssttis);
        listHash.put(imenaAlbuma.get(1), gvd);
        listHash.put(imenaAlbuma.get(2), svizaj);
        listHash.put(imenaAlbuma.get(3), oni);
        listHash.put(imenaAlbuma.get(4), diskretni);
    }
}
