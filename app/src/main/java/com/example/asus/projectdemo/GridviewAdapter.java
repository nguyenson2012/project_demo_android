package com.example.asus.projectdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by ThangDuong on 12-May-16.
 */
public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private String data[][];//The board must be rectangular + data[x][y]
    private int color[][];//The board must be rectangular + data[x][y]
    public static final String DISABLE = "0";
    public static final int NORMAL = 1;
    public static final int MAIN_CLICKED = 2;
    public static final int SUB_CLICKED = 3;
    //Constructor to initialize values
    public GridviewAdapter(Context context, String[][] data) {

        this.context = context;
        this.data = data;
        color = new int[data.length][data[0].length];
        for(int i = 0;i<color.length;i++)
        {
            for(int j = 0;j<color[0].length;j++)//the board is rectangular
            {
                color[i][j]=NORMAL;
            }
        }
    }
    @Override
    public int getCount() {

        Log.e("GridviewAdapter","data.length = "
            +data.length+" , data[0].length = "+data[0].length);

        return data.length*data[0].length;//The board must be rectangular
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // LayoutInflater to call external grid_item.xml file

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);
            // get layout from grid_item.xml ( Defined Below )
            gridView = inflater.inflate( R.layout.adapter , null);
        } else {
            gridView = (View) convertView;

        }

        final Button cell = (Button)gridView.findViewById(R.id.button);
        cell.setText(Integer.toString(position));
        final int positionX = position%MainActivity.NUM_OF_COLLUMN;
        final int positionY = position/MainActivity.NUM_OF_COLLUMN;
        if(data[positionX][positionY] == DISABLE)//find the chosen cell, If can not compare, test with temp.equal(DISABLE)
        {
            cell.setVisibility(View.INVISIBLE);
        }
        else
        {
            cell.setVisibility(View.VISIBLE);
            cell.setText(data[positionX][positionY]);
        }

        //Check again : remove final cell, change the color data, require word length, onclick => return normal other cells, color next cells
        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cell.setBackgroundColor(Color.RED);
            }
        });
        return gridView;
    }
}
