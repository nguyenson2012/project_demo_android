package com.example.asus.projectdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ThangDuong on 03-Mar-16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {
    Context context;
    static String[] data;
//    private static LayoutInflater inflater=null;
    private static int numCreated = 0;
    public MyRecyclerAdapter(MainActivity mainActivity, String[] data) {
        // TODO Auto-generated constructor stub
        this.context =mainActivity;
        this.data = data;

        //Input data into list here if needed

//        inflater = ( LayoutInflater ) this.context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Log.e("Adapter", "MyRecyclerAdapter Construction");
    }

    @Override
    public MyRecyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        numCreated++;
//        Log.e("RV", "OncreateViewHolder [" + numCreated + "]");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, null);
        MyHolder mh = new MyHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You Clicked ", Toast.LENGTH_SHORT).show();
            }
        });
        return mh;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyHolder holder, int position) {
//        Log.e("RV", "OnBindViewHolder");
        holder.txt.setText(data[position]);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
//        Log.e("Adapter", "Position = "+position);
        return position;
    }

    @Override
    public int getItemCount() {
//        Log.e("Adapter", "getItemCount");
        return data.length;
    }

//    public class Holder
//    {
//        TextView tv;
//        ImageView img;
//    }
    /*@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = null;
            rowView = inflater.inflate(R.layout.adapter, null);
            holder.tv = (TextView) rowView.findViewById(R.id.textView);
            holder.img = (ImageView) rowView.findViewById(R.id.imageView);
            holder.tv.setText(data[position]);
//            holder.img.setImageResource(imageId[position]);

            rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+data[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }*/

    public class MyHolder extends RecyclerView.ViewHolder {
        protected TextView txt;
        protected ImageView img;

        private MyHolder(View v) {
            super(v);
//            Log.e("Adapter", "MyHolder Construction");
            this.txt = (TextView) v.findViewById(R.id.textView);
            this.img = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
