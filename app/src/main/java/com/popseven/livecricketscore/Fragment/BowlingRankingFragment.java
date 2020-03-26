package com.popseven.livecricketscore.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.popseven.livecricketscore.Adapter.PlayerRankingAdapter;
import com.popseven.livecricketscore.Model.Ranking.Batting;
import com.popseven.livecricketscore.Model.Ranking.Bowling;
import com.popseven.livecricketscore.Model.Ranking.Bowling_;
import com.popseven.livecricketscore.Model.Ranking.Bowling__;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class BowlingRankingFragment extends Fragment {

    private ToggleSwitch togglePlayers;
    private LinearLayout ll1;
    private TextView textviewRank;
    private TextView textviewPlayer;
    private TextView textviewRating;
    private LinearLayout ll;
    private RecyclerView recyclerRanking;
    private PlayerRankingAdapter adapter;
    private List<Batting> battingList;

    public BowlingRankingFragment(List<Batting> bowling, List<Batting> battings, List<Batting> battingList) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bowling_ranking, container, false);

        togglePlayers = view.findViewById(R.id.togglePlayers);
        ll1 = view.findViewById(R.id.ll1);
        textviewRank = view.findViewById(R.id.textviewRank);
        textviewPlayer = view.findViewById(R.id.textviewPlayer);
        textviewRating = view.findViewById(R.id.textviewRating);
        ll = view.findViewById(R.id.ll);
        recyclerRanking = view.findViewById(R.id.recyclerRanking);

        battingList = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("TEST");
        labels.add("ODI");
        labels.add("T20");
        togglePlayers.setLabels(labels);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recyclerRanking.setHasFixedSize(true);
        recyclerRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PlayerRankingAdapter(getActivity(), battingList);
        recyclerRanking.setAdapter(adapter);
        recyclerRanking.setNestedScrollingEnabled(false);
        recyclerRanking.addItemDecoration(itemDecor);

        //loadData();

        return view;
    }
}
