package com.popseven.livecricketscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popseven.livecricketscore.Model.LiveMatches.Match;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Match> itemList;
    private Context context;

    public ItemAdapter(List<Match> itemList, Context context) {

        // generate constructors to initialise the List and Context objects

        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views
        final Match item = itemList.get(position);
        holder.txtEventName.setText(item.getSeriesName());
        holder.txtFromDate.setText(item.getMatchId());
        holder.txtToDate.setText(item.getSeriesId());
        holder.txtBookingName.setText(item.getDataPath());

    }

    @Override

    //return the size of the listItems (developersList)
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects
        public TextView txtEventName,txtFromDate,txtToDate,txtBookingName;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects
            txtEventName = (TextView) itemView.findViewById(R.id.txtEventName);
            txtFromDate = (TextView) itemView.findViewById(R.id.txtFromDate);
            txtToDate = (TextView) itemView.findViewById(R.id.txtToDate);
            txtBookingName = (TextView) itemView.findViewById(R.id.txtBookingName);
        }

    }

}
