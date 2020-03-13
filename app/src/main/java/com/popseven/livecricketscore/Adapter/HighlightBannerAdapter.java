package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.popseven.livecricketscore.Model.Highlights.Content;
import com.popseven.livecricketscore.R;

import java.util.List;

public class HighlightBannerAdapter extends LoopingPagerAdapter<Content> {

    private ImageView imageViewThumb;
    private TextView txtHighlightName;
    private ImageButton btnPlay;
    private HighlightBannerAdapterListener listener;

    public HighlightBannerAdapter(Context context, List<Content> itemList, boolean isInfinite, HighlightBannerAdapterListener listener) {
        super(context, itemList, isInfinite);
        this.listener = listener;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.item_highlight_banner, container, false);
    }


    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {

        imageViewThumb = convertView.findViewById(R.id.imageViewThumb);
        txtHighlightName = convertView.findViewById(R.id.txtHighlightName);
        btnPlay = convertView.findViewById(R.id.btnPlay);

        final Content content = itemList.get(listPosition);

        txtHighlightName.setText(content.getTitle());

        Glide.with(context)
                .load(content.getImageUrl())
                .into(imageViewThumb);

        btnPlay.setClickable(false);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHighlightBannerSelected(content.getMediaId(),content.getTitle());
            }
        });
    }

    public interface HighlightBannerAdapterListener {
        void onHighlightBannerSelected(String mediaId, String title);
    }

}
