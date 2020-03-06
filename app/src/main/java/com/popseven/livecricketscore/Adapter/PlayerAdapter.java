package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.MatchDetail.Player;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private Context context;
    private List<Integer> playerIdList = new ArrayList<>();
    private List<Player> playerList = new ArrayList<>();

    public PlayerAdapter(Context context, List<Integer> playerIdList, List<Player> playerList) {
        this.context = context;
        this.playerIdList = playerIdList;
        this.playerList = playerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textPlayerName;
        private TextView textPlayerSpeciality;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textPlayerName = itemView.findViewById(R.id.textPlayerName);
            textPlayerSpeciality = itemView.findViewById(R.id.textPlayerSpeciality);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_players, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        for(int k=0; k<playerList.size(); k++){
            if(String.valueOf(playerIdList.get(i)).equals(playerList.get(k).getId())){
                if(playerList.get(k).getRole()==null){
                    holder.textPlayerName.setText(playerList.get(k).getFName());
                }else {
                    holder.textPlayerName.setText(playerList.get(k).getFName()+" "+playerList.get(k).getRole());
                }
                holder.textPlayerSpeciality.setText(playerList.get(k).getSpeciality());
            }
        }

    }

    @Override
    public int getItemCount() {
        return playerIdList.size();
    }

}

