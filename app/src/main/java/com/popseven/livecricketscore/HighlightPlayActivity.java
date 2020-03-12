package com.popseven.livecricketscore;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class HighlightPlayActivity extends AppCompatActivity {

    private VideoView myVideoView;
    private String url;
    private String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight_play);

        myVideoView = findViewById(R.id.myVideoView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            videoId = bundle.getString("videoId");
        }

        MediaController mc = new MediaController(this);
        myVideoView.setMediaController(mc);
        url= "https://secure.brightcove.com/services/mobile/streaming/index/master.m3u8?videoId="+videoId+"&pubId=3588749423001&secure=true";

        myVideoView.setVideoURI(Uri.parse(url));

    }
}
