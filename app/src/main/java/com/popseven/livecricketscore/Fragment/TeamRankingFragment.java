package com.popseven.livecricketscore.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Adapter.TeamRankingAdapter;
import com.popseven.livecricketscore.Model.Ranking.TEST_;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamRankingFragment extends Fragment {

    private ToggleSwitch toggleTeam;
    private LinearLayout ll1;
    private TextView textviewRank;
    private TextView textviewTeam;
    private TextView textviewRating;
    private TextView textviewPoints;
    private LinearLayout ll;
    private RecyclerView recyclerRankingTeam;
    private TeamRankingAdapter adapter;
    private List<TEST_> test,odi,t20,teamRankList;

    public TeamRankingFragment(List<TEST_> test, List<TEST_> odi, List<TEST_> t20) {
        // Required empty public constructor
        this.test=test;
        this.odi=odi;
        this.t20=t20;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_ranking, container, false);

        toggleTeam = view.findViewById(R.id.toggleTeam);
        ll1 = view.findViewById(R.id.ll1);
        textviewRank = view.findViewById(R.id.textviewRank);
        textviewTeam = view.findViewById(R.id.textviewTeam);
        textviewRating = view.findViewById(R.id.textviewRating);
        textviewPoints = view.findViewById(R.id.textviewPoints);
        ll = view.findViewById(R.id.ll);
        recyclerRankingTeam = view.findViewById(R.id.recyclerRankingTeam);

        teamRankList = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("TEST");
        labels.add("ODI");
        labels.add("T20");
        toggleTeam.setLabels(labels);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), VERTICAL);

        recyclerRankingTeam.setHasFixedSize(true);
        recyclerRankingTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TeamRankingAdapter(getActivity(), teamRankList);
        recyclerRankingTeam.setAdapter(adapter);
        recyclerRankingTeam.setNestedScrollingEnabled(false);
        recyclerRankingTeam.addItemDecoration(itemDecor);
        teamRankList.clear();
        teamRankList.addAll(test);
        adapter.notifyDataSetChanged();

        toggleTeam.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if(position==0){
                    teamRankList.clear();
                    teamRankList.addAll(test);
                    adapter.notifyDataSetChanged();
                }else if(position == 1) {
                    teamRankList.clear();
                    teamRankList.addAll(odi);
                    adapter.notifyDataSetChanged();
                }else if(position == 2) {
                    teamRankList.clear();
                    teamRankList.addAll(t20);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return view;

    }
}
