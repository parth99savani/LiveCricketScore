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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.PlayerRankingAdapter;
import com.popseven.livecricketscore.Model.Ranking.Batting;
import com.popseven.livecricketscore.Model.Ranking.Batting_;
import com.popseven.livecricketscore.Model.Ranking.Batting__;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattingRankingFragment extends Fragment {

    private ToggleSwitch togglePlayers;
    private LinearLayout ll1;
    private TextView textviewRank;
    private TextView textviewPlayer;
    private TextView textviewRating;
    private LinearLayout ll;
    private RecyclerView recyclerRanking;
    private PlayerRankingAdapter adapter;
    private List<Batting> batting = new ArrayList<>(),battings = new ArrayList<>(),battingList = new ArrayList<>(),battingPlayerList;

    public BattingRankingFragment(List<Batting> batting, List<Batting> battings, List<Batting> battingList) {
        // Required empty public constructor
        this.batting=batting;
        this.battings=battings;
        this.battingList=battingList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_ranking, container, false);

        togglePlayers = view.findViewById(R.id.togglePlayersRank);
        ll1 = view.findViewById(R.id.ll1);
        textviewRank = view.findViewById(R.id.textviewRank);
        textviewPlayer = view.findViewById(R.id.textviewPlayer);
        textviewRating = view.findViewById(R.id.textviewRating);
        ll = view.findViewById(R.id.ll);
        recyclerRanking = view.findViewById(R.id.recyclerRanking);

        battingPlayerList = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("TEST");
        labels.add("ODI");
        labels.add("T20");
        togglePlayers.setLabels(labels);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recyclerRanking.setHasFixedSize(true);
        recyclerRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PlayerRankingAdapter(getActivity(), battingPlayerList);
        recyclerRanking.setAdapter(adapter);
        recyclerRanking.setNestedScrollingEnabled(false);
        recyclerRanking.addItemDecoration(itemDecor);
        battingPlayerList.clear();
        battingPlayerList.addAll(batting);
        adapter.notifyDataSetChanged();

        togglePlayers.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if(position==0){
                    battingPlayerList.clear();
                    battingPlayerList.addAll(batting);
                    adapter.notifyDataSetChanged();
                }else if(position == 1) {
                    battingPlayerList.clear();
                    battingPlayerList.addAll(battings);
                    adapter.notifyDataSetChanged();
                }else if(position == 2) {
                    battingPlayerList.clear();
                    battingPlayerList.addAll(battingList);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

}
