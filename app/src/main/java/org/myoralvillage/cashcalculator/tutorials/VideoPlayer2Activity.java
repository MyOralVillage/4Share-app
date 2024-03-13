package org.myoralvillage.cashcalculator.tutorials;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.myoralvillage.cashcalculator.R;

public class VideoPlayer2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        VideoView videoView =(VideoView)findViewById(R.id.videoView1);
        videoView.setZOrderOnTop(false);
        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

       // initiate a video view
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2));
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}