package com.sindikat.beogradski.beogradskisindikat.ui.home;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sindikat.beogradski.beogradskisindikat.ui.muzika.Albumi;
import com.sindikat.beogradski.beogradskisindikat.ui.clanovi.ClanoviActivity;
import com.sindikat.beogradski.beogradskisindikat.ui.oaplikaciji.OAplikaciji;
import com.sindikat.beogradski.beogradskisindikat.R;
import com.sindikat.beogradski.beogradskisindikat.ui.slike.SlikeActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        String[] stavke = {"Članovi","Muzika","SlikeActivity","Nastupi","O aplikaciji"
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
                    // PROMENITI ANIMACIJU
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                }
                if(i == 1){
                    Intent it = new Intent(HomeActivity.this, Albumi.class);
                    startActivity(it);
                }
                if(i == 2){
                    Intent it = new Intent( HomeActivity.this, SlikeActivity.class);
                    startActivity(it);
                }
                if(i == 4){
                    Intent it = new Intent( HomeActivity.this, OAplikaciji.class);
                    startActivity(it);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
