package com.popseven.livecricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.ScheduleAdapter;
import com.popseven.livecricketscore.Model.Series.Match;
import com.popseven.livecricketscore.Model.Series.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesScheduleActivity extends AppCompatActivity implements ScheduleAdapter.ScheduleAdapterListener {

    private ImageButton btnBack;
    private TextView txtSeriesName;
    private RecyclerView recyclerViewSeriesSchedule;
    private String seriesId, seriesName;
    private static String URL_DATA;
    private List<Match> matchList;
    private ScheduleAdapter adapter;
    private ProgressBar progressBar;
    private ImageButton btnMoveTop;
    private LinearLayoutManager linearLayoutManager;
    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_schedule);

        btnBack = findViewById(R.id.btnBack);
        txtSeriesName = findViewById(R.id.txtSeriesName);
        recyclerViewSeriesSchedule = findViewById(R.id.recyclerViewSeriesSchedule);
        progressBar = findViewById(R.id.progressBar);
        btnMoveTop = findViewById(R.id.btnMoveTop);

        view1 = findViewById(R.id.rl);

        matchList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            seriesId = bundle.getString("SeriesId");
            seriesName = bundle.getString("SeriesName");
            txtSeriesName.setText(seriesName);
            loadData(seriesId);
        }

        adapter = new ScheduleAdapter(this, matchList, this);
        recyclerViewSeriesSchedule.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSeriesSchedule.setLayoutManager(linearLayoutManager);
        recyclerViewSeriesSchedule.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerViewSeriesSchedule.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibility = (linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) ? View.VISIBLE : View.GONE;
                btnMoveTop.setVisibility(visibility);
            }
        });

        btnMoveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewSeriesSchedule.smoothScrollToPosition(0);
            }
        });

    }

    private void loadData(String seriesId) {

        URL_DATA = "http://mapps.cricbuzz.com/cbzios/series/" + seriesId + "/matches";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                matchList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Series series = gson.fromJson(response, Series.class);

                matchList.addAll(series.getMatches());

                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("SeriesScheduleActivity","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SeriesScheduleActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onScheduleSelected(String matchId, String seriesId) {
        Intent intent = new Intent(SeriesScheduleActivity.this, MatchDetailsActivity.class);
        intent.putExtra("matchId", matchId);
        intent.putExtra("seriesId", seriesId);
        startActivity(intent);
    }
}
