package com.popseven.livecricketscore;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.popseven.livecricketscore.Adapter.PointTableGroupAdapter;
import com.popseven.livecricketscore.Model.PointTable.GroupA;
import com.popseven.livecricketscore.Model.PointTable.GroupB;
import com.popseven.livecricketscore.Model.PointTable.GroupC;
import com.popseven.livecricketscore.Model.PointTable.GroupD;
import com.popseven.livecricketscore.Model.PointTable.PointTable;
import com.popseven.livecricketscore.Model.PointTable.Team;
import com.popseven.livecricketscore.Model.PointTableList.PointTableList;
import com.popseven.livecricketscore.Model.PointTableList.Series;
import com.popseven.livecricketscore.Model.Schedule.Schedule;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PointTableActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialSpinner spinnerSeriesType;
    private TextView textviewTeamName;
    private TextView textviewPoint;
    private TextView textviewWin;
    private TextView textviewLoss;
    private TextView textviewNotResult;
    private TextView textviewPoints;
    private TextView textviewNetRunRet;
    private LinearLayout linearScoreBowlerItem;
    private RecyclerView recyclePointTable;
    private static final String URL_DATA = "https://mapps.cricbuzz.com/cbzios/pointtable";
    private static final String URL_TEAMS = "http://mapps.cricbuzz.com/cbzios/match/schedule";
    private List<Series> seriesList;
    private ArrayList<String> seriesNameList;
    private static String URL_TABLE;
    private PointTableGroupAdapter adapter;
    private List<Team> teamList;
    private List<GroupD> groupDList;
    private List<GroupC> groupCList;
    private List<GroupB> groupBList;
    private List<GroupA> groupAList;
    private List<String> orderList;
    private List<com.popseven.livecricketscore.Model.Schedule.Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_table);

        btnBack = findViewById(R.id.btnBack);
        spinnerSeriesType = findViewById(R.id.spinnerSeriesType);
        textviewTeamName = findViewById(R.id.textviewTeamName);
        textviewPoint = findViewById(R.id.textviewPoint);
        textviewWin = findViewById(R.id.textviewWin);
        textviewLoss = findViewById(R.id.textviewLoss);
        textviewNotResult = findViewById(R.id.textviewNotResult);
        textviewPoints = findViewById(R.id.textviewPoints);
        textviewNetRunRet = findViewById(R.id.textviewNetRunRet);
        linearScoreBowlerItem = findViewById(R.id.linearScoreBowlerItem);
        recyclePointTable = findViewById(R.id.recyclePointTable);

        seriesList = new ArrayList<>();
        seriesNameList = new ArrayList<>();
        teamList = new ArrayList<>();
        groupDList = new ArrayList<>();
        groupCList = new ArrayList<>();
        groupBList = new ArrayList<>();
        groupAList = new ArrayList<>();
        orderList = new ArrayList<>();
        teams = new ArrayList<>();

        adapter = new PointTableGroupAdapter(this,orderList,teams,teamList,groupDList,groupCList,groupBList,groupAList);

        recyclePointTable.setHasFixedSize(true);

        recyclePointTable.setLayoutManager(new LinearLayoutManager(this));

        recyclePointTable.setAdapter(adapter);

        loadTeams();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadTeams() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_TEAMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Schedule schedule = gson.fromJson(response, Schedule.class);

                teams.clear();

                teams.addAll(schedule.getTeams());

                loadPointTableList();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PointTableActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(PointTableActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadPointTableList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                seriesList.clear();

                seriesNameList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                PointTableList pointTableList = gson.fromJson(response, PointTableList.class);

                seriesList.addAll(pointTableList.getSeries());

                for (int i=0; i<seriesList.size(); i++){
                    seriesNameList.add(seriesList.get(i).getName());
                }

                spinnerSeriesType.setItems(seriesNameList);
                loadPointTable(seriesList.get(0).getId());
                spinnerSeriesType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        loadPointTable(seriesList.get(position).getId());
                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PointTableActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(PointTableActivity.this);
        requestQueue.add(stringRequest);
    }

    private void loadPointTable(String seriesId) {

        URL_TABLE = "https://mapps.cricbuzz.com/cbzios/pointtable/"+seriesId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_TABLE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                teamList.clear();
                groupDList.clear();
                groupCList.clear();
                groupBList.clear();
                groupAList.clear();
                orderList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                PointTable pointTable = gson.fromJson(response, PointTable.class);

                orderList.addAll(pointTable.getOrder());

                if(pointTable.getGroup().getTeams()!=null){
                    teamList.addAll(pointTable.getGroup().getTeams());
                }
                if (pointTable.getGroup().getGroupD()!=null){
                    groupDList.addAll(pointTable.getGroup().getGroupD());
                }
                if (pointTable.getGroup().getGroupC()!=null){
                    groupCList.addAll(pointTable.getGroup().getGroupC());
                }
                if (pointTable.getGroup().getGroupB()!=null){
                    groupBList.addAll(pointTable.getGroup().getGroupB());
                }
                if (pointTable.getGroup().getGroupA()!=null){
                    groupAList.addAll(pointTable.getGroup().getGroupA());
                }

                adapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PointTableActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(PointTableActivity.this);
        requestQueue.add(stringRequest);
    }
}
