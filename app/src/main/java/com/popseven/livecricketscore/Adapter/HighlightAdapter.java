package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.popseven.livecricketscore.Model.Highlights.Content;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.MyViewHolder> {

    private Context context;
    private List<Content> highlightList = new ArrayList<>();
    private HighlightAdapterListener listener;

    public HighlightAdapter(Context context, List<Content> highlightList, HighlightAdapterListener listener) {
        this.context = context;
        this.highlightList = highlightList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewThumb;
        private TextView txtHighlightName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumb = itemView.findViewById(R.id.imageViewThumb);
            txtHighlightName = itemView.findViewById(R.id.txtHighlightName);
        }
    }

    @NonNull
    @Override
    public HighlightAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_highlights, viewGroup, false);

        return new HighlightAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HighlightAdapter.MyViewHolder holder, final int i) {

        final Content content = highlightList.get(i);

        holder.txtHighlightName.setText(content.getTitle());

        Glide.with(context)
                .load(content.getImageUrl())
                .into(holder.imageViewThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHighlightSelected(content.getMediaId(),content.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public interface HighlightAdapterListener {
        void onHighlightSelected(String mediaId, String title);
    }

}