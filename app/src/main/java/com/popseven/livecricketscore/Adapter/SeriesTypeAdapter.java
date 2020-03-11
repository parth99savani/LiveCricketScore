package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.popseven.livecricketscore.Model.Schedule.Tab;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class SeriesTypeAdapter extends RecyclerView.Adapter<SeriesTypeAdapter.MyViewHolder> {

    private Context context;
    private List<Tab> tabList = new ArrayList<>();
    private SeriesTypeAdapterListener listener;
    private int row_index = -1;

    public SeriesTypeAdapter(Context context, List<Tab> tabList, SeriesTypeAdapterListener listener) {
        this.context = context;
        this.tabList = tabList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSeriesType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSeriesType = itemView.findViewById(R.id.txtSeriesType);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_series_type, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        holder.txtSeriesType.setText(tabList.get(i).getPageTitle());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=i;
                listener.onSeriesTypeSelected(tabList.get(i).getId());
                notifyDataSetChanged();
            }
        });

        if(row_index==i){
            Drawable buttonDrawable = holder.txtSeriesType.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorPrimary));
            holder.txtSeriesType.setBackground(buttonDrawable);
            holder.txtSeriesType.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            Drawable buttonDrawable = holder.txtSeriesType.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.backgroundGray));
            holder.txtSeriesType.setBackground(buttonDrawable);
            holder.txtSeriesType.setTextColor(context.getResources().getColor(android.R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return tabList.size();
    }

    public interface SeriesTypeAdapterListener {
        void onSeriesTypeSelected(int tabId);
    }
}
