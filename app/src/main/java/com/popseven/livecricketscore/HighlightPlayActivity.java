package com.popseven.livecricketscore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.HighlightAdapter;
import com.popseven.livecricketscore.Model.Highlights.Content;
import com.popseven.livecricketscore.Model.Highlights.Highlights;

import java.util.ArrayList;
import java.util.List;

public class HighlightPlayActivity extends AppCompatActivity {

    private String url;
    private String videoId, title;
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private ImageView fullscreenButton;
    private boolean fullscreen = false;
    private ImageButton btnBack;
    private TextView txtVideoName;
    private TextView txtInternational;
    private RecyclerView recyclerViewHighlights;
    private TextView txtIpl;
    private RecyclerView recyclerViewHighlightsIpl;
    private ProgressBar progressBar;
    private HighlightAdapter adapter, adapter2;
    private List<Content> highlightList;
    private List<Content> highlightIplList;
    private static final String URL_IPL = "http://api.platform.iplt20.com/content/ipl/VIDEO/en?page=0&pageSize=20&tagNames=indian-premier-league&tagNames=highlights&detail=DETAILED";
    private static final String URL_DATA = "https://api.platform.bcci.tv/content/bcci/video/EN/?tagNames=highlights&pageSize=20&page=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight_play);

        playerView = findViewById(R.id.exoplayer);
        btnBack = findViewById(R.id.btnBack);
        txtVideoName = findViewById(R.id.txtVideoName);
        txtInternational = findViewById(R.id.txtInternational);
        recyclerViewHighlights = findViewById(R.id.recyclerViewHighlights);
        txtIpl = findViewById(R.id.txtIpl);
        recyclerViewHighlightsIpl = findViewById(R.id.recyclerViewHighlightsIpl);
        progressBar = findViewById(R.id.progressBar);
        fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_icon);

        progressBar.setVisibility(View.VISIBLE);
        txtInternational.setVisibility(View.GONE);
        txtIpl.setVisibility(View.GONE);

        highlightList = new ArrayList<>();
        highlightIplList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            videoId = bundle.getString("videoId");
            title = bundle.getString("title");
            url = "https://secure.brightcove.com/services/mobile/streaming/index/master.m3u8?videoId=" + videoId + "&pubId=3588749423001&secure=true";
            txtVideoName.setText(title);
        }

        adapter = new HighlightAdapter(this, highlightList, new HighlightAdapter.HighlightAdapterListener() {
            @Override
            public void onHighlightSelected(String mediaId, String title) {
                Intent intent = new Intent(HighlightPlayActivity.this, HighlightPlayActivity.class);
                intent.putExtra("videoId", mediaId);
                intent.putExtra("title", title);
                startActivity(intent);
                finish();
            }
        });

        recyclerViewHighlights.setHasFixedSize(true);
        recyclerViewHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHighlights.setAdapter(adapter);
        recyclerViewHighlights.setNestedScrollingEnabled(false);


        adapter2 = new HighlightAdapter(this, highlightIplList, new HighlightAdapter.HighlightAdapterListener() {
            @Override
            public void onHighlightSelected(String mediaId, String title) {
                Intent intent = new Intent(HighlightPlayActivity.this, HighlightPlayActivity.class);
                intent.putExtra("videoId", mediaId);
                intent.putExtra("title", title);
                startActivity(intent);
                finish();
            }
        });

        recyclerViewHighlightsIpl.setHasFixedSize(true);
        recyclerViewHighlightsIpl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHighlightsIpl.setAdapter(adapter2);
        recyclerViewHighlightsIpl.setNestedScrollingEnabled(false);

        loadData();
        loadDataIpl();

        playerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    if (btnBack.getVisibility() == View.VISIBLE) {
                        btnBack.setVisibility(View.GONE);
                        playerView.hideController();
                    } else {
                        btnBack.setVisibility(View.VISIBLE);
                        playerView.showController();
                    }
                    return true;
                }
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(HighlightPlayActivity.this, R.drawable.ic_fullscreen_black_24dp));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().show();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullscreen = false;
                } else {
                    finish();
                }
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            fullscreenButton.setImageDrawable(ContextCompat.getDrawable(HighlightPlayActivity.this, R.drawable.ic_fullscreen_black_24dp));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
            playerView.setLayoutParams(params);
            fullscreen = false;
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            fullscreenButton.setImageDrawable(ContextCompat.getDrawable(HighlightPlayActivity.this, R.drawable.ic_fullscreen_exit_black_24dp));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = params.MATCH_PARENT;
            playerView.setLayoutParams(params);
            fullscreen = true;
        }



        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(HighlightPlayActivity.this, R.drawable.ic_fullscreen_black_24dp));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().show();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(HighlightPlayActivity.this, R.drawable.ic_fullscreen_exit_black_24dp));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().hide();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);
                    fullscreen = true;
                }
            }
        });

    }

    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                highlightList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Highlights highlights = gson.fromJson(response, Highlights.class);

                for (int i=0; i<highlights.getContent().size(); i++){
                    if(!videoId.equals(highlights.getContent().get(i).getMediaId())){
                        highlightList.add(highlights.getContent().get(i));
                    }
                }

                adapter.notifyDataSetChanged();
                txtInternational.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HighlightPlayActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(HighlightPlayActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadDataIpl() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_IPL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                highlightIplList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Highlights highlights = gson.fromJson(response, Highlights.class);

                for (int i=0; i<highlights.getContent().size(); i++){
                    if(!videoId.equals(highlights.getContent().get(i).getMediaId())){
                        highlightIplList.add(highlights.getContent().get(i));
                    }
                }

                adapter2.notifyDataSetChanged();
                txtIpl.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HighlightPlayActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(HighlightPlayActivity.this);
        requestQueue.add(stringRequest);
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(url);
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, getResources().getString(R.string.app_name)));
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(hlsMediaSource, false, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }
}
