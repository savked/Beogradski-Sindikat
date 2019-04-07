package com.sindikat.beogradski.beogradskisindikat.ui.main;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sindikat.beogradski.beogradskisindikat.R;
import com.sindikat.beogradski.beogradskisindikat.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(it);
                overridePendingTransition( R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });
    }
}
