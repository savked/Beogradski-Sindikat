package com.sindikat.beogradski.beogradskisindikat;

import android.app.WallpaperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;

public class Slike extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slike);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if(position == 0)
                        myWallpaperManager.setResource(R.drawable.slika1);
                    else if(position == 1)
                        myWallpaperManager.setResource(R.drawable.slika2);
                    else if(position == 2)
                        myWallpaperManager.setResource(R.drawable.slika3);
                    else if(position == 3)
                        myWallpaperManager.setResource(R.drawable.slika4);

                    Toast.makeText(Slike.this, "Pozadina postavljena", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
