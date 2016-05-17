package com.example.asus.projectdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SON on 3/21/2016.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imageLevel;
    public TextView textViewLevel;
    public RecyclerViewHolders(View itemView) {
        super(itemView);
        imageLevel=(ImageView)itemView.findViewById(R.id.image_level);
        textViewLevel=(TextView)itemView.findViewById(R.id.textview_level);
    }
    @Override
    public void onClick(View view) {

    }
}
