package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.popseven.livecricketscore.Model.Ranking.TEST_;
import com.popseven.livecricketscore.R;
import java.util.ArrayList;
import java.util.List;

public class TeamRankingAdapter extends RecyclerView.Adapter<TeamRankingAdapter.MyViewHolder> {

    private Context context;
    private List<TEST_> teamRankList = new ArrayList<>();

    public TeamRankingAdapter(Context context, List<TEST_> teamRankList) {
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

        TEST_ test = teamRankList.get(i);

        holder.textviewRank.setText(test.getRank());
        holder.textviewTeam.setText(test.getName());
        holder.textviewPoints.setText(test.getPoints());
        holder.textviewRating.setText(test.getRating());

    }

    @Override
    public int getItemCount() {
        return teamRankList.size();
    }

}
