package com.sindikat.beogradski.beogradskisindikat;

import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import android.net.Uri;

import java.io.IOException;

public class Slusanje extends AppCompatActivity {

    Button playButton;
    Button repeatButton;
    Button shuffleButton;
    Button backButton;
    Button nextButton;
    SeekBar positionBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    TextView songNameTV;

    MediaPlayer mediaPlayer = new MediaPlayer();

    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzika);

        playButton = (Button) findViewById(R.id.playButton);
        repeatButton = (Button) findViewById(R.id.repeatButton);
        shuffleButton = (Button) findViewById(R.id.shuffleButton);
        backButton = (Button) findViewById(R.id.backButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        elapsedTimeLabel = (TextView) findViewById(R.id.timeStart);
        remainingTimeLabel = (TextView) findViewById(R.id.timeEnd);
        songNameTV = (TextView) findViewById(R.id.songName);

        // Media Player
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        fetchAudioUrlFromFirebase();
        mediaPlayer.seekTo(0);

        // Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);

        // When button is clicked, music starts playing and icon changes
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){

                    mediaPlayer.start();
                    playButton.setBackgroundResource(R.drawable.pause);
                }
                else{
                    mediaPlayer.pause();
                    playButton.setBackgroundResource(R.drawable.play);
                }
            }
        });

        // Handling repeat
        repeatButton.setOnClickListener(new View.OnClickListener() {
            Boolean repeatEnabled = false;
            @Override
            public void onClick(View view) {
                if(!repeatEnabled) {
                    mediaPlayer.setLooping(true);

                    repeatButton.setBackgroundResource(R.drawable.repeat);
                    repeatButton.setAlpha(1.0f);

                    repeatEnabled = true;
                }
                else{
                    mediaPlayer.setLooping(false);

                    repeatButton.setBackgroundResource(R.drawable.repeatdisabled);
                    repeatButton.setAlpha(0.5f);

                    repeatEnabled = false;
                }
            }
        });

        // Handling shuffle
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            Boolean shuffleEnabled = false;
            @Override
            public void onClick(View view) {
                if(!shuffleEnabled) {
                    // HANDLE SHUFFLING SONGS
                    shuffleButton.setBackgroundResource(R.drawable.shuffle);
                    shuffleButton.setAlpha(1.0f);

                    shuffleEnabled = true;
                }
                else{
                    shuffleButton.setBackgroundResource(R.drawable.shuffledisabled);
                    shuffleButton.setAlpha(0.5f);

                    shuffleEnabled = false;
                }
            }
        });

        // Seekbar seeks to time the user has clicked on
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

    }
    // Getting the song from Firebase Storage
    private void fetchAudioUrlFromFirebase() {

        mediaPlayer.getDuration();

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://beogradski-sindikat.appspot.com/Slusanje").child("Beogradski Sindikat - Dolazi Sindikat.mp3");

        String songName = storageRef.getName();///////////////////////////// Getting song name from the Firebase Storage
        songNameTV.setText(songName.substring(0, songName.length() -    4)); // Removing '.mp3' from the name

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer.setDataSource(url);
                    // Wait for media player to get prepare
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", e.getMessage());
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