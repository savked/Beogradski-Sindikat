package com.sindikat.beogradski.beogradskisindikat;

import android.content.Intent;
import android.content.res.Resources;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;

public class Slusanje extends AppCompatActivity {

    Button playButton;
    Button repeatButton;
    Button shuffleButton;
    Button previousButton;
    Button nextButton;
    SeekBar positionBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    TextView songNameTV;
    ImageView songImage;

    private Handler handler = new Handler();

    MediaPlayer mediaPlayer = new MediaPlayer();

    StorageReference storageRef;
    FirebaseStorage storage;

    int totalTime;

    String songName = "";
    String nextSongName = "";
    String previousSongName = "";

    private Boolean shuffleEnabled = false;
    private Boolean repeatEnabled = false;

    public String link[];

    int position;

    Integer songimage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzika);

         link = getResources().getStringArray(R.array.link);

        playButton = (Button) findViewById(R.id.playButton);
        repeatButton = (Button) findViewById(R.id.repeatButton);
        shuffleButton = (Button) findViewById(R.id.shuffleButton);
        previousButton = (Button) findViewById(R.id.backButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        elapsedTimeLabel = (TextView) findViewById(R.id.timeStart);
        remainingTimeLabel = (TextView) findViewById(R.id.timeEnd);
        songNameTV = (TextView) findViewById(R.id.songName);
        songImage = (ImageView) findViewById(R.id.songImage);
        positionBar = (SeekBar) findViewById(R.id.positionBar);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        // Getting the song name from Muzika class
        Bundle extras = getIntent().getExtras();

        if(extras == null){
            songName = null;
        } else {
            songName = extras.getString("songname");
            songimage = extras.getInt("songimage");
            songImage.setImageResource(songimage);

            position = extras.getInt("position");

            songNameTV.setText(songName);
        }

        fetchfromFirebase();


        // Next button clicked
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.reset();

                playButton.setBackgroundResource(R.drawable.play);

                position += 1;

                nextSongName = link[position].substring(44, link[position].length() - 4);

                songNameTV.setText(nextSongName);
                getAlbumSliku();

                fetchfromFirebase();
            }
        });

        // Previous button clicked
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.reset();

                playButton.setBackgroundResource(R.drawable.play);

                position -= 1;

                nextSongName = link[position].substring(44, link[position].length() - 4);

                songNameTV.setText(nextSongName);
                getAlbumSliku();

                fetchfromFirebase();
            }
        });

        // Media Player`
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // Handling repeat
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!repeatEnabled) {
                    mediaPlayer.setLooping(true);

                    repeatButton.setBackgroundResource(R.drawable.repeat);
                    repeatButton.setAlpha(1.0f);

                    repeatEnabled = true;
                    shuffleEnabled = false;
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

        // Shuffle button function
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!shuffleEnabled) {
                    shuffleButton.setBackgroundResource(R.drawable.shuffle);
                    shuffleButton.setAlpha(1.0f);

                    shuffleEnabled = true;
                    repeatEnabled = false;
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
                mediaPlayer.setOnPreparedListener(this);
                totalTime = mediaPlayer.getDuration();
                positionBar.setMax(totalTime / 1000);

                String duration = createTimeLabel(totalTime / 1000);
                remainingTimeLabel.setText(duration);

                mediaPlayer.start();
                playButton.setBackgroundResource(R.drawable.pause);

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

                // Handling next songs
                if(shuffleEnabled){
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.reset();

                            position = (int)(Math.random() * 68);
                            System.out.println(position);

                            nextSongName = link[position].substring(44, link[position].length() - 4);

                            songNameTV.setText(nextSongName);
                            getAlbumSliku();

                            fetchfromFirebase();
                        }
                    });
                } else {
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();

                            position += 1;

                            nextSongName = link[position].substring(44, link[position].length() - 4);

                            songNameTV.setText(nextSongName);
                            getAlbumSliku();

                            fetchfromFirebase();
                        }
                    });
                }
            }
        });

        // Updating positionBar (SeekBar)
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

        // If clicked, going to the time clicked
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

    public void fetchfromFirebase(){
        // Create a storage reference from our app
        storageRef = storage.getReferenceFromUrl(link[position]);

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
                    Toast.makeText(Slusanje.this, "Učitavanje", Toast.LENGTH_SHORT).show();

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

    public void getAlbumSliku(){
        if(position >= 0 && position <= 16) songImage.setImageResource(R.drawable.album1);
        else if(position >= 17 && position <= 22) songImage.setImageResource(R.drawable.album2);
        else if(position >= 23 && position <= 43) songImage.setImageResource(R.drawable.album3);
        else if(position >= 44 && position <= 47) songImage.setImageResource(R.drawable.album4);
        else if(position >= 48 && position <= 64) songImage.setImageResource(R.drawable.album5);
        else if(position >= 65 && position <= 68) songImage.setImageResource(R.drawable.album6);
    }
}