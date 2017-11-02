package com.sindikat.beogradski.beogradskisindikat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ClanoviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanovi);

        String[] clanovi = {"Feđa Dimović","Ognjen Janković", "Boško Ćirković", "Aleksandar Karađinović",
                "Marko Đurić", "Darko Marjanović","Đorđe Jovanović"
        };
        ListAdapter adapter = new CustomAdapter(this, clanovi);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidebackward2 ,R.anim.slidebackward1);
    }
}
