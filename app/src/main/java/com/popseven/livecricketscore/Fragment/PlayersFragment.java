package com.popseven.livecricketscore.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.popseven.livecricketscore.Adapter.PlayerAdapter;
import com.popseven.livecricketscore.Model.MatchDetail.MatchDetail;
import com.popseven.livecricketscore.Model.MatchDetail.Player;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {

    private static String URL_MATCH_DETAILS;
    private ToggleSwitch togglePlayers;
    private LinearLayout ll1;
    private TextView textEleven;
    private RecyclerView recycleplayingEleven;
    private TextView textBench;
    private RecyclerView recycleBench;
    private PlayerAdapter adapter, adapter2;
    private List<Integer> playerIdList = new ArrayList<>();
    private List<Integer> playerIdList2 = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();
    private String matchId;
    private RelativeLayout rlPlayers;

    public PlayersFragment(String matchId) {
        this.matchId = matchId;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        togglePlayers = view.findViewById(R.id.togglePlayers);
        ll1 = view.findViewById(R.id.ll1);
        textEleven = view.findViewById(R.id.textEleven);
        recycleplayingEleven = view.findViewById(R.id.recycleplayingEleven);
        textBench = view.findViewById(R.id.textBench);
        recycleBench = view.findViewById(R.id.recycleBench);
        rlPlayers = view.findViewById(R.id.rlPlayers);

        playerIdList = new ArrayList<>();
        playerIdList2 = new ArrayList<>();
        playerList = new ArrayList<>();

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recycleplayingEleven.setHasFixedSize(true);
        recycleplayingEleven.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PlayerAdapter(getActivity(), playerIdList, playerList);
        recycleplayingEleven.setAdapter(adapter);
        recycleplayingEleven.setNestedScrollingEnabled(false);
        recycleplayingEleven.addItemDecoration(itemDecor);

        recycleBench.setHasFixedSize(true);
        recycleBench.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter2 = new PlayerAdapter(getActivity(), playerIdList2, playerList);
        recycleBench.setAdapter(adapter2);
        recycleBench.setNestedScrollingEnabled(false);
        recycleBench.addItemDecoration(itemDecor);

        loadMatchDetails();

        return view;
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
                    rlPlayers.setVisibility(View.GONE);
                } else {
                    rlPlayers.setVisibility(View.VISIBLE);
                    playerList.addAll(matchDetail.getPlayers());
                }

                if (matchDetail.getTeam1().getSName() == null) {
                    rlPlayers.setVisibility(View.GONE);
                } else {
                    if (matchDetail.getTeam2().getSName() == null) {
                        rlPlayers.setVisibility(View.GONE);
                    } else {
                        ArrayList<String> labels = new ArrayList<>();
                        labels.add(matchDetail.getTeam1().getSName());
                        labels.add(matchDetail.getTeam2().getSName());
                        togglePlayers.setLabels(labels);
                        rlPlayers.setVisibility(View.VISIBLE);
                        loadPlayerTeam1();

                        togglePlayers.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){

                            @Override
                            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                                if(position==0){
                                    loadPlayerTeam1();
                                }else if(position == 1) {
                                    loadPlayerTeam2();
                                }
                            }
                        });

                    }
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PlayersFragment","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void loadPlayerTeam1() {
        URL_MATCH_DETAILS = "http://mapps.cricbuzz.com/cbzios/match/" + matchId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MATCH_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                playerIdList.clear();
                playerIdList2.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                MatchDetail matchDetail = gson.fromJson(response, MatchDetail.class);

                if (matchDetail.getTeam1().getSquad().size() == 0) {
                    textEleven.setVisibility(View.GONE);
                    recycleplayingEleven.setVisibility(View.GONE);
                    if (matchDetail.getTeam1().getSquadBench().size() == 0) {
                        textBench.setVisibility(View.GONE);
                        recycleBench.setVisibility(View.GONE);
                        togglePlayers.setVisibility(View.GONE);
                    } else {
                        textBench.setText("Players");
                        playerIdList2.addAll(matchDetail.getTeam1().getSquadBench());
                        textBench.setVisibility(View.VISIBLE);
                        recycleBench.setVisibility(View.VISIBLE);
                        togglePlayers.setVisibility(View.VISIBLE);
                    }
                } else {
                    playerIdList.addAll(matchDetail.getTeam1().getSquad());
                    textEleven.setVisibility(View.VISIBLE);
                    recycleplayingEleven.setVisibility(View.VISIBLE);
                    togglePlayers.setVisibility(View.VISIBLE);
                    if (matchDetail.getTeam1().getSquadBench().size() == 0) {
                        textBench.setVisibility(View.GONE);
                        recycleBench.setVisibility(View.GONE);
                    } else {
                        textBench.setText("Bench");
                        playerIdList2.addAll(matchDetail.getTeam1().getSquadBench());
                        textBench.setVisibility(View.VISIBLE);
                        recycleBench.setVisibility(View.VISIBLE);
                    }
                }

                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PlayersFragment","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadPlayerTeam2() {
        URL_MATCH_DETAILS = "http://mapps.cricbuzz.com/cbzios/match/" + matchId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_MATCH_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                playerIdList.clear();
                playerIdList2.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                MatchDetail matchDetail = gson.fromJson(response, MatchDetail.class);

                if (matchDetail.getTeam2().getSquad().size() == 0) {
                    textEleven.setVisibility(View.GONE);
                    recycleplayingEleven.setVisibility(View.GONE);
                    if (matchDetail.getTeam2().getSquadBench().size() == 0) {
                        textBench.setVisibility(View.GONE);
                        recycleBench.setVisibility(View.GONE);
                        togglePlayers.setVisibility(View.GONE);
                    } else {
                        textBench.setText("Players");
                        playerIdList2.addAll(matchDetail.getTeam2().getSquadBench());
                        textBench.setVisibility(View.VISIBLE);
                        recycleBench.setVisibility(View.VISIBLE);
                        togglePlayers.setVisibility(View.VISIBLE);
                    }
                } else {
                    playerIdList.addAll(matchDetail.getTeam2().getSquad());
                    textEleven.setVisibility(View.VISIBLE);
                    recycleplayingEleven.setVisibility(View.VISIBLE);
                    togglePlayers.setVisibility(View.VISIBLE);
                    if (matchDetail.getTeam2().getSquadBench().size() == 0) {
                        textBench.setVisibility(View.GONE);
                        recycleBench.setVisibility(View.GONE);
                    } else {
                        textBench.setText("Bench");
                        playerIdList2.addAll(matchDetail.getTeam2().getSquadBench());
                        textBench.setVisibility(View.VISIBLE);
                        recycleBench.setVisibility(View.VISIBLE);
                    }
                }

                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PlayersFragment","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
