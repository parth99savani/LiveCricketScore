package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Model.Schedule.Match;
import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.Model.Schedule.Team;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingMatchAdapter extends RecyclerView.Adapter<UpcomingMatchAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Series> series = new ArrayList<>();
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private UpcomingMatchAdapterListener listener;

    public UpcomingMatchAdapter(Context context, List<Match> matchList, List<Team> teams, List<Series> series, UpcomingMatchAdapterListener listener) {
        this.context = context;
        this.matchList = matchList;
        this.teams = teams;
        this.series = series;
        colorList = teamColorList.getTeamColorList();
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesName;
        private Button btnTeam1;
        private TextView txtTeam1Score;
        private Button btnTeam2;
        private TextView txtTeam2Score;
        private TextView txtMatchDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesName = itemView.findViewById(R.id.txtSeriesName);
            btnTeam1 = itemView.findViewById(R.id.btnTeam1);
            txtTeam1Score = itemView.findViewById(R.id.txtTeam1Score);
            btnTeam2 = itemView.findViewById(R.id.btnTeam2);
            txtTeam2Score = itemView.findViewById(R.id.txtTeam2Score);
            txtMatchDate = itemView.findViewById(R.id.txtMatchDate);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_match_series, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Match item = matchList.get(i);

        holder.txtTeam1Score.setVisibility(View.GONE);
        holder.txtTeam2Score.setVisibility(View.GONE);

        for(int j=0; j<teams.size();j++){
            if(teams.get(j).getId().equals(String.valueOf(item.getTeam().get(0)))){
                holder.btnTeam1.setText(teams.get(j).getName());
                for(int k=0; k<colorList.size(); k++ ){
                    if(String.valueOf(item.getTeam().get(0)).equals(colorList.get(k).getId())){
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(k).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
            }
            if(teams.get(j).getId().equals(String.valueOf(item.getTeam().get(1)))){
                holder.btnTeam2.setText(teams.get(j).getName());
                for(int k=0; k<colorList.size(); k++ ){
                    if(String.valueOf(item.getTeam().get(1)).equals(colorList.get(k).getId())){
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(k).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
            }
        }

        for(int k=0; k<series.size();k++){
            if(series.get(k).getId().equals(item.getSeriesId())){
                holder.txtSeriesName.setText(item.getMDesc() + " of " + series.get(k).getName());
            }
        }

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(item.getStartTime())*1000);
        Date date = cal.getTime();

        holder.txtMatchDate.setText(new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date));

        holder.btnTeam1.setClickable(false);
        holder.btnTeam2.setClickable(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpcomingMatchSelected();
                holder.itemView.setEnabled(false);
                holder.itemView.postDelayed(new Runnable() {
                    public void run() {
                        holder.itemView.setEnabled(true);
                    }
                }, 500);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public interface UpcomingMatchAdapterListener {
        void onUpcomingMatchSelected();
    }

}
