package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.PointTable.EliteGroupAAndB;
import com.popseven.livecricketscore.Model.PointTable.EliteGroupC;
import com.popseven.livecricketscore.Model.PointTable.GroupA;
import com.popseven.livecricketscore.Model.PointTable.GroupB;
import com.popseven.livecricketscore.Model.PointTable.GroupC;
import com.popseven.livecricketscore.Model.PointTable.GroupD;
import com.popseven.livecricketscore.Model.PointTable.Plate;
import com.popseven.livecricketscore.Model.PointTable.Team;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PointTableAdapter extends RecyclerView.Adapter<PointTableAdapter.MyViewHolder> {

    private Context context;
    private List<Team> teamList;
    private List<GroupD> groupDList;
    private List<GroupC> groupCList;
    private List<GroupB> groupBList;
    private List<GroupA> groupAList;
    private List<EliteGroupAAndB> eliteGroupAAndBList;
    private List<EliteGroupC> eliteGroupCList;
    private List<Plate> plateList;
    private List<com.popseven.livecricketscore.Model.AllSeries.Team> teams;

    public PointTableAdapter(Context context,List<com.popseven.livecricketscore.Model.AllSeries.Team> teams, List<Team> teamList, List<GroupD> groupDList, List<GroupC> groupCList, List<GroupB> groupBList, List<GroupA> groupAList, List<EliteGroupAAndB> eliteGroupAAndBList, List<EliteGroupC> eliteGroupCList, List<Plate> plateList) {
        this.context = context;
        this.teams = teams;
        this.teamList = teamList;
        this.groupDList = groupDList;
        this.groupCList = groupCList;
        this.groupBList = groupBList;
        this.groupAList = groupAList;
        this.eliteGroupAAndBList = eliteGroupAAndBList;
        this.eliteGroupCList = eliteGroupCList;
        this.plateList = plateList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewTeamName;
        private TextView textviewPlay;
        private TextView textviewWin;
        private TextView textviewLoss;
        private TextView textviewNoResult;
        private TextView textviewPoints;
        private TextView textviewNetRunRet;
        private LinearLayout linearScoreBowlerItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewTeamName = itemView.findViewById(R.id.textviewTeamName);
            textviewPlay = itemView.findViewById(R.id.textviewPlay);
            textviewWin = itemView.findViewById(R.id.textviewWin);
            textviewLoss = itemView.findViewById(R.id.textviewLoss);
            textviewNoResult = itemView.findViewById(R.id.textviewNoResult);
            textviewPoints = itemView.findViewById(R.id.textviewPoints);
            textviewNetRunRet = itemView.findViewById(R.id.textviewNetRunRet);
            linearScoreBowlerItem = itemView.findViewById(R.id.linearScoreBowlerItem);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_point_table, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        if (groupAList!=null){
            GroupA groupA = groupAList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (groupA.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(groupAList.get(i).getName());
            holder.textviewPlay.setText(groupA.getP());
            holder.textviewWin.setText(groupA.getW());
            holder.textviewLoss.setText(groupA.getL());
            holder.textviewNoResult.setText(groupA.getNr());
            holder.textviewPoints.setText(groupA.getPoints());
            holder.textviewNetRunRet.setText(groupA.getNrr());
        }else if (groupBList!=null){
            GroupB groupB = groupBList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (groupB.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(groupBList.get(i).getName());
            holder.textviewPlay.setText(groupB.getP());
            holder.textviewWin.setText(groupB.getW());
            holder.textviewLoss.setText(groupB.getL());
            holder.textviewNoResult.setText(groupB.getNr());
            holder.textviewPoints.setText(groupB.getPoints());
            holder.textviewNetRunRet.setText(groupB.getNrr());
        }else if (groupCList!=null){
            GroupC groupC = groupCList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (groupC.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(groupCList.get(i).getName());
            holder.textviewPlay.setText(groupC.getP());
            holder.textviewWin.setText(groupC.getW());
            holder.textviewLoss.setText(groupC.getL());
            holder.textviewNoResult.setText(groupC.getNr());
            holder.textviewPoints.setText(groupC.getPoints());
            holder.textviewNetRunRet.setText(groupC.getNrr());
        }else if (groupDList!=null){
            GroupD groupD = groupDList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (groupD.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(groupDList.get(i).getName());
            holder.textviewPlay.setText(groupD.getP());
            holder.textviewWin.setText(groupD.getW());
            holder.textviewLoss.setText(groupD.getL());
            holder.textviewNoResult.setText(groupD.getNr());
            holder.textviewPoints.setText(groupD.getPoints());
            holder.textviewNetRunRet.setText(groupD.getNrr());
        }else if (teamList!=null){
            Team team = teamList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (team.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(teamList.get(i).getName());
            holder.textviewPlay.setText(team.getP());
            holder.textviewWin.setText(team.getW());
            holder.textviewLoss.setText(team.getL());
            holder.textviewNoResult.setText(team.getNr());
            holder.textviewPoints.setText(team.getPoints());
            holder.textviewNetRunRet.setText(team.getNrr());
        }else if (eliteGroupAAndBList!=null){
            EliteGroupAAndB eliteGroupAAndB = eliteGroupAAndBList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (eliteGroupAAndB.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(eliteGroupAAndBList.get(i).getName());
            holder.textviewPlay.setText(eliteGroupAAndB.getP());
            holder.textviewWin.setText(eliteGroupAAndB.getW());
            holder.textviewLoss.setText(eliteGroupAAndB.getL());
            holder.textviewNoResult.setText(eliteGroupAAndB.getNr());
            holder.textviewPoints.setText(eliteGroupAAndB.getPoints());
            holder.textviewNetRunRet.setText(eliteGroupAAndB.getNrr());
        }else if (eliteGroupCList!=null){
            EliteGroupC eliteGroupC = eliteGroupCList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (eliteGroupC.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(eliteGroupCList.get(i).getName());
            holder.textviewPlay.setText(eliteGroupC.getP());
            holder.textviewWin.setText(eliteGroupC.getW());
            holder.textviewLoss.setText(eliteGroupC.getL());
            holder.textviewNoResult.setText(eliteGroupC.getNr());
            holder.textviewPoints.setText(eliteGroupC.getPoints());
            holder.textviewNetRunRet.setText(eliteGroupC.getNrr());
        }else if (plateList!=null){
            Plate plate = plateList.get(i);
//            for (int j=0; j<teams.size(); j++){
//                if (plate.getId().equals(teams.get(j).getId())){
//                    holder.textviewTeamName.setText(teams.get(j).getSName());
//                }
//            }
            holder.textviewTeamName.setText(plateList.get(i).getName());
            holder.textviewPlay.setText(plate.getP());
            holder.textviewWin.setText(plate.getW());
            holder.textviewLoss.setText(plate.getL());
            holder.textviewNoResult.setText(plate.getNr());
            holder.textviewPoints.setText(plate.getPoints());
            holder.textviewNetRunRet.setText(plate.getNrr());
        }

    }

    @Override
    public int getItemCount() {
        if (groupAList!=null){
            return groupAList.size();
        }else if (groupBList!=null){
            return groupBList.size();
        }else if (groupCList!=null){
            return groupCList.size();
        }else if (groupDList!=null){
            return groupDList.size();
        }else if (teamList!=null){
            return teamList.size();
        }else if (eliteGroupAAndBList!=null){
            return eliteGroupAAndBList.size();
        }else if (eliteGroupCList!=null){
            return eliteGroupCList.size();
        }else if (plateList!=null){
            return plateList.size();
        }else {
            return 0;
        }
    }

}
