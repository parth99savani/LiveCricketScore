package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Model.Schedule.Series;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private Context context;
    private List<Series> seriesList = new ArrayList<>();
    private SeriesAdapterListener listener;

    public SeriesAdapter(Context context, List<Series> seriesList, SeriesAdapterListener listener) {
        this.context = context;
        this.seriesList = seriesList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesName = itemView.findViewById(R.id.txtSeriesName);
        }
    }

    @NonNull
    @Override
    public SeriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_series_schedule, viewGroup, false);

        return new SeriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SeriesAdapter.MyViewHolder holder, final int i) {
        holder.txtSeriesName.setText(seriesList.get(i).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSeriesSelected(seriesList.get(i).getId(),seriesList.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public interface SeriesAdapterListener {
        void onSeriesSelected(String seriesId, String name);
    }
}
