package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Common.TeamColorList;

import com.popseven.livecricketscore.Model.LiveMatches.Match;
import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.Model.Schedule.Team;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PastSeriesAdapter extends RecyclerView.Adapter<PastSeriesAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Series> series = new ArrayList<>();
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private List<String> seriesIdList = new ArrayList<>();
    private PastMatchAdapter adapter;
    private List<Match> seriesMatchList;
    private PastMatchAdapter.PastMatchAdapterListener listener;

    public PastSeriesAdapter(Context context, List<String> seriesIdList, List<Match> matchList, List<Team> teams, List<Series> series, PastMatchAdapter.PastMatchAdapterListener listener) {
        this.context = context;
        this.seriesIdList = seriesIdList;
        this.matchList = matchList;
        this.teams = teams;
        this.series = series;
        colorList = teamColorList.getTeamColorList();
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesName;
        private RecyclerView recyclerViewSeriesMatch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesName = itemView.findViewById(R.id.txtSeriesName);
            recyclerViewSeriesMatch = itemView.findViewById(R.id.recyclerViewSeriesMatch);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_series, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        //seriesMatchList.clear();

        seriesMatchList = new ArrayList<>();

        for(int k=0; k<series.size();k++){
            if(series.get(k).getId().equals(seriesIdList.get(i))){
                holder.txtSeriesName.setText(series.get(k).getName());
            }
        }

        for(int j=0; j<matchList.size(); j++){
            if (matchList.get(j).getSeriesId().equals(seriesIdList.get(i))){
                seriesMatchList.add(matchList.get(j));
            }
        }

        adapter = new PastMatchAdapter(context, seriesMatchList,teams,series,listener);

        holder.recyclerViewSeriesMatch.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
        linearLayoutManager.setStackFromEnd(true);

        holder.recyclerViewSeriesMatch.setLayoutManager(linearLayoutManager);

        holder.recyclerViewSeriesMatch.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return seriesIdList.size();
    }

}


