package com.sindikat.beogradski.beogradskisindikat;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.net.Uri;
import android.widget.Toast;

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
    ImageView songImage;

    private Handler handler = new Handler();

    MediaPlayer mediaPlayer = new MediaPlayer();

    int totalTime;
    String songName = "";
    Integer songimage = 0;

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
        songImage = (ImageView) findViewById(R.id.songImage);
        positionBar = (SeekBar) findViewById(R.id.positionBar);

        // Getting the song name from Muzika class
        Bundle extras = getIntent().getExtras();

        if(extras == null){
            songName = null;
        } else {
            songName = extras.getString("songname");
            songimage = extras.getInt("songimage");

            songNameTV.setText(songName);
            songImage.setImageResource(songimage);
        }

        // Getting the song from Firebase
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://beogradski-sindikat.appspot.com/Muzika").child(songName + ".mp3");

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    String url = uri.toString();
                    // Setting the Data Source to URL
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



        // Media Player`
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

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
                    Toast.makeText(Slusanje.this, "Ponavljanje uključeno", Toast.LENGTH_SHORT).show();
                }
                else{
                    mediaPlayer.setLooping(false);

                    repeatButton.setBackgroundResource(R.drawable.repeatdisabled);
                    repeatButton.setAlpha(0.5f);

                    repeatEnabled = false;
                    Toast.makeText(Slusanje.this, "Ponavljanje isključeno", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Slusanje.this, "Shuffle uključen", Toast.LENGTH_SHORT).show();
                }
                else{
                    shuffleButton.setBackgroundResource(R.drawable.shuffledisabled);
                    shuffleButton.setAlpha(0.5f);

                    shuffleEnabled = false;
                    Toast.makeText(Slusanje.this, "Shuffle isključen", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When Media Player is prepared
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mediaPlayer) {
                totalTime = mediaPlayer.getDuration();
                positionBar.setMax(totalTime / 1000);

                String duration = createTimeLabel(totalTime / 1000);
                remainingTimeLabel.setText(duration);

                Toast.makeText(Slusanje.this, "Pesma učitana", Toast.LENGTH_SHORT).show();

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
            }
        });

        Slusanje.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    positionBar.setProgress(currentPosition);
                    // Update Labels
                    String elapsedTime = createTimeLabel(currentPosition);
                    elapsedTimeLabel.setText(elapsedTime);
                }
                handler.postDelayed(this, 1000);
            }
        });

        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer != null && b){
                    mediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time / 60;
        int sec = time % 60;

        timeLabel = min + ":";
        if(sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }
}