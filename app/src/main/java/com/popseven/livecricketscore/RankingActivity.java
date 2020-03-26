package com.popseven.livecricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RankingActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtName;
    private LinearLayout ll;
    private ImageButton btnMensRanking;
    private ImageButton btnWomensRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtName);
        ll = findViewById(R.id.ll);
        btnMensRanking = findViewById(R.id.btnMensRanking);
        btnWomensRanking = findViewById(R.id.btnWomensRanking);

        btnMensRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RankingActivity.this, MensRankingActivity.class));
            }
        });

        btnWomensRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RankingActivity.this, WomensRankingActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
