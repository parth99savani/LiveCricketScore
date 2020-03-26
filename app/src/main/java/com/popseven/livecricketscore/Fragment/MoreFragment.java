package com.popseven.livecricketscore.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popseven.livecricketscore.MensRankingActivity;
import com.popseven.livecricketscore.PointTableActivity;
import com.popseven.livecricketscore.R;
import com.popseven.livecricketscore.RankingActivity;
import com.popseven.livecricketscore.ScheduleActivity;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private CardView cardviewPointTable;
    private CardView cardviewRanking;
    private CardView cardviewSchedule;
    private CardView cardviewShareApp;
    private CardView cardviewRateapp;
    private CardView cardviewPrivacypolicy;

    public MoreFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        cardviewPointTable = view.findViewById(R.id.cardview_point_table);
        cardviewRanking = view.findViewById(R.id.cardview_ranking);
        cardviewSchedule = view.findViewById(R.id.cardview_schedule);
        cardviewShareApp = view.findViewById(R.id.cardview_share_app);
        cardviewRateapp = view.findViewById(R.id.cardview_rateapp);
        cardviewPrivacypolicy = view.findViewById(R.id.cardview_privacypolicy);

        cardviewPointTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PointTableActivity.class));
            }
        });

        cardviewRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RankingActivity.class));
            }
        });

        cardviewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleActivity.class));
            }
        });

        cardviewShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardviewRateapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardviewPrivacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
