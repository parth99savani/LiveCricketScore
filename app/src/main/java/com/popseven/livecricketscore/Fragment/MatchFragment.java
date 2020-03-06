package com.popseven.livecricketscore.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import com.popseven.livecricketscore.Adapter.PastMatchAdapter;
import com.popseven.livecricketscore.Adapter.PastSeriesAdapter;
import com.popseven.livecricketscore.Adapter.UpcomingSeriesAdapter;
import com.popseven.livecricketscore.Adapter.UpcomingMatchAdapter;
import com.popseven.livecricketscore.MatchScoreActivity;
import com.popseven.livecricketscore.Model.LiveMatches.Livematches;
import com.popseven.livecricketscore.Model.Schedule.Match;
import com.popseven.livecricketscore.Model.Schedule.Schedule;
import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.Model.Schedule.Team;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {

    private ToggleSwitch toggleMatch;
    private RecyclerView recyclerViewUpcomingPastMatches;
    private ProgressBar progressBar;
    private static final String URL_DATA = "http://mapps.cricbuzz.com/cbzios/match/schedule";
    private static final String URL_MATCH = "http://mapps.cricbuzz.com/cbzios/match/livematches";
    private List<Match> matchList;
    private List<Team> teams;
    private List<Series> series;
    private UpcomingSeriesAdapter adapter;
    private List<String> seriesIdList;
    private ImageButton btnMoveTop;
    private LinearLayoutManager linearLayoutManager;
    private PastSeriesAdapter pastSeriesAdapter;
    private List<com.popseven.livecricketscore.Model.LiveMatches.Match> liveMatchList;

    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_match, container, false);

        toggleMatch = view.findViewById(R.id.toggleMatch);
        recyclerViewUpcomingPastMatches = view.findViewById(R.id.recyclerViewUpcomingPastMatches);
        progressBar = view.findViewById(R.id.progressBar);
        btnMoveTop = view.findViewById(R.id.btnMoveTop);

        matchList = new ArrayList<>();
        teams = new ArrayList<>();
        series = new ArrayList<>();
        seriesIdList = new ArrayList<>();
        liveMatchList = new ArrayList<>();

        recyclerViewUpcomingPastMatches.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewUpcomingPastMatches.setLayoutManager(linearLayoutManager);

        progressBar.setVisibility(View.VISIBLE);

        loadUpcomingMatch();

        toggleMatch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if (position == 0) {
                    matchList.clear();
                    seriesIdList.clear();
                    loadUpcomingMatch();
                } else if (position == 1) {
                    matchList.clear();
                    seriesIdList.clear();
                    loadMatch();
                }
            }
        });

        recyclerViewUpcomingPastMatches.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                recyclerViewUpcomingPastMatches.smoothScrollToPosition(0);
            }
        });

        return view;
    }

    private void loadMatch() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MATCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Livematches livematches = gson.fromJson(response, Livematches.class);

                liveMatchList.clear();

                for (int i = 0; i < livematches.getMatches().size(); i++) {
                    final long TWELVE_HOURS = 1000 * 60 * 5;
                    long currentTime = System.currentTimeMillis();
                    long matchEndTime = Long.parseLong(livematches.getMatches().get(i).getHeader().getEndTime());
                    long matchStartTime = Long.parseLong(livematches.getMatches().get(i).getHeader().getStartTime());
                    long differ = currentTime - (matchEndTime * 1000);
                    long differ1 = (matchStartTime * 1000) - currentTime;

                    if (differ > TWELVE_HOURS) {
                        liveMatchList.add(livematches.getMatches().get(i));
                    }
                }

                loadPastMatch();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadUpcomingMatch() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Schedule schedule = gson.fromJson(response, Schedule.class);

                matchList.clear();

                seriesIdList.clear();

                for (int i = 0; i < schedule.getMatches().size(); i++) {
                    final long TWELVE_HOURS = 1000 * 60 * 60 * 6;
                    long currentTime = System.currentTimeMillis();
                    //long matchEndTime = Long.parseLong(schedule.getMatches().get(i).getEndTime());
                    long matchStartTime = Long.parseLong(schedule.getMatches().get(i).getStartTime());
                    //long differ = currentTime - (matchEndTime*1000);
                    long differ = (matchStartTime * 1000) - currentTime;

                    if (differ > TWELVE_HOURS) {
                        matchList.add(schedule.getMatches().get(i));
                    }
                }

                teams.addAll(schedule.getTeams());
                series.addAll(schedule.getSeries());

                for (int i = 0; i < matchList.size(); i++) {
                    if (!seriesIdList.contains(matchList.get(i).getSeriesId())) {
                        seriesIdList.add(matchList.get(i).getSeriesId());
                    }
                }

                //matchList.addAll(schedule.getMatches());

                adapter = new UpcomingSeriesAdapter(getActivity(), seriesIdList, matchList, teams, series, new UpcomingMatchAdapter.UpcomingMatchAdapterListener() {
                    @Override
                    public void onUpcomingMatchSelected() {
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Match is not started", Snackbar.LENGTH_SHORT);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
                        TextView textView = snackbarView.findViewById(R.id.snackbar_text);
                        textView.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        snackbar.show();
                    }
                });

                recyclerViewUpcomingPastMatches.setAdapter(adapter);

                adapter.notifyDataSetChanged();

//                adapter = new LiveMatchAdapter(getActivity(), livematches.getMatches());
//                recyclerViewLiveMatches.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadPastMatch() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Schedule schedule = gson.fromJson(response, Schedule.class);

                matchList.clear();

                seriesIdList.clear();

//                for(int i=0; i<schedule.getMatches().size(); i++){
//                    final long TWELVE_HOURS = 1000 * 60 * 60 * 6;
//                    long currentTime = System.currentTimeMillis();
//                    //long matchEndTime = Long.parseLong(schedule.getMatches().get(i).getEndTime());
//                    long matchStartTime = Long.parseLong(schedule.getMatches().get(i).getStartTime());
//                    //long differ = currentTime - (matchEndTime*1000);
//                    long differ = (matchStartTime * 1000) - currentTime;
//
//                    if (differ < 0) {
//                        matchList.add(schedule.getMatches().get(i));
//                    }
//                }

                //loadMatch();

                teams.addAll(schedule.getTeams());
                series.addAll(schedule.getSeries());

                for (int i = 0; i < liveMatchList.size(); i++) {
                    if (!seriesIdList.contains(liveMatchList.get(i).getSeriesId())) {
                        seriesIdList.add(liveMatchList.get(i).getSeriesId());
                    }
                }

                //matchList.addAll(livematches.getMatches());

                pastSeriesAdapter = new PastSeriesAdapter(getActivity(), seriesIdList, liveMatchList, teams, series, new PastMatchAdapter.PastMatchAdapterListener() {
                    @Override
                    public void onPastMatchSelected(String matchId) {
                        Intent intent = new Intent(getActivity(), MatchScoreActivity.class);
                        intent.putExtra("matchId", matchId);
                        startActivity(intent);
                    }
                });

                recyclerViewUpcomingPastMatches.setAdapter(pastSeriesAdapter);

                pastSeriesAdapter.notifyDataSetChanged();

//                adapter = new LiveMatchAdapter(getActivity(), livematches.getMatches());
//                recyclerViewLiveMatches.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
