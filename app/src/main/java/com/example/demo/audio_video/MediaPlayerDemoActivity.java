package com.example.demo.audio_video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.R;

import java.io.IOException;

public class MediaPlayerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_demo);
        MediaPlayer mediaPlayer;
        Button play = findViewById(R.id.play);
        Button stop = findViewById(R.id.stop);
        //1.   local file
//        MediaPlayer player  = MediaPlayer.create(this,R.raw.gezhinv);


        // 2. romote file
        String musicurl = "http://127.0.0.1:8080/gezhinv.mp3";

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(musicurl);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                Toast.makeText(this,"准备好了",Toast.LENGTH_SHORT).show();
                Log.e("zyh","ispreared");
            });
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("zyh","mediaplayer error  "+e.getLocalizedMessage());
        }




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        stop.setOnClickListener(v->{
            mediaPlayer.stop();
        });
    }
}
