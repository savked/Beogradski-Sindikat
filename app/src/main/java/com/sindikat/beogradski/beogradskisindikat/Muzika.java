package com.sindikat.beogradski.beogradskisindikat;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Muzika extends AppCompatActivity {

    Button playButton;
    SeekBar positionBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mediaPlayer;

    int totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzika);

        playButton = (Button) findViewById(R.id.playButton);
        elapsedTimeLabel = (TextView) findViewById(R.id.timeStart);
        remainingTimeLabel = (TextView) findViewById(R.id.timeEnd);

        // Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.test);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        totalTime = mediaPlayer.getDuration();

        // Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                    positionBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Thread (Update positionBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaPlayer != null){
                    try{
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    }catch (InterruptedException e){}
                }
            }
        }).start();

        // When button is clicked, music starts playing and icon changes
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    //Stopping
                    mediaPlayer.start();
                    playButton.setBackgroundResource(R.drawable.pause);
                }
                else{
                    mediaPlayer.pause();
                    playButton.setBackgroundResource(R.drawable.play);
                }
            }
        });

    }

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int currentPosition = msg.what;
                // Update positionBar
                positionBar.setProgress(currentPosition);

                // Update Labels
                String elapsedTime = createTimeLabel(currentPosition);
                elapsedTimeLabel.setText(elapsedTime);

                String duration = createTimeLabel(mediaPlayer.getDuration());
                remainingTimeLabel.setText(duration);
            }
        };

        public String createTimeLabel(int time){
            String timeLabel = "";
            int min = time / 1000 / 60;
            int sec = time / 1000 % 60;

            timeLabel = min + ":";
            if(sec < 10) timeLabel += "0";
            timeLabel += sec;

            return timeLabel;
    }
}
