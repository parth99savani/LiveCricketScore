package com.popseven.livecricketscore;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
import com.popseven.livecricketscore.Fragment.BattingRankingFragment;
import com.popseven.livecricketscore.Fragment.TeamRankingFragment;
import com.popseven.livecricketscore.Model.Ranking.Ranking;

public class MensRankingActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtName;
    private LinearLayout ll;
    private TabLayout tabLayoutRanking;
    private ViewPager viewPagerRanking;
    private TabAdapter adapter;
    private static final String URL_DATA = "http://mapps.cricbuzz.com/cbzios/stats/rankings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_ranking);

        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtName);
        ll = findViewById(R.id.ll);
        tabLayoutRanking = findViewById(R.id.tabLayoutRanking);
        viewPagerRanking = findViewById(R.id.viewPagerRanking);

        loadData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                Ranking ranking = gson.fromJson(response, Ranking.class);

                adapter = new TabAdapter(getSupportFragmentManager());
                adapter.addFragment(new BattingRankingFragment(ranking.getPlayer().getTEST().getBatting(),ranking.getPlayer().getODI().getBatting(),ranking.getPlayer().getT20().getBatting()), "Batting");
                adapter.addFragment(new BattingRankingFragment(ranking.getPlayer().getTEST().getBowling(), ranking.getPlayer().getODI().getBowling(), ranking.getPlayer().getT20().getBowling()), "Bowling");
                adapter.addFragment(new BattingRankingFragment(ranking.getPlayer().getTEST().getAllrounder(), ranking.getPlayer().getODI().getAllrounder(), ranking.getPlayer().getT20().getAllrounder()), "All-rounder");
                adapter.addFragment(new TeamRankingFragment(ranking.getTeam().getTEST(), ranking.getTeam().getODI(), ranking.getTeam().getT20()), "Team");

                viewPagerRanking.setAdapter(adapter);
                tabLayoutRanking.setupWithViewPager(viewPagerRanking);
                tabLayoutRanking.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FEE715"));

                //adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("MensRankingActivity","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MensRankingActivity.this);
        requestQueue.add(stringRequest);
    }
}
