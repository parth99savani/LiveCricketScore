package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.MatchDetail.Player;
import com.popseven.livecricketscore.Model.Scorecard.Batsman;
import com.popseven.livecricketscore.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SCBatsmanAdapter extends RecyclerView.Adapter<SCBatsmanAdapter.MyViewHolder> {

    private Context context;
    private List<Batsman> batsmanList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();

    public SCBatsmanAdapter(Context context, List<Batsman> batsmanList, List<Player> playerList) {
        this.context = context;
        this.batsmanList = batsmanList;
        this.playerList = playerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewBatsmanItem;
        private TextView textviewRunItem;
        private TextView textviewBowlItem;
        private TextView textviewFourItem;
        private TextView textviewSixItem;
        private TextView textviewStrikeRateItem;
        private TextView textviewOutDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewBatsmanItem = itemView.findViewById(R.id.textviewBatsmanItem);
            textviewRunItem = itemView.findViewById(R.id.textviewRunItem);
            textviewBowlItem = itemView.findViewById(R.id.textviewBowlItem);
            textviewFourItem = itemView.findViewById(R.id.textviewFourItem);
            textviewSixItem = itemView.findViewById(R.id.textviewSixItem);
            textviewStrikeRateItem = itemView.findViewById(R.id.textviewStrikeRateItem);
            textviewOutDesc = itemView.findViewById(R.id.textviewOutDesc);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_scorecard_batsman, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        Batsman batsman = batsmanList.get(i);

        for(int j=0; j<playerList.size(); j++){
            if(batsman.getId().equals(playerList.get(j).getId())){
                holder.textviewBatsmanItem.setText(playerList.get(j).getFName());
            }
        }
        holder.textviewRunItem.setText(batsman.getR());
        holder.textviewBowlItem.setText(batsman.getB());
        holder.textviewFourItem.setText(batsman.get4s());
        holder.textviewSixItem.setText(batsman.get6s());
        holder.textviewOutDesc.setText(batsman.getOutDesc());
        if (Integer.parseInt(batsman.getB()) > 0) {
            float r = Float.parseFloat(batsman.getR());
            float b = Float.parseFloat(batsman.getB());
            float strikeRateP1 = (r / b) * 100;
            holder.textviewStrikeRateItem.setText(new DecimalFormat("###.##").format(strikeRateP1));
        } else {
            holder.textviewStrikeRateItem.setText("0");
        }

    }

    @Override
    public int getItemCount() {
        return batsmanList.size();
    }

}

