package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Model.LiveMatches.Match;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class LiveMatchAdapter extends RecyclerView.Adapter<LiveMatchAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private LiveMatchAdapterListener listener;
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();

    public LiveMatchAdapter(Context context, List<Match> matchList, LiveMatchAdapterListener listener) {
        this.context = context;
        this.matchList = matchList;
        this.listener = listener;
        colorList = teamColorList.getTeamColorList();
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_match, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Match item = matchList.get(i);

//        final Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis());
//        Date date = cal.getTime();
//        int mHour = date.getHours();
//        int mMinute = date.getMinutes();

        //String time = DateUtils.formatDateTime(context, Integer.parseInt(item.getHeader().getStartTime()), DateUtils.FORMAT_SHOW_TIME);
        //String date =  DateUtils.formatDateTime(context, Integer.parseInt(item.getHeader().getStartTime()), DateUtils.FORMAT_SHOW_DATE);

        //holder.txtMatchDate.setText((int) System.currentTimeMillis());

        holder.txtSeriesName.setText(item.getHeader().getMatchDesc() + " of " + item.getSeriesName());

        holder.txtMatchDate.setText(item.getHeader().getStatus());

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

        holder.btnTeam1.setClickable(false);
        holder.btnTeam2.setClickable(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLiveMatchSelected(item.getMatchId());
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

    public interface LiveMatchAdapterListener {
        void onLiveMatchSelected(String matchId);
    }

}