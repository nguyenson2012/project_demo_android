package com.example.asus.projectdemo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
/**
 * Created by SON on 3/21/2016.
 */
public class GridViewLevelAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<LevelCardItem> levelCardItems;
    private Activity context;

    public GridViewLevelAdapter(Activity context, List<LevelCardItem> levelCardItems) {
        this.levelCardItems = levelCardItems;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_level, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.imageLevel.setImageResource(R.drawable.background_puzzle);
        holder.textViewLevel.setText(levelCardItems.get(position).getLevelTitle());
    }

    @Override
    public int getItemCount() {
        return this.levelCardItems.size();
    }

}