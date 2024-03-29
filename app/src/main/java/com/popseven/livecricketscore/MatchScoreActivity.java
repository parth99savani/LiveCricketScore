package com.popseven.livecricketscore;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.TabAdapter;
import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Fragment.LiveFragment;
import com.popseven.livecricketscore.Fragment.PlayersFragment;
import com.popseven.livecricketscore.Fragment.ScorecardFragment;
import com.popseven.livecricketscore.Model.LiveMatches.Livematches;
import com.popseven.livecricketscore.Model.LiveMatches.Match;
import com.popseven.livecricketscore.Model.TeamColor;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

public class MatchScoreActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtSeriesName;
    private Button btnTeam1;
    private TextView txtTeam1Score;
    private Button btnTeam2;
    private TextView txtTeam2Score;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;
    private String matchId;
    private static String URL_DATA = "http://mapps.cricbuzz.com/cbzios/match/livematches";
    private TextView txtMatchStatus;
    private Handler handler = new Handler();
    private int apiDelayed = 5000; //5 seconds
    private Runnable runnable;
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();

//    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
//    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
//
//    View rootLayout;
//
//    private int revealX;
//    private int revealY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_match_score);

        btnBack = findViewById(R.id.btnBack);
        txtSeriesName = findViewById(R.id.txtSeriesName);
        btnTeam1 = findViewById(R.id.btnTeam1);
        txtTeam1Score = findViewById(R.id.txtTeam1Score);
        btnTeam2 = findViewById(R.id.btnTeam2);
        txtTeam2Score = findViewById(R.id.txtTeam2Score);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        txtMatchStatus = findViewById(R.id.txtMatchStatus);

        colorList = teamColorList.getTeamColorList();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            matchId = bundle.getString("matchId");
            loadData();

        }

//        rootLayout = findViewById(R.id.root_layout);
//
//        final Intent intent = getIntent();
//
//        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
//                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) && intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
//            rootLayout.setVisibility(View.INVISIBLE);
//
//            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
//            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
//
//            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
//            if (viewTreeObserver.isAlive()) {
//                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        revealActivity(revealX, revealY);
//                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    }
//                });
//            }
//        } else {
//            rootLayout.setVisibility(View.VISIBLE);
//        }

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new LiveFragment(matchId), "Live");
        adapter.addFragment(new ScorecardFragment(matchId), "Scorecard");
        adapter.addFragment(new PlayersFragment(matchId), "Player");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FEE715"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

//    protected void revealActivity(int x, int y) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
//
//            // create the animator for this view (the start radius is zero)
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
//            circularReveal.setDuration(600);
//            circularReveal.setInterpolator(new AccelerateInterpolator());
//
//            // make the view visible and start the animation
//            rootLayout.setVisibility(View.VISIBLE);
//            circularReveal.start();
//        } else {
//            finish();
//        }
//    }

