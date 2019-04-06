package com.sindikat.beogradski.beogradskisindikat.ui.clanovi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sindikat.beogradski.beogradskisindikat.R;
import com.sindikat.beogradski.beogradskisindikat.ui.home.HomeActivity;

public class ClanoviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanovi);

        ImageView bsShtit2 = (ImageView) findViewById(R.id.bsShtit2);

        bsShtit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClanoviActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

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
