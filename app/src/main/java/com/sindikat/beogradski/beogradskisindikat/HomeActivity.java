package com.sindikat.beogradski.beogradskisindikat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String[] stavke = {"Članovi","Muzika","Slike","Nastupi","O aplikaciji"
        };
        ListAdapter adapter = new CustomAdapter2(this, stavke);
        ListView lv = (ListView) findViewById(R.id.listViewHome);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stavka = String.valueOf(adapterView.getItemAtPosition(i));
                if(i == 0){
                    Intent it = new Intent(HomeActivity.this, ClanoviActivity.class);
                    startActivity(it);
                }
                if(i == 4){
                    Intent it = new Intent( HomeActivity.this, OAplikaciji.class);
                    startActivity(it);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidebackward2 ,R.anim.slidebackward1);
    }
}
