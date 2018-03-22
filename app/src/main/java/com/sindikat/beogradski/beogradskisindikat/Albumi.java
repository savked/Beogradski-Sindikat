package com.sindikat.beogradski.beogradskisindikat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class Albumi extends AppCompatActivity {

    private GridView albumiGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albumi);

        albumiGrid = (GridView) findViewById(R.id.albumiGrid);
        albumiGrid.setAdapter(new ImageAdapterAlbumi(this));

        ImageView bsShtit5 = (ImageView) findViewById(R.id.bsShtit5);

        bsShtit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Albumi.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        albumiGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Albumi.this, Pesme.class);
                intent.putExtra("pos", i);

                startActivity(intent);
            }
        });

    }
}