//    protected void unRevealActivity() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            finish();
//        } else {
//            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(
//                    rootLayout, revealX, revealY, finalRadius, 0);
//
//            circularReveal.setDuration(600);
//            circularReveal.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    rootLayout.setVisibility(View.INVISIBLE);
//                    finish();
//                }
//            });
//
//
//            circularReveal.start();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                //do your function;
                //progressBar.setVisibility(View.VISIBLE);
                loadData();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed); // so basically after your getHeroes(), from next time it will be 5 sec repeated
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }

    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Livematches livematches = gson.fromJson(response, Livematches.class);

                Match match = null;

                for (int i = 0; i < livematches.getMatches().size(); i++) {
                    if (livematches.getMatches().get(i).getMatchId().equals(matchId)) {
                        match = livematches.getMatches().get(i);
                    }
                }

                if(match==null){

                    loadMatchDetail();

                }else {

                    txtSeriesName.setText(match.getHeader().getMatchDesc() + " of " + match.getSeriesName());

                    txtMatchStatus.setText(match.getHeader().getStatus());

                    if (match.getBatTeam() == null && match.getBowTeam() == null) {
                        btnTeam1.setText(match.getTeam1().getSName());
                        for(int j=0; j<colorList.size(); j++ ){
                            if(match.getTeam1().getId().equals(colorList.get(j).getId())){
                                Drawable buttonDrawable = btnTeam1.getBackground();
                                buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                btnTeam1.setBackground(buttonDrawable);
                                break;
                            }else {
                                Drawable buttonDrawable = btnTeam1.getBackground();
                                buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                btnTeam1.setBackground(buttonDrawable);
                            }
                        }
                        btnTeam2.setText(match.getTeam2().getSName());
                        for(int j=0; j<colorList.size(); j++ ){
                            if(match.getTeam2().getId().equals(colorList.get(j).getId())){
                                Drawable buttonDrawable = btnTeam2.getBackground();
                                buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                btnTeam2.setBackground(buttonDrawable);
                                break;
                            }else {
                                Drawable buttonDrawable = btnTeam2.getBackground();
                                buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorGreen));
                                btnTeam2.setBackground(buttonDrawable);
                            }
                        }
                        txtTeam1Score.setVisibility(View.GONE);
                        txtTeam2Score.setVisibility(View.GONE);
                    } else {
                        txtTeam1Score.setVisibility(View.VISIBLE);
                        txtTeam2Score.setVisibility(View.VISIBLE);
                        if (match.getBatTeam().getId().equals(match.getTeam1().getId())) {
                            btnTeam1.setText(match.getTeam1().getSName());
                            for(int j=0; j<colorList.size(); j++ ){
                                if(match.getTeam1().getId().equals(colorList.get(j).getId())){
                                    Drawable buttonDrawable = btnTeam1.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                    btnTeam1.setBackground(buttonDrawable);
                                    break;
                                }else {
                                    Drawable buttonDrawable = btnTeam1.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                    btnTeam1.setBackground(buttonDrawable);
                                }
                            }
                        } else if (match.getBatTeam().getId().equals(match.getTeam2().getId())) {
                            btnTeam1.setText(match.getTeam2().getSName());
                            for(int j=0; j<colorList.size(); j++ ){
                                if(match.getTeam2().getId().equals(colorList.get(j).getId())){
                                    Drawable buttonDrawable = btnTeam1.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                    btnTeam1.setBackground(buttonDrawable);
                                    break;
                                }else {
                                    Drawable buttonDrawable = btnTeam1.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                    btnTeam1.setBackground(buttonDrawable);
                                }
                            }
                        }
                        if (match.getBatTeam().getInnings().size() == 1) {
                            txtTeam1Score.setText(match.getBatTeam().getInnings().get(0).getScore() + "-" + match.getBatTeam().getInnings().get(0).getWkts() + " (" + match.getBatTeam().getInnings().get(0).getOvers() + ")");
                        } else if (match.getBatTeam().getInnings().size() == 2) {
                            txtTeam1Score.setText(match.getBatTeam().getInnings().get(0).getScore() + " & " + match.getBatTeam().getInnings().get(1).getScore() + "-" + match.getBatTeam().getInnings().get(1).getWkts()+ " (" + match.getBatTeam().getInnings().get(1).getOvers() + ")");
                        } else if (match.getBatTeam().getInnings().size() == 0) {
                            txtTeam1Score.setText("");
                        }

                        if (match.getBowTeam().getId().equals(match.getTeam1().getId())) {
                            btnTeam2.setText(match.getTeam1().getSName());
                            for(int j=0; j<colorList.size(); j++ ){
                                if(match.getTeam1().getId().equals(colorList.get(j).getId())){
                                    Drawable buttonDrawable = btnTeam2.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                    btnTeam2.setBackground(buttonDrawable);
                                    break;
                                }else {
                                    Drawable buttonDrawable = btnTeam2.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorGreen));
                                    btnTeam2.setBackground(buttonDrawable);
                                }
                            }
                        } else if (match.getBowTeam().getId().equals(match.getTeam2().getId())) {
                            btnTeam2.setText(match.getTeam2().getSName());
                            for(int j=0; j<colorList.size(); j++ ){
                                if(match.getTeam2().getId().equals(colorList.get(j).getId())){
                                    Drawable buttonDrawable = btnTeam2.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                    btnTeam2.setBackground(buttonDrawable);
                                    break;
                                }else {
                                    Drawable buttonDrawable = btnTeam2.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorGreen));
                                    btnTeam2.setBackground(buttonDrawable);
                                }
                            }
                        }

                        if (match.getBowTeam().getInnings().size() == 1) {
                            txtTeam2Score.setText(match.getBowTeam().getInnings().get(0).getScore() + "-" + match.getBowTeam().getInnings().get(0).getWkts() + " (" + match.getBowTeam().getInnings().get(0).getOvers() + ")");
                        } else if (match.getBowTeam().getInnings().size() == 2) {
                            txtTeam2Score.setText(match.getBowTeam().getInnings().get(0).getScore() + " & " + match.getBowTeam().getInnings().get(1).getScore() + "-" + match.getBowTeam().getInnings().get(1).getWkts()+ " (" + match.getBowTeam().getInnings().get(1).getOvers() + ")");
                        } else if (match.getBowTeam().getInnings().size() == 0) {
                            txtTeam2Score.setText("");
                        }

                        txtTeam2Score.setTextColor(getResources().getColor(R.color.colorTextLightBlack));
                    }


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MatchScoreActivity","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MatchScoreActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadMatchDetail() {

    }

    @Override
    public void onBackPressed() {
        finish();
        //unRevealActivity();
        super.onBackPressed();
    }
}
