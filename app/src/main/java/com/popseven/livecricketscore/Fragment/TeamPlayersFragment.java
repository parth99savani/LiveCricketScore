package com.popseven.livecricketscore.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popseven.livecricketscore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamPlayersFragment extends Fragment {


    public TeamPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_players, container, false);
    }

}
