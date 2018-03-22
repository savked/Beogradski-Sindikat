package com.sindikat.beogradski.beogradskisindikat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Pesme extends AppCompatActivity {

    private ListView listView;

    private String link[];

    private String imenaPesama1[];
    private String imenaPesama2[];
    private String imenaPesama3[];
    private String imenaPesama4[];
    private String imenaPesama5[];
    private String imenaPesama6[];

    private int albumPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesme);

        listView = (ListView) findViewById(R.id.pesmeList);

        // Pressing Logo - Home button
        ImageView bsShtit6 = (ImageView) findViewById(R.id.bsShtit6);

        bsShtit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pesme.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imenaPesama1 = getResources().getStringArray(R.array.imenaPesama1);
        imenaPesama2 = getResources().getStringArray(R.array.imenaPesama2);
        imenaPesama3 = getResources().getStringArray(R.array.imenaPesama3);
        imenaPesama4 = getResources().getStringArray(R.array.imenaPesama4);
        imenaPesama5 = getResources().getStringArray(R.array.imenaPesama5);
        imenaPesama6 = getResources().getStringArray(R.array.imenaPesama6);

        Bundle extras = getIntent().getExtras();
        albumPos = extras.getInt("pos");


        if(albumPos == 0) {
            ListAdapter adapter1 = new ListAdapterPesme(this, imenaPesama1);
            listView.setAdapter(adapter1);
        }
        else if(albumPos == 1){
            ListAdapter adapter2 = new ListAdapterPesme(this, imenaPesama2);
            listView.setAdapter(adapter2);
        }
        else if(albumPos == 2){
            ListAdapter adapter3 = new ListAdapterPesme(this, imenaPesama3);
            listView.setAdapter(adapter3);
        }
        else if(albumPos == 3){
            ListAdapter adapter4 = new ListAdapterPesme(this, imenaPesama4);
            listView.setAdapter(adapter4);
        }
        else if(albumPos == 4){
            ListAdapter adapter5 = new ListAdapterPesme(this, imenaPesama5);
            listView.setAdapter(adapter5);
        }
        else if(albumPos == 5){
            ListAdapter adapter6 = new ListAdapterPesme(this, imenaPesama6);
            listView.setAdapter(adapter6);
        }

        link = getResources().getStringArray(R.array.link);
    }
}
