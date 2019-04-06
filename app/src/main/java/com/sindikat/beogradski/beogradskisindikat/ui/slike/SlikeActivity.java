package com.sindikat.beogradski.beogradskisindikat.ui.slike;

import android.app.WallpaperManager;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sindikat.beogradski.beogradskisindikat.R;

import java.io.IOException;

public class SlikeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slike);
    }
}