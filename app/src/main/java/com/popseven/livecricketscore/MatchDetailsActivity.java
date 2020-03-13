package com.popseven.livecricketscore;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

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
import com.popseven.livecricketscore.Fragment.LiveMatchFragment;
import com.popseven.livecricketscore.Fragment.PlayersFragment;
import com.popseven.livecricketscore.Fragment.ScorecardMatchFragment;
import com.popseven.livecricketscore.Model.Scorecard.Scorecard;
import com.popseven.livecricketscore.Model.Series.Match;
import com.popseven.livecricketscore.Model.Series.Series;
import com.popseven.livecricketscore.Model.TeamColor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatchDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtSeriesName;
    private LinearLayout ll;
    private Button btnTeam1;
    private TextView txtTeam1Score;
    private Button btnTeam2;
    private TextView txtTeam2Score;
    private TextView txtMatchStatus;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout rootLayout;
    private static String URL_DATA;
    private String matchId,seriesId;
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        btnBack = findViewById(R.id.btnBack);
        txtSeriesName = findViewById(R.id.txtSeriesName);
        ll = findViewById(R.id.ll);
        btnTeam1 = findViewById(R.id.btnTeam1);
        txtTeam1Score = findViewById(R.id.txtTeam1Score);
        btnTeam2 = findViewById(R.id.btnTeam2);
        txtTeam2Score = findViewById(R.id.txtTeam2Score);
        txtMatchStatus = findViewById(R.id.txtMatchStatus);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        rootLayout = findViewById(R.id.root_layout);

        colorList = teamColorList.getTeamColorList();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            matchId = bundle.getString("matchId");
            seriesId = bundle.getString("seriesId");
            loadData(matchId,seriesId);
        }

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new LiveMatchFragment(matchId), "Live");
        adapter.addFragment(new ScorecardMatchFragment(matchId,seriesId), "Scorecard");
        adapter.addFragment(new PlayersFragment(matchId), "Player");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FEE715"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void loadData(final String matchId, final String seriesId) {

        URL_DATA="http://mapps.cricbuzz.com/cbzios/series/" + seriesId + "/matches";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Series series = gson.fromJson(response, Series.class);

                for (int i=0; i<series.getMatches().size(); i++){
                    if (matchId.equals(series.getMatches().get(i).getMatchId())){
                        Match item = series.getMatches().get(i);

                        txtSeriesName.setText(item.getHeader().getMatchDesc()+" of "+item.getSeriesName());

                        if (item.getHeader().getStatus().equals("")){
                            final Calendar cal = Calendar.getInstance();
                            cal.setTimeInMillis(Long.parseLong(item.getHeader().getStartTime())*1000);
                            Date date = cal.getTime();
                            txtMatchStatus.setText(new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date));
                        }else {
                            txtMatchStatus.setText(item.getHeader().getStatus());
                        }

                        if (item.getBatTeam() == null && item.getBowTeam() == null) {
                            btnTeam1.setText(item.getTeam1().getSName());
                            for (int j = 0; j < colorList.size(); j++) {
                                if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
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
                            btnTeam2.setText(item.getTeam2().getSName());
                            for (int j = 0; j < colorList.size(); j++) {
                                if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
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
                            if (item.getBatTeam().getName().equalsIgnoreCase(item.getTeam1().getName()) || item.getBatTeam().getName().equalsIgnoreCase(item.getTeam1().getSName())) {
                                btnTeam1.setText(item.getTeam1().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
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
                                btnTeam2.setText(item.getTeam2().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
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
                            } else if (item.getBatTeam().getName().equalsIgnoreCase(item.getTeam2().getName()) || item.getBatTeam().getName().equalsIgnoreCase(item.getTeam2().getSName())) {
                                btnTeam1.setText(item.getTeam2().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                        btnTeam1.setBackground(buttonDrawable);
                                        break;
                                    } else {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                        btnTeam1.setBackground(buttonDrawable);
                                    }
                                }
                                btnTeam2.setText(item.getTeam1().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
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
                            } else if (item.getBowTeam().getName().equalsIgnoreCase(item.getTeam1().getName()) || item.getBowTeam().getName().equalsIgnoreCase(item.getTeam1().getSName())) {
                                btnTeam2.setText(item.getTeam1().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
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
                                btnTeam1.setText(item.getTeam2().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                        btnTeam1.setBackground(buttonDrawable);
                                        break;
                                    } else {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                        btnTeam1.setBackground(buttonDrawable);
                                    }
                                }
                            } else if (item.getBowTeam().getName().equalsIgnoreCase(item.getTeam2().getName()) || item.getBowTeam().getName().equalsIgnoreCase(item.getTeam2().getSName())) {
                                btnTeam2.setText(item.getTeam2().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
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
                                btnTeam1.setText(item.getTeam1().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                        btnTeam1.setBackground(buttonDrawable);
                                        break;
                                    } else {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                        btnTeam1.setBackground(buttonDrawable);
                                    }
                                }
                            }else {
                                btnTeam1.setText(item.getTeam1().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                        btnTeam1.setBackground(buttonDrawable);
                                        break;
                                    } else {
                                        Drawable buttonDrawable = btnTeam1.getBackground();
                                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorAccent));
                                        btnTeam1.setBackground(buttonDrawable);
                                    }
                                }
                                btnTeam2.setText(item.getTeam2().getSName());
                                for (int j = 0; j < colorList.size(); j++) {
                                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
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

                            if (item.getBatTeam().getInnings().size() == 1) {
                                txtTeam1Score.setText(item.getBatTeam().getInnings().get(0).getScore()+"-"+item.getBatTeam().getInnings().get(0).getWkts()+" ("+item.getBatTeam().getInnings().get(0).getOvers()+")");
                            } else if(item.getBatTeam().getInnings().size() == 2){
                                txtTeam1Score.setText(item.getBatTeam().getInnings().get(0).getScore()+" & "+item.getBatTeam().getInnings().get(1).getScore()+"-"+item.getBatTeam().getInnings().get(1).getWkts());
                            }else if(item.getBatTeam().getInnings().size() == 0){
                                txtTeam1Score.setText("");
                            }

                            if (item.getBowTeam().getInnings().size() == 1) {
                                txtTeam2Score.setText(item.getBowTeam().getInnings().get(0).getScore()+"-"+item.getBowTeam().getInnings().get(0).getWkts()+" ("+item.getBowTeam().getInnings().get(0).getOvers()+")");
                            } else if(item.getBowTeam().getInnings().size() == 2){
                                txtTeam2Score.setText(item.getBowTeam().getInnings().get(0).getScore()+" & "+item.getBowTeam().getInnings().get(1).getScore()+"-"+item.getBowTeam().getInnings().get(1).getWkts());
                            }else if(item.getBowTeam().getInnings().size() == 0){
                                txtTeam2Score.setText("");
                            }
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchDetailsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MatchDetailsActivity.this);
        requestQueue.add(stringRequest);
    }
}
