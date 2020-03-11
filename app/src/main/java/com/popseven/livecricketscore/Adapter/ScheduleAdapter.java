package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Model.Series.Match;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private Context context;
    private List<Match> matchList = new ArrayList<>();
    private ScheduleAdapterListener listener;
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();

    public ScheduleAdapter(Context context, List<Match> matchList, ScheduleAdapterListener listener) {
        this.context = context;
        this.matchList = matchList;
        this.listener = listener;
        colorList = teamColorList.getTeamColorList();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesName;
        private Button btnTeam1;
        private Button btnTeam2;
        private TextView txtMatchDate;
        private TextView txtTeam1Score;
        private TextView txtTeam2Score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesName = itemView.findViewById(R.id.txtSeriesNo);
            btnTeam1 = itemView.findViewById(R.id.btnTeam1);
            btnTeam2 = itemView.findViewById(R.id.btnTeam2);
            txtMatchDate = itemView.findViewById(R.id.txtMatchDate);
            txtTeam1Score = itemView.findViewById(R.id.txtTeam1Score);
            txtTeam2Score = itemView.findViewById(R.id.txtTeam2Score);
        }
    }

    @NonNull
    @Override
    public ScheduleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_match_schedule, viewGroup, false);

        return new ScheduleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduleAdapter.MyViewHolder holder, final int i) {

        final Match item = matchList.get(i);

        holder.txtSeriesName.setText(item.getHeader().getMatchDesc());

        if (item.getHeader().getStatus().equals("")){
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(item.getHeader().getStartTime())*1000);
            Date date = cal.getTime();
            holder.txtMatchDate.setText(new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date));
        }else {
            holder.txtMatchDate.setText(item.getHeader().getStatus());
        }

        if (item.getBatTeam() == null && item.getBowTeam() == null) {
            holder.btnTeam1.setText(item.getTeam1().getSName());
            for (int j = 0; j < colorList.size(); j++) {
                if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                    Drawable buttonDrawable = holder.btnTeam1.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                    holder.btnTeam1.setBackground(buttonDrawable);
                    break;
                }else {
                    Drawable buttonDrawable = holder.btnTeam1.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                    holder.btnTeam1.setBackground(buttonDrawable);
                }
            }
            holder.btnTeam2.setText(item.getTeam2().getSName());
            for (int j = 0; j < colorList.size(); j++) {
                if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                    Drawable buttonDrawable = holder.btnTeam2.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                    holder.btnTeam2.setBackground(buttonDrawable);
                    break;
                }else {
                    Drawable buttonDrawable = holder.btnTeam2.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                    holder.btnTeam2.setBackground(buttonDrawable);
                }
            }
            holder.txtTeam1Score.setVisibility(View.GONE);
            holder.txtTeam2Score.setVisibility(View.GONE);
        } else {
            holder.txtTeam1Score.setVisibility(View.VISIBLE);
            holder.txtTeam2Score.setVisibility(View.VISIBLE);
            if (item.getBatTeam().getName().equalsIgnoreCase(item.getTeam1().getName()) || item.getBatTeam().getName().equalsIgnoreCase(item.getTeam1().getSName())) {
                holder.btnTeam1.setText(item.getTeam1().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
                holder.btnTeam2.setText(item.getTeam2().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
            } else if (item.getBatTeam().getName().equalsIgnoreCase(item.getTeam2().getName()) || item.getBatTeam().getName().equalsIgnoreCase(item.getTeam2().getSName())) {
                holder.btnTeam1.setText(item.getTeam2().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                        break;
                    } else {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
                holder.btnTeam2.setText(item.getTeam1().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
            } else if (item.getBowTeam().getName().equalsIgnoreCase(item.getTeam1().getName()) || item.getBowTeam().getName().equalsIgnoreCase(item.getTeam1().getSName())) {
                holder.btnTeam2.setText(item.getTeam1().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
                holder.btnTeam1.setText(item.getTeam2().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                        break;
                    } else {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
            } else if (item.getBowTeam().getName().equalsIgnoreCase(item.getTeam2().getName()) || item.getBowTeam().getName().equalsIgnoreCase(item.getTeam2().getSName())) {
                holder.btnTeam2.setText(item.getTeam2().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                        holder.btnTeam2.setBackground(buttonDrawable);
                    }
                }
                holder.btnTeam1.setText(item.getTeam1().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                        break;
                    } else {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
            }else {
                holder.btnTeam1.setText(item.getTeam1().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam1().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam1.setBackground(buttonDrawable);
                        break;
                    } else {
                        Drawable buttonDrawable = holder.btnTeam1.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorAccent));
                        holder.btnTeam1.setBackground(buttonDrawable);
                    }
                }
                holder.btnTeam2.setText(item.getTeam2().getSName());
                for (int j = 0; j < colorList.size(); j++) {
                    if (item.getTeam2().getId().equals(colorList.get(j).getId())) {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                        holder.btnTeam2.setBackground(buttonDrawable);
                        break;
                    }else {
                        Drawable buttonDrawable = holder.btnTeam2.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
                        holder.btnTeam2.setBackground(buttonDrawable);
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
                listener.onScheduleSelected(matchList.get(i).getMatchId(),matchList.get(i).getSeriesId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public interface ScheduleAdapterListener {
        void onScheduleSelected(String matchId, String seriesId);
    }
}
