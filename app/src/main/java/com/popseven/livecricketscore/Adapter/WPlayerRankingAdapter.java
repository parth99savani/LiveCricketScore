package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Model.WomenPlayerRanking.Content;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class WPlayerRankingAdapter extends RecyclerView.Adapter<WPlayerRankingAdapter.MyViewHolder> {

    private Context context;
    private List<Content> playerList = new ArrayList<>();

    public WPlayerRankingAdapter(Context context, List<Content> playerList) {
        this.context = context;
        this.playerList = playerList;
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

        Content content = playerList.get(i);

        holder.textviewRank.setText(String.valueOf(i+1));
        holder.textviewPlayerName.setText(content.getPlayer().getFullName());
        holder.textviewPlayerCountry.setText(content.getPlayer().getNationality());
        holder.textviewRating.setText(String.valueOf(content.getRating()));

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

}
