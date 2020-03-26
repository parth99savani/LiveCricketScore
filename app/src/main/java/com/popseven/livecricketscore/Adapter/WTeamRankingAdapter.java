package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Model.WomenTeamRanking.WomenTeamRanking;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class WTeamRankingAdapter extends RecyclerView.Adapter<WTeamRankingAdapter.MyViewHolder> {

    private Context context;
    private List<WomenTeamRanking> teamRankList = new ArrayList<>();

    public WTeamRankingAdapter(Context context, List<WomenTeamRanking> teamRankList) {
        this.context = context;
        this.teamRankList = teamRankList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewRank;
        private TextView textviewTeam;
        private TextView textviewRating;
        private TextView textviewPoints;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewRank = itemView.findViewById(R.id.textviewRank);
            textviewTeam = itemView.findViewById(R.id.textviewTeam);
            textviewRating = itemView.findViewById(R.id.textviewRating);
            textviewPoints = itemView.findViewById(R.id.textviewPoints);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_team_ranking, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        WomenTeamRanking test = teamRankList.get(i);

        holder.textviewRank.setText(String.valueOf(test.getPosition()));
        holder.textviewTeam.setText(test.getTeam().getName());
        holder.textviewPoints.setText(String.valueOf(test.getPoints()));
        holder.textviewRating.setText(String.valueOf(test.getRating()));

    }

    @Override
    public int getItemCount() {
        return teamRankList.size();
    }

}