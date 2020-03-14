package com.popseven.livecricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.popseven.livecricketscore.Adapter.SeriesAdapter;
import com.popseven.livecricketscore.Adapter.SeriesTypeAdapter;
import com.popseven.livecricketscore.Model.Schedule.Schedule;
import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.Model.Schedule.Tab;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class ScheduleActivity extends AppCompatActivity implements SeriesTypeAdapter.SeriesTypeAdapterListener, SeriesAdapter.SeriesAdapterListener {

    private ImageButton mBtnBack;
    private RecyclerView mRecyclerViewSeriesType;
    private RecyclerView mRecyclerViewSeriesList;
    private static final String URL_DATA = "http://mapps.cricbuzz.com/cbzios/match/schedule";
    private List<Tab> tabList;
    private List<Series> seriesList;
    private List<Series> allSeriesList;
    private SeriesTypeAdapter seriesTypeAdapter;
    private SeriesAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mBtnBack = findViewById(R.id.btnBack);
        mRecyclerViewSeriesType = findViewById(R.id.recyclerViewSeriesType);
        mRecyclerViewSeriesList = findViewById(R.id.recyclerViewSeriesList);

        tabList = new ArrayList<>();
        allSeriesList = new ArrayList<>();
        seriesList = new ArrayList<>();

        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);

        seriesTypeAdapter = new SeriesTypeAdapter(this,tabList,this);
        mRecyclerViewSeriesType.setHasFixedSize(true);
        mRecyclerViewSeriesType.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewSeriesType.setAdapter(seriesTypeAdapter);

        seriesAdapter = new SeriesAdapter(this,seriesList,this);
        mRecyclerViewSeriesList.setHasFixedSize(true);
        mRecyclerViewSeriesList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewSeriesList.setAdapter(seriesAdapter);
        mRecyclerViewSeriesList.addItemDecoration(itemDecor);

        loadSchedule();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadSchedule() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                tabList.clear();
                allSeriesList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Schedule schedule = gson.fromJson(response, Schedule.class);

                tabList.addAll(schedule.getTabs());
                allSeriesList.addAll(schedule.getSeries());

                seriesTypeAdapter.notifyDataSetChanged();

                seriesList.clear();
                loaderies(0);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ScheduleActivity","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ScheduleActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onSeriesTypeSelected(int tabId) {
        seriesList.clear();
        loaderies(tabId);
    }

    private void loaderies(int tabId) {
        seriesList.clear();
        for (int i=0; i<allSeriesList.size(); i++){
            if(allSeriesList.get(i).getTabs().contains(tabId)){
                seriesList.add(allSeriesList.get(i));
            }
        }
        seriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSeriesSelected(String seriesId, String seriesName) {
        Intent intent = new Intent(ScheduleActivity.this,SeriesScheduleActivity.class);
        intent.putExtra("SeriesId",seriesId);
        intent.putExtra("SeriesName",seriesName);
        startActivity(intent);
    }
}
