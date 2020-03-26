package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Model.Ranking.Batting;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class PlayerRankingAdapter extends RecyclerView.Adapter<PlayerRankingAdapter.MyViewHolder> {

    private Context context;
    private List<Batting> battingList = new ArrayList<>();

    public PlayerRankingAdapter(Context context, List<Batting> battingList) {
        this.context = context;
        this.battingList = battingList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewRank;
        private TextView textviewPlayerName;
        private TextView textviewPlayerCountry;
        private TextView textviewRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewRank = itemView.findViewById(R.id.textviewRank);
            textviewPlayerName = itemView.findViewById(R.id.textviewPlayerName);
            textviewPlayerCountry = itemView.findViewById(R.id.textviewPlayerCountry);
            textviewRating = itemView.findViewById(R.id.textviewRating);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player_ranking, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        Batting batting = battingList.get(i);

        holder.textviewRank.setText(batting.getRank());
        holder.textviewPlayerName.setText(batting.getName());
        holder.textviewPlayerCountry.setText(batting.getCountry());
        holder.textviewRating.setText(batting.getRating());

    }

    @Override
    public int getItemCount() {
        return battingList.size();
    }

}