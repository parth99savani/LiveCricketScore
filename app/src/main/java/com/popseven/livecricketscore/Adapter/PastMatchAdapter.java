package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.popseven.livecricketscore.Common.TeamColorList;

import com.popseven.livecricketscore.Model.LiveMatches.Match;
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

public class PastMatchAdapter extends RecyclerView.Adapter<PastMatchAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Series> series = new ArrayList<>();
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private PastMatchAdapterListener listener;

    public PastMatchAdapter(Context context, List<Match> matchList, List<Team> teams, List<Series> series, PastMatchAdapterListener listener) {
        this.context = context;
        this.matchList = matchList;
        this.teams = teams;
        this.series = series;
        colorList = teamColorList.getTeamColorList();
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesName;
        private TextView txtMatchDate;
        private Button btnTeam1;
        private TextView txtTeam1Score;
        private Button btnTeam2;
        private TextView txtTeam2Score;
        private TextView txtMatchResult;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesName = itemView.findViewById(R.id.txtSeriesName);
            txtMatchDate = itemView.findViewById(R.id.txtMatchDate);
            btnTeam1 = itemView.findViewById(R.id.btnTeam1);
            txtTeam1Score = itemView.findViewById(R.id.txtTeam1Score);
            btnTeam2 = itemView.findViewById(R.id.btnTeam2);
            txtTeam2Score = itemView.findViewById(R.id.txtTeam2Score);
            txtMatchResult = itemView.findViewById(R.id.txtMatchResult);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_past_match, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Match item = matchList.get(i);

//        holder.txtTeam1Score.setVisibility(View.GONE);
//        holder.txtTeam2Score.setVisibility(View.GONE);

//        //for (int j = 0; j < teams.size(); j++) {
//            //if (teams.get(j).getId().equals(String.valueOf(item.getTeam1()))) {
//                holder.btnTeam1.setText(item.getTeam1().getSName());
//                for (int k = 0; k < colorList.size(); k++) {
//                    if (String.valueOf(item.getTeam1().getId()).equals(colorList.get(k).getId())) {
//                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
//                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
//                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(k).getColorCode()));
//                        holder.btnTeam1.setBackground(buttonDrawable);
//                    }
//                }
//           // }
//           // if (teams.get(j).getId().equals(String.valueOf(item.getTeam2()))) {
//                holder.btnTeam2.setText(item.getTeam2().getSName());
//                for (int k = 0; k < colorList.size(); k++) {
//                    if (String.valueOf(item.getTeam2().getId()).equals(colorList.get(k).getId())) {
//                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
//                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
//                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(k).getColorCode()));
//                        holder.btnTeam2.setBackground(buttonDrawable);
//                    }
//                }
//           // }
//        //}

        if(item.getBatTeam() == null && item.getBowTeam() == null){
            holder.btnTeam1.setText(item.getTeam1().getSName());
            for(int j=0; j<colorList.size(); j++ ){
                if(item.getTeam1().getId().equals(colorList.get(j).getId())){
                    Drawable buttonDrawable = holder.btnTeam1.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                    holder.btnTeam1.setBackground(buttonDrawable);
                }
            }
            holder.btnTeam2.setText(item.getTeam2().getSName());
            for(int j=0; j<colorList.size(); j++ ){
                if(item.getTeam2().getId().equals(colorList.get(j).getId())){
                    Drawable buttonDrawable = holder.btnTeam2.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                    holder.btnTeam2.setBackground(buttonDrawable);
                }
            }
            holder.txtTeam1Score.setVisibility(View.GONE);
            holder.txtTeam2Score.setVisibility(View.GONE);
        }else {
            holder.txtTeam1Score.setVisibility(View.VISIBLE);
            holder.txtTeam2Score.setVisibility(View.VISIBLE);
            if (item.getBatTeam().getId().equals(item.getTeam1().getId())) {
                holder.btnTeam1.setText(item.getTeam1().getSName());
                for(int j=0; j<colorList.size(); j++ ){
                    if(item.getTeam1().getId().equals(colorList.get(j).getId())){
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
            } else if (item.getBatTeam().getId().equals(item.getTeam2().getId())) {
                holder.btnTeam1.setText(item.getTeam2().getSName());
                for(int j=0; j<colorList.size(); j++ ){
                    if(item.getTeam2().getId().equals(colorList.get(j).getId())){
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
            }
            if (item.getBatTeam().getInnings().size() == 1) {
                holder.txtTeam1Score.setText(item.getBatTeam().getInnings().get(0).getScore()+"-"+item.getBatTeam().getInnings().get(0).getWkts()+" ("+item.getBatTeam().getInnings().get(0).getOvers()+")");
            } else if(item.getBatTeam().getInnings().size() == 2){
                holder.txtTeam1Score.setText(item.getBatTeam().getInnings().get(0).getScore()+" & "+item.getBatTeam().getInnings().get(1).getScore()+"-"+item.getBatTeam().getInnings().get(1).getWkts());
            }else if(item.getBatTeam().getInnings().size() == 0){
                holder.txtTeam1Score.setText("");
            }

            if (item.getBowTeam().getId().equals(item.getTeam1().getId())) {
                holder.btnTeam2.setText(item.getTeam1().getSName());
                for(int j=0; j<colorList.size(); j++ ){
                    if(item.getTeam1().getId().equals(colorList.get(j).getId())){
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
            } else if (item.getBowTeam().getId().equals(item.getTeam2().getId())) {
                holder.btnTeam2.setText(item.getTeam2().getSName());
                for(int j=0; j<colorList.size(); j++ ){
                    if(item.getTeam2().getId().equals(colorList.get(j).getId())){
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
            }
            if (item.getBowTeam().getInnings().size() == 1) {
                holder.txtTeam2Score.setText(item.getBowTeam().getInnings().get(0).getScore()+"-"+item.getBowTeam().getInnings().get(0).getWkts()+" ("+item.getBowTeam().getInnings().get(0).getOvers()+")");
            } else if(item.getBowTeam().getInnings().size() == 2){
                holder.txtTeam2Score.setText(item.getBowTeam().getInnings().get(0).getScore()+" & "+item.getBowTeam().getInnings().get(1).getScore()+"-"+item.getBowTeam().getInnings().get(1).getWkts());
            }else if(item.getBowTeam().getInnings().size() == 0){
                holder.txtTeam2Score.setText("");
            }
        }

        holder.txtSeriesName.setText(item.getHeader().getMatchDesc());

        holder.txtMatchResult.setText(item.getHeader().getStatus());

//        for (int k = 0; k < series.size(); k++) {
//            if (series.get(k).getId().equals(item.getSeriesId())) {
//                holder.txtSeriesName.setText(item.getHeader().getMatchDesc());
//            }
//        }

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(item.getHeader().getStartTime()) * 1000);
        Date date = cal.getTime();

        holder.txtMatchDate.setText(new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date));

        holder.btnTeam1.setClickable(false);
        holder.btnTeam2.setClickable(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPastMatchSelected(item.getMatchId());
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

    public interface PastMatchAdapterListener {
        void onPastMatchSelected(String matchId);
    }

}