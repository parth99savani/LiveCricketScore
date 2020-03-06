package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.PointTable.GroupA;
import com.popseven.livecricketscore.Model.PointTable.GroupB;
import com.popseven.livecricketscore.Model.PointTable.GroupC;
import com.popseven.livecricketscore.Model.PointTable.GroupD;
import com.popseven.livecricketscore.Model.PointTable.Team;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PointTableGroupAdapter extends RecyclerView.Adapter<PointTableGroupAdapter.MyViewHolder> {

    private Context context;
    private List<String> orderList = new ArrayList<>();
    private List<Team> teamList = new ArrayList<>();
    private List<GroupD> groupD = new ArrayList<>();
    private List<GroupC> groupC = new ArrayList<>();
    private List<GroupB> groupB = new ArrayList<>();
    private List<GroupA> groupA = new ArrayList<>();
    private PointTableAdapter adapter;
    private List<com.popseven.livecricketscore.Model.Schedule.Team> teams = new ArrayList<>();

    public PointTableGroupAdapter(Context context, List<String> orderList, List<com.popseven.livecricketscore.Model.Schedule.Team> teams, List<Team> teamList, List<GroupD> groupD, List<GroupC> groupC, List<GroupB> groupB, List<GroupA> groupA) {
        this.context = context;
        this.orderList = orderList;
        this.teams = teams;
        this.teamList = teamList;
        this.groupD = groupD;
        this.groupC = groupC;
        this.groupB = groupB;
        this.groupA = groupA;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtGroupName;
        private RecyclerView recyclerViewPointTable;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtGroupName = itemView.findViewById(R.id.txtGroupName);
            recyclerViewPointTable = itemView.findViewById(R.id.recyclerViewPointTable);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_pointtable, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        switch(orderList.get(i)){
            case "Teams":
                adapter=null;
                adapter = new PointTableAdapter(context,teams,teamList,null,null,null,null);
                holder.recyclerViewPointTable.setHasFixedSize(true);
                holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
                holder.recyclerViewPointTable.setAdapter(adapter);
                break;
            case "Group A":
                adapter=null;
                adapter = new PointTableAdapter(context,teams,null,null,null,null,groupA);
                holder.recyclerViewPointTable.setHasFixedSize(true);
                holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
                holder.recyclerViewPointTable.setAdapter(adapter);
                break;
            case "Group B":
                adapter=null;
                adapter = new PointTableAdapter(context,teams,null,null,null,groupB,null);
                holder.recyclerViewPointTable.setHasFixedSize(true);
                holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
                holder.recyclerViewPointTable.setAdapter(adapter);
                break;
            case "Group C":
                adapter=null;
                adapter = new PointTableAdapter(context,teams,null,null,groupC,null,null);
                holder.recyclerViewPointTable.setHasFixedSize(true);
                holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
                holder.recyclerViewPointTable.setAdapter(adapter);
                break;
            case "Group D":
                adapter=null;
                adapter = new PointTableAdapter(context,teams,null,groupD,null,null,null);
                holder.recyclerViewPointTable.setHasFixedSize(true);
                holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
                holder.recyclerViewPointTable.setAdapter(adapter);
                break;
        }

//        if(orderList.size()==1){
//            groupA.clear();
//            groupB.clear();
//            groupC.clear();
//            groupD.clear();
//        }else if (orderList.size()==2){
//            teamList.clear();
//            groupC.clear();
//            groupD.clear();
//        }else if (orderList.size()==4){
//            teamList.clear();
//            groupA.clear();
//            groupB.clear();
//        }

//        adapter = new PointTableAdapter(context,teamList,groupD,groupC,groupB,groupA);
//        holder.recyclerViewPointTable.setHasFixedSize(true);
//        holder.recyclerViewPointTable.setLayoutManager(new LinearLayoutManager(context));
//        holder.recyclerViewPointTable.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

}

