package com.popseven.livecricketscore.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.WTeamRankingAdapter;
import com.popseven.livecricketscore.Model.WomenTeamRanking.Team;
import com.popseven.livecricketscore.Model.WomenTeamRanking.WomenTeamRanking;
import com.popseven.livecricketscore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class WTeamRankingFragment extends Fragment {

    private ToggleSwitch toggleTeam;
    private LinearLayout ll1;
    private TextView textviewRank;
    private TextView textviewTeam;
    private TextView textviewRating;
    private TextView textviewPoints;
    private LinearLayout ll;
    private RecyclerView recyclerRankingTeam;
    private WTeamRankingAdapter adapter;
    private List<WomenTeamRanking> teamRankingList;
    private static final String URL_DATA = "https://cricketapi-icc.pulselive.com/icc-ratings/ranked/teams/wodi";
    private static final String URL_T20_DATA = "https://cricketapi-icc.pulselive.com/icc-ratings/ranked/teams/wt20i";

    public WTeamRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_team_ranking, container, false);

        toggleTeam = view.findViewById(R.id.toggleTeam);
        ll1 = view.findViewById(R.id.ll1);
        textviewRank = view.findViewById(R.id.textviewRank);
        textviewTeam = view.findViewById(R.id.textviewTeam);
        textviewRating = view.findViewById(R.id.textviewRating);
        textviewPoints = view.findViewById(R.id.textviewPoints);
        ll = view.findViewById(R.id.ll);
        recyclerRankingTeam = view.findViewById(R.id.recyclerRankingTeam);

        teamRankingList = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("ODI");
        labels.add("T20");
        toggleTeam.setLabels(labels);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recyclerRankingTeam.setHasFixedSize(true);
        recyclerRankingTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WTeamRankingAdapter(getActivity(), teamRankingList);
        recyclerRankingTeam.setAdapter(adapter);
        recyclerRankingTeam.setNestedScrollingEnabled(false);
        recyclerRankingTeam.addItemDecoration(itemDecor);

        toggleTeam.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if(position==0){
                    loadOdiData();
                }else if(position == 1) {
                    loadT20Data();
                }
            }
        });

        loadOdiData();

        return view;
    }

    private void loadOdiData() {

        teamRankingList.clear();

        JsonArrayRequest request = new JsonArrayRequest(URL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                JSONObject jsonObject1 = jsonObject.getJSONObject("team");

                                Team team = new Team(jsonObject1.getString("name"));

                                WomenTeamRanking womenTeamRanking = new WomenTeamRanking(team,jsonObject.getInt("points"),jsonObject.getInt("rating"),jsonObject.getInt("position"));

                                teamRankingList.add(womenTeamRanking);

                                adapter.notifyDataSetChanged();
                            }
                            catch(JSONException e) {

                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("WTeamRankingFragment","Error" + volleyError.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void loadT20Data() {

        teamRankingList.clear();

        JsonArrayRequest request = new JsonArrayRequest(URL_T20_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                JSONObject jsonObject1 = jsonObject.getJSONObject("team");

                                Team team = new Team(jsonObject1.getString("name"));

                                WomenTeamRanking womenTeamRanking = new WomenTeamRanking(team,jsonObject.getInt("points"),jsonObject.getInt("rating"),jsonObject.getInt("position"));

                                teamRankingList.add(womenTeamRanking);

                                adapter.notifyDataSetChanged();
                            }
                            catch(JSONException e) {

                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("WTeamRankingFragment","Error" + volleyError.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}
