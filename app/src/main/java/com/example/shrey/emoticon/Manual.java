package com.example.shrey.emoticon;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.VideoView;

public class Manual extends AppCompatActivity {
    Button firstButton;
    Button secondButton;
    Button thirdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstButton = (Button) findViewById(R.id.button2);
        secondButton = (Button) findViewById(R.id.button3);
        thirdButton = (Button) findViewById(R.id.button4);
        final VideoView video = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.changemode;
        video.setVideoURI(Uri.parse(path));
       firstButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               video.start();
           }
       });

        //repeated each time for different mode tutorials, didn't have other vids so used the same one as example

        final VideoView video3 = (VideoView) findViewById(R.id.videoView);
        path = "android.resource://" + getPackageName() + "/" + R.raw.calculator;
        video.setVideoURI(Uri.parse(path));

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video3.start();
            }
        });

        final VideoView video2 = (VideoView) findViewById(R.id.videoView);
        path = "android.resource://" + getPackageName() + "/" + R.raw.shareanswers;
        video2.setVideoURI(Uri.parse(path));
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video2.start();
            }
        });
        final VideoView video4 = (VideoView) findViewById(R.id.videoView);
        path = "android.resource://" + getPackageName() + "/" + R.raw.clockclip;
        video4.setVideoURI(Uri.parse(path));
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video4.start();
            }
        });
        final VideoView video5 = (VideoView) findViewById(R.id.videoView);
        path = "android.resource://" + getPackageName() + "/" + R.raw.morseclip;
        video5.setVideoURI(Uri.parse(path));
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video5.start();
            }
        });

    }
}
