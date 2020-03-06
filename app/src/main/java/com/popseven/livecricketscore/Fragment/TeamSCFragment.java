package com.popseven.livecricketscore.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.SCBatsmanAdapter;
import com.popseven.livecricketscore.Adapter.SCBowlerAdapter;
import com.popseven.livecricketscore.Model.MatchDetail.MatchDetail;
import com.popseven.livecricketscore.Model.MatchDetail.Player;
import com.popseven.livecricketscore.Model.Scorecard.Batsman;
import com.popseven.livecricketscore.Model.Scorecard.Bowler;
import com.popseven.livecricketscore.Model.Scorecard.Inning;
import com.popseven.livecricketscore.Model.Scorecard.Scorecard;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamSCFragment extends Fragment {

    private static String URL_MATCH_DETAILS;
    private static String URL_SCORECARD;
    private TextView textviewBatsman;
    private TextView textviewRun;
    private TextView textviewBowl;
    private TextView textviewFour;
    private TextView textviewSix;
    private TextView textviewStrikeRate;
    private LinearLayout relative;
    private RecyclerView recyclebatsman;
    private TextView textviewExtras;
    private TextView textviewExtrasRun;
    private TextView textviewTotal;
    private TextView txtTeamScore;
    private TextView textviewNextBat;
    private TextView textviewNextBatsman;
    private TextView textviewBowler;
    private TextView textviewOver;
    private TextView textviewMedan;
    private TextView textviewRunn;
    private TextView textviewWicket;
    private TextView textviewEconomicrate;
    private LinearLayout relative3;
    private RecyclerView recyclebowler;
    private TextView txtInning;
    private LinearLayout llInning1;
    private TextView txtInning2;
    private TextView textviewBatsman2;
    private TextView textviewRun2;
    private TextView textviewBowl2;
    private TextView textviewFour2;
    private TextView textviewSix2;
    private TextView textviewStrikeRate2;
    private LinearLayout relative2;
    private RecyclerView recyclebatsman2;
    private TextView textviewExtras2;
    private TextView textviewExtrasRun2;
    private TextView textviewTotal2;
    private TextView txtTeamScore2;
    private TextView textviewNextBat2;
    private TextView textviewNextBatsman2;
    private TextView textviewBowler2;
    private TextView textviewOver2;
    private TextView textviewMedan2;
    private TextView textviewRunn2;
    private TextView textviewWicket2;
    private TextView textviewEconomicrate2;
    private LinearLayout relative4;
    private RecyclerView recyclebowler2;
    private LinearLayout llInning2;
    private List<Player> playerList;
    private List<Batsman> batsmanListTeam1In1;
    private List<Batsman> batsmanListTeam1In2;
    private List<Bowler> bowlerListTeam1In1;
    private List<Bowler> bowlerListTeam1In2;
    private SCBatsmanAdapter adapter, adapter2;
    private SCBowlerAdapter adapter3, adapter4;
    private String teamId;
    private List<Inning> team1InningList;
    private String matchId;
    private NestedScrollView scrollView;
    private Handler handler = new Handler();
    private int apiDelayed = 5000; //5 seconds
    private Runnable runnable;

    public TeamSCFragment(String matchId, String teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_sc, container, false);


        txtInning = view.findViewById(R.id.txtInning);
        textviewBatsman = view.findViewById(R.id.textviewBatsman);
        textviewRun = view.findViewById(R.id.textviewRun);
        textviewBowl = view.findViewById(R.id.textviewBowl);
        textviewFour = view.findViewById(R.id.textviewFour);
        textviewSix = view.findViewById(R.id.textviewSix);
        textviewStrikeRate = view.findViewById(R.id.textviewStrikeRate);
        relative = view.findViewById(R.id.relative);
        recyclebatsman = view.findViewById(R.id.recyclebatsman);
        textviewExtras = view.findViewById(R.id.textviewExtras);
        textviewExtrasRun = view.findViewById(R.id.textviewExtrasRun);
        textviewTotal = view.findViewById(R.id.textviewTotal);
        txtTeamScore = view.findViewById(R.id.txtTeamScore);
        textviewNextBat = view.findViewById(R.id.textviewNextBat);
        textviewNextBatsman = view.findViewById(R.id.textviewNextBatsman);
        textviewBowler = view.findViewById(R.id.textviewBowler);
        textviewOver = view.findViewById(R.id.textviewOver);
        textviewMedan = view.findViewById(R.id.textviewMedan);
        textviewRunn = view.findViewById(R.id.textviewRunn);
        textviewWicket = view.findViewById(R.id.textviewWicket);
        textviewEconomicrate = view.findViewById(R.id.textviewEconomicrate);
        relative3 = view.findViewById(R.id.relative3);
        recyclebowler = view.findViewById(R.id.recyclebowler);
        llInning1 = view.findViewById(R.id.llInning1);
        txtInning2 = view.findViewById(R.id.txtInning2);
        textviewBatsman2 = view.findViewById(R.id.textviewBatsman2);
        textviewRun2 = view.findViewById(R.id.textviewRun2);
        textviewBowl2 = view.findViewById(R.id.textviewBowl2);
        textviewFour2 = view.findViewById(R.id.textviewFour2);
        textviewSix2 = view.findViewById(R.id.textviewSix2);
        textviewStrikeRate2 = view.findViewById(R.id.textviewStrikeRate2);
        relative2 = view.findViewById(R.id.relative2);
        recyclebatsman2 = view.findViewById(R.id.recyclebatsman2);
        textviewExtras2 = view.findViewById(R.id.textviewExtras2);
        textviewExtrasRun2 = view.findViewById(R.id.textviewExtrasRun2);
        textviewTotal2 = view.findViewById(R.id.textviewTotal2);
        txtTeamScore2 = view.findViewById(R.id.txtTeamScore2);
        textviewNextBat2 = view.findViewById(R.id.textviewNextBat2);
        textviewNextBatsman2 = view.findViewById(R.id.textviewNextBatsman2);
        textviewBowler2 = view.findViewById(R.id.textviewBowler2);
        textviewOver2 = view.findViewById(R.id.textviewOver2);
        textviewMedan2 = view.findViewById(R.id.textviewMedan2);
        textviewRunn2 = view.findViewById(R.id.textviewRunn2);
        textviewWicket2 = view.findViewById(R.id.textviewWicket2);
        textviewEconomicrate2 = view.findViewById(R.id.textviewEconomicrate2);
        relative4 = view.findViewById(R.id.relative4);
        recyclebowler2 = view.findViewById(R.id.recyclebowler2);
        llInning2 = view.findViewById(R.id.llInning2);
        scrollView = view.findViewById(R.id.scrollView);

        playerList = new ArrayList<>();
        batsmanListTeam1In1 = new ArrayList<>();
        batsmanListTeam1In2 = new ArrayList<>();
        bowlerListTeam1In1 = new ArrayList<>();
        bowlerListTeam1In2 = new ArrayList<>();
        team1InningList = new ArrayList<>();

        recyclebatsman.setHasFixedSize(true);
        recyclebatsman.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SCBatsmanAdapter(getActivity(), batsmanListTeam1In1, playerList);
        recyclebatsman.setAdapter(adapter);
        recyclebatsman.setNestedScrollingEnabled(false);

        recyclebatsman2.setHasFixedSize(true);
        recyclebatsman2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter2 = new SCBatsmanAdapter(getActivity(), batsmanListTeam1In2, playerList);
        recyclebatsman2.setAdapter(adapter2);
        recyclebatsman2.setNestedScrollingEnabled(false);

        recyclebowler.setHasFixedSize(true);
        recyclebowler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter3 = new SCBowlerAdapter(getActivity(), bowlerListTeam1In1, playerList);
        recyclebowler.setAdapter(adapter3);
        recyclebowler.setNestedScrollingEnabled(false);

        recyclebowler2.setHasFixedSize(true);
        recyclebowler2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter4 = new SCBowlerAdapter(getActivity(), bowlerListTeam1In2, playerList);
        recyclebowler2.setAdapter(adapter4);
        recyclebowler2.setNestedScrollingEnabled(false);

        loadMatchDetails();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {

                loadMatchDetails();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed); // so basically after your getHeroes(), from next time it will be 5 sec repeated
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }

    private void loadMatchDetails() {
        URL_MATCH_DETAILS = "http://mapps.cricbuzz.com/cbzios/match/" + matchId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MATCH_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                playerList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                MatchDetail matchDetail = gson.fromJson(response, MatchDetail.class);

                if (matchDetail.getPlayers() == null) {

                } else {
                    playerList.addAll(matchDetail.getPlayers());

                    loadScorecard();
                }

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

    private void loadScorecard() {
        URL_SCORECARD = "http://mapps.cricbuzz.com/cbzios/match/" + matchId + "/scorecard";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_SCORECARD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                batsmanListTeam1In1.clear();
                batsmanListTeam1In2.clear();
                bowlerListTeam1In1.clear();
                bowlerListTeam1In2.clear();
                team1InningList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                //pass response
                Scorecard scorecard = gson.fromJson(response, Scorecard.class);

                if (scorecard.getInnings().size() > 0) {

                    for (int i = 0; i < scorecard.getInnings().size(); i++) {
                        if (teamId.equals(scorecard.getInnings().get(i).getBatTeamId())) {
                            team1InningList.add(scorecard.getInnings().get(i));
                        }
                    }

                    if (team1InningList.size() == 1) {
                        llInning2.setVisibility(View.GONE);
                        txtInning.setVisibility(View.GONE);
                        txtInning2.setVisibility(View.GONE);

                        batsmanListTeam1In1.addAll(team1InningList.get(0).getBatsmen());
                        textviewExtrasRun.setText(team1InningList.get(0).getExtras().getT());
                        txtTeamScore.setText(team1InningList.get(0).getScore() + "-" + team1InningList.get(0).getWkts() + " (" + team1InningList.get(0).getOvr() + ")");

                        if (team1InningList.get(0).getNextBatsman() == null) {

                        } else {
                            String[] elements = team1InningList.get(0).getNextBatsman().split(",");

                            List<String> nextBatsmanList = Arrays.asList(elements);

                            List<String> nextBatNameList = new ArrayList<>();

                            for (int i = 0; i < nextBatsmanList.size(); i++) {
                                for (int j = 0; j < playerList.size(); j++) {
                                    if (nextBatsmanList.get(i).equals(playerList.get(j).getId())) {
                                        nextBatNameList.add(playerList.get(j).getName());
                                    }
                                }
                            }

                            String nextBatsman = nextBatNameList.toString().replace("[", "").replace("]", "");

                            textviewNextBatsman.setText(nextBatsman);
                        }

                        adapter.notifyDataSetChanged();

                        bowlerListTeam1In1.addAll(team1InningList.get(0).getBowlers());
                        adapter3.notifyDataSetChanged();

                    } else if (team1InningList.size() == 2) {
                        batsmanListTeam1In1.addAll(team1InningList.get(0).getBatsmen());
                        textviewExtrasRun.setText(team1InningList.get(0).getExtras().getT());
                        txtTeamScore.setText(team1InningList.get(0).getScore() + "-" + team1InningList.get(0).getWkts() + " (" + team1InningList.get(0).getOvr() + ")");

                        if (team1InningList.get(0).getNextBatsman() == null) {

                        } else {
                            String[] elements = team1InningList.get(0).getNextBatsman().split(",");

                            List<String> nextBatsmanList = Arrays.asList(elements);

                            List<String> nextBatNameList = new ArrayList<>();

                            for (int i = 0; i < nextBatsmanList.size(); i++) {
                                for (int j = 0; j < playerList.size(); j++) {
                                    if (nextBatsmanList.get(i).equals(playerList.get(j).getId())) {
                                        nextBatNameList.add(playerList.get(j).getName());
                                    }
                                }
                            }

                            String nextBatsman = nextBatNameList.toString().replace("[", "").replace("]", "");

                            textviewNextBatsman.setText(nextBatsman);
                        }

                        adapter.notifyDataSetChanged();

                        bowlerListTeam1In1.addAll(team1InningList.get(0).getBowlers());
                        adapter3.notifyDataSetChanged();


                        batsmanListTeam1In2.addAll(team1InningList.get(1).getBatsmen());
                        textviewExtrasRun2.setText(team1InningList.get(1).getExtras().getT());
                        txtTeamScore2.setText(team1InningList.get(1).getScore() + "-" + team1InningList.get(1).getWkts() + " (" + team1InningList.get(1).getOvr() + ")");
                        if (team1InningList.get(1).getNextBatsman() == null) {

                        } else {

                            String[] elements = team1InningList.get(1).getNextBatsman().split(",");

                            List<String> nextBatsmanList = Arrays.asList(elements);

                            List<String> nextBatNameList = new ArrayList<>();

                            for (int i = 0; i < nextBatsmanList.size(); i++) {
                                for (int j = 0; j < playerList.size(); j++) {
                                    if (nextBatsmanList.get(i).equals(playerList.get(j).getId())) {
                                        nextBatNameList.add(playerList.get(j).getName());
                                    }
                                }
                            }

                            String nextBatsman = nextBatNameList.toString().replace("[", "").replace("]", "");

                            textviewNextBatsman2.setText(nextBatsman);
                        }

                        adapter2.notifyDataSetChanged();

                        bowlerListTeam1In2.addAll(team1InningList.get(1).getBowlers());
                        adapter4.notifyDataSetChanged();

                        llInning2.setVisibility(View.VISIBLE);
                        txtInning.setVisibility(View.VISIBLE);
                        txtInning2.setVisibility(View.VISIBLE);

                    }else if(team1InningList.size() == 0){
                        scrollView.setVisibility(View.GONE);
                    }

                    scrollView.setVisibility(View.VISIBLE);

                } else {
                    scrollView.setVisibility(View.GONE);
                }


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
