package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Model.Schedule.Match;
import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.Model.Schedule.Team;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingSeriesAdapter extends RecyclerView.Adapter<UpcomingSeriesAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Series> series = new ArrayList<>();
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private List<String> seriesIdList = new ArrayList<>();
    private UpcomingMatchAdapter adapter;
    private List<Match> seriesMatchList;
    private UpcomingMatchAdapter.UpcomingMatchAdapterListener listener;
    private UpcomingSeriesAdapterListener upcomingSeriesAdapterListener;

    public UpcomingSeriesAdapter(Context context, List<String> seriesIdList, List<Match> matchList, List<Team> teams, List<Series> series, UpcomingMatchAdapter.UpcomingMatchAdapterListener listener, UpcomingSeriesAdapterListener upcomingSeriesAdapterListener) {
        this.context = context;
        this.seriesIdList = seriesIdList;
        this.matchList = matchList;
        this.teams = teams;
        this.series = series;
        colorList = teamColorList.getTeamColorList();
        this.listener = listener;
        this.upcomingSeriesAdapterListener = upcomingSeriesAdapterListener;
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

        String seriesName = null;

        for(int k=0; k<series.size();k++){
            if(series.get(k).getId().equals(seriesIdList.get(i))){
                holder.txtSeriesName.setText(series.get(k).getName());
                seriesName = series.get(k).getName();
            }
        }

        for(int j=0; j<matchList.size(); j++){
            if (matchList.get(j).getSeriesId().equals(seriesIdList.get(i))){
                seriesMatchList.add(matchList.get(j));
            }
        }

        adapter = new UpcomingMatchAdapter(context, seriesMatchList, teams, series, listener);

        holder.recyclerViewSeriesMatch.setHasFixedSize(true);

        holder.recyclerViewSeriesMatch.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.recyclerViewSeriesMatch.setAdapter(adapter);

        holder.recyclerViewSeriesMatch.setNestedScrollingEnabled(false);

        final String finalSeriesName = seriesName;
        holder.txtSeriesName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upcomingSeriesAdapterListener.onUpcomingSeriesSelected(seriesIdList.get(i), finalSeriesName);
            }
        });

    }

    @Override
    public int getItemCount() {
        return seriesIdList.size();
    }

    public interface UpcomingSeriesAdapterListener {
        void onUpcomingSeriesSelected(String seriesId, String seriesName);
    }

}

