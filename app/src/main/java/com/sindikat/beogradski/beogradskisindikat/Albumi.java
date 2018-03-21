package com.sindikat.beogradski.beogradskisindikat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Albumi extends AppCompatActivity {

    private GridView albumiGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albumi);

        albumiGrid = (GridView) findViewById(R.id.albumiGrid);
        albumiGrid.setAdapter(new ImageAdapterAlbumi(this));
    }
}
