package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.MatchDetail.Player;
import com.popseven.livecricketscore.Model.Scorecard.Bowler;
import com.popseven.livecricketscore.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SCBowlerAdapter extends RecyclerView.Adapter<SCBowlerAdapter.MyViewHolder> {

    private Context context;
    private List<Bowler> bowlerList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();

    public SCBowlerAdapter(Context context, List<Bowler> bowlerList, List<Player> playerList) {
        this.context = context;
        this.bowlerList = bowlerList;
        this.playerList = playerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewBowlerItem;
        private TextView textviewOverItem;
        private TextView textviewMaidenItem;
        private TextView textviewRunBowlerItem;
        private TextView textviewWicketItem;
        private TextView textviewEconomyRateItem;
        private LinearLayout linearScoreBowlerItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewBowlerItem = itemView.findViewById(R.id.textviewBowlerItem);
            textviewOverItem = itemView.findViewById(R.id.textviewOverItem);
            textviewMaidenItem = itemView.findViewById(R.id.textviewMaidenItem);
            textviewRunBowlerItem = itemView.findViewById(R.id.textviewRunBowlerItem);
            textviewWicketItem = itemView.findViewById(R.id.textviewWicketItem);
            textviewEconomyRateItem = itemView.findViewById(R.id.textviewEconomyRateItem);
            linearScoreBowlerItem = itemView.findViewById(R.id.linearScoreBowlerItem);
        }
    }

    @NonNull
    @Override
    public SCBowlerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_scorecard_bowler, viewGroup, false);

        return new SCBowlerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SCBowlerAdapter.MyViewHolder holder, final int i) {

        Bowler bowler = bowlerList.get(i);

        for(int j=0; j<playerList.size(); j++){
            if(bowler.getId().equals(playerList.get(j).getId())){
                holder.textviewBowlerItem.setText(playerList.get(j).getFName());
            }
        }
        holder.textviewOverItem.setText(bowler.getO());
        holder.textviewMaidenItem.setText(bowler.getM());
        holder.textviewRunBowlerItem.setText(bowler.getR());
        holder.textviewWicketItem.setText(bowler.getW());

        if (Float.parseFloat(bowler.getO()) > 0) {
            float r = Float.parseFloat(bowler.getR());
            int o = (int) (Float.parseFloat(bowler.getO()));
            float b = (Float.parseFloat(bowler.getO()) - o) * 10;
            b = b + (o * 6);
            float economicRateP1 = (r / b) * 6;
            holder.textviewEconomyRateItem.setText(new DecimalFormat("###.##").format(economicRateP1));
        } else {
            holder.textviewEconomyRateItem.setText("0");
        }
    }

    @Override
    public int getItemCount() {
        return bowlerList.size();
    }

}

