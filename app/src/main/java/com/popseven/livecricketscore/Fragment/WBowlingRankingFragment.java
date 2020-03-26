package com.popseven.livecricketscore.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.WPlayerRankingAdapter;
import com.popseven.livecricketscore.Model.WomenPlayerRanking.Content;
import com.popseven.livecricketscore.Model.WomenPlayerRanking.WomenPlayerRanking;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class WBowlingRankingFragment extends Fragment {

    private ToggleSwitch togglePlayersRank;
    private LinearLayout ll1;
    private TextView textviewRank;
    private TextView textviewPlayer;
    private TextView textviewRating;
    private LinearLayout ll;
    private RecyclerView recyclerRanking;
    private WPlayerRankingAdapter adapter;
    private List<Content> playerList;
    private static final String URL_DATA = "https://cricketapi-icc.pulselive.com/icc-ratings/ranked/players/wodi/bowl?page=0&pageSize=20";
    private static final String URL_T20_DATA = "https://cricketapi-icc.pulselive.com/icc-ratings/ranked/players/wt20i/bowl?page=0&pageSize=20";

    public WBowlingRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_bowling_ranking, container, false);

        togglePlayersRank = view.findViewById(R.id.togglePlayersRank);
        ll1 = view.findViewById(R.id.ll1);
        textviewRank = view.findViewById(R.id.textviewRank);
        textviewPlayer = view.findViewById(R.id.textviewPlayer);
        textviewRating = view.findViewById(R.id.textviewRating);
        ll = view.findViewById(R.id.ll);
        recyclerRanking = view.findViewById(R.id.recyclerRanking);

        playerList = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("ODI");
        labels.add("T20");
        togglePlayersRank.setLabels(labels);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recyclerRanking.setHasFixedSize(true);
        recyclerRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WPlayerRankingAdapter(getActivity(), playerList);
        recyclerRanking.setAdapter(adapter);
        recyclerRanking.setNestedScrollingEnabled(false);
        recyclerRanking.addItemDecoration(itemDecor);

        togglePlayersRank.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                playerList.clear();

                //progressDialog.dismiss();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                WomenPlayerRanking ranking = gson.fromJson(response, WomenPlayerRanking.class);

                playerList.addAll(ranking.getContent());

                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("WBowlingRankingFragment","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadT20Data() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_T20_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                playerList.clear();

                //progressDialog.dismiss();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                WomenPlayerRanking ranking = gson.fromJson(response, WomenPlayerRanking.class);

                playerList.addAll(ranking.getContent());

                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("WBowlingRankingFragment","Error" + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
