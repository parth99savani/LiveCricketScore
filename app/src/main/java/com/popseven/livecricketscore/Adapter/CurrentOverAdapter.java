package com.popseven.livecricketscore.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CurrentOverAdapter extends RecyclerView.Adapter<CurrentOverAdapter.MyViewHolder> {

    private Context context;
    private List<String> ballList = new ArrayList<>();

    public CurrentOverAdapter(Context context, List<String> ballList) {
        this.context = context;
        this.ballList = ballList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCurrentBall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCurrentBall = itemView.findViewById(R.id.txtCurrentBall);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_current_over, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        holder.txtCurrentBall.setText(ballList.get(i));

        Drawable buttonDrawable = holder.txtCurrentBall.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);

        switch (ballList.get(i)){
            case "4":
                DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorGreen));
                holder.txtCurrentBall.setBackground(buttonDrawable);
                break;
            case "6":
                DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorGreen));
                holder.txtCurrentBall.setBackground(buttonDrawable);
                break;
            case "W":
                DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorRed));
                holder.txtCurrentBall.setBackground(buttonDrawable);
                break;
            default:
                DrawableCompat.setTint(buttonDrawable, context.getResources().getColor(R.color.colorBlack));
                holder.txtCurrentBall.setBackground(buttonDrawable);
        }

    }

    @Override
    public int getItemCount() {
        return ballList.size();
    }

}
