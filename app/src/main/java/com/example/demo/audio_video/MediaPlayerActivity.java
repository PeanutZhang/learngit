package com.example.demo.audio_video;

import android.content.ComponentName;
import android.media.AudioManager;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.demo.R;
import com.example.demo.TestJava;

public class MediaPlayerActivity extends AppCompatActivity {

    private MediaBrowserCompat mediaBrowserCompat;


    private MediaBrowserCompat.ConnectionCallback connectionCallbacks;
    private ImageView playPause;
    private MediaControllerCompat.Callback controllerCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaBrowserCompat = new MediaBrowserCompat(this, new ComponentName(this, MediaPlayerBackService.class), connectionCallbacks, null);
        controllerCallback = new MediaControllerCompat.Callback() {
            @Override
            public void onMetadataChanged(MediaMetadataCompat metadata) {
            }

            @Override
            public void onPlaybackStateChanged(PlaybackStateCompat state) {
            }
        };

        connectionCallbacks =
                new MediaBrowserCompat.ConnectionCallback() {
                    @Override
                    public void onConnected() {

                        // Get the token for the MediaSession
                        MediaSessionCompat.Token token = mediaBrowserCompat.getSessionToken();

                        // Create a MediaControllerCompat
                        MediaControllerCompat mediaController =
                                null;
                        try {
                            mediaController = new MediaControllerCompat(MediaPlayerActivity.this, // Context
                                    token);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        // Save the controller
                        MediaControllerCompat.setMediaController(MediaPlayerActivity.this, mediaController);

                        // Finish building the UI
                        //  buildTransportControls();

                    }

                    @Override
                    public void onConnectionSuspended() {
                        // The Service has crashed. Disable transport controls until it automatically reconnects
                    }

                    @Override
                    public void onConnectionFailed() {
                        // The Service has refused our connection
                    }
                };
    }

    private void buildTransportControls() {
        // Grab the view for the play/pause button
//        playPause = (ImageView) findViewById(R.id.play_pause);
//
//        // Attach a listener to the button
//        playPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Since this is a play/pause button, you'll need to test the current state
//                // and choose the action accordingly
//
//                int pbState = MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getPlaybackState().getState();
//                if (pbState == PlaybackStateCompat.STATE_PLAYING) {
//                    MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getTransportControls().pause();
//                } else {
//                    MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getTransportControls().play();
//                }
//
//            }
//
//            MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MediaPlayerActivity.this);
//
//            // Display the initial state
//            MediaMetadataCompat metadata = mediaController.getMetadata();
//            PlaybackStateCompat pbState = mediaController.getPlaybackState();

        // Register a Callback to stay in sync
//      mediaController.registerCallback(controllerCallback);

        playPause.setOnClickListener(v -> {
            // Since this is a play/pause button, you'll need to test the current state
//                // and choose the action accordingly
            int pbState = MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getPlaybackState().getState();
            if (pbState == PlaybackStateCompat.STATE_PLAYING) {
                MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getTransportControls().pause();
            } else {
                MediaControllerCompat.getMediaController(MediaPlayerActivity.this).getTransportControls().play();
            }
        });
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MediaPlayerActivity.this);

        // Display the initial state
        MediaMetadataCompat metadata = mediaController.getMetadata();
        PlaybackStateCompat pbState = mediaController.getPlaybackState();

        // Register a Callback to stay in sync
        mediaController.registerCallback(controllerCallback);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaBrowserCompat.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // (see "stay in sync with the MediaSession")
        if (MediaControllerCompat.getMediaController(MediaPlayerActivity.this) != null) {
            MediaControllerCompat.getMediaController(MediaPlayerActivity.this).unregisterCallback(controllerCallback);
        }
        mediaBrowserCompat.disconnect();
    }
}
