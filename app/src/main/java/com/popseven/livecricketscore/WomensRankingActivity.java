package com.popseven.livecricketscore;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.popseven.livecricketscore.Adapter.TabAdapter;
import com.popseven.livecricketscore.Fragment.WAllRounderRankingFragment;
import com.popseven.livecricketscore.Fragment.WBattingRankingFragment;
import com.popseven.livecricketscore.Fragment.WBowlingRankingFragment;
import com.popseven.livecricketscore.Fragment.WTeamRankingFragment;

public class WomensRankingActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtName;
    private LinearLayout ll;
    private TabLayout tabLayoutRanking;
    private ViewPager viewPagerRanking;
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_ranking);

        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtName);
        ll = findViewById(R.id.ll);
        tabLayoutRanking = findViewById(R.id.tabLayoutRanking);
        viewPagerRanking = findViewById(R.id.viewPagerRanking);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new WBattingRankingFragment(), "Batting");
        adapter.addFragment(new WBowlingRankingFragment(), "Bowling");
        adapter.addFragment(new WAllRounderRankingFragment(), "All-rounder");
        adapter.addFragment(new WTeamRankingFragment(), "Team");

        viewPagerRanking.setAdapter(adapter);
        tabLayoutRanking.setupWithViewPager(viewPagerRanking);
        tabLayoutRanking.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FEE715"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
