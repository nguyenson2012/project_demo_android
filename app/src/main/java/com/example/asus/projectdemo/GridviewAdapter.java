package com.example.asus.projectdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by ThangDuong on 12-May-16.
 */
public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private String data[][];//The board must be rectangular + data[x][y]
    private static int color[][];//The board must be rectangular + data[x][y]
    public static final String DISABLE = "0";
    public static final String ENABLE = "";
    public static final int NORMAL = 2;
    public static final int MAIN_CLICKED = 3;
    public static final int SUB_CLICKED = 4;
    private static WordObject clickedOject = null;
    //Constructor to initialize values
    WordObjectsManager objManager = WordObjectsManager.getInstance();
    public GridviewAdapter(Context context, String[][] data) {

        this.context = context;
        this.data = data;
        color = new int[data.length][data[0].length];
        resetColor();
    }
    public void resetColor()
    {
        for(int i = 0;i<color.length;i++)
        {
            for(int j = 0;j<color[0].length;j++)//the board is rectangular
            {
                color[i][j]=NORMAL;
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
//        Log.e("GridviewAdapter","data.length = "+data.length+" , data[0].length = "+data[0].length);
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
        //set Row Height
        cell.setHeight(MainActivity.getRowHeight());
        cell.setMinimumHeight(MainActivity.getRowHeight());

//        cell.setText(Integer.toString(position));
        final int positionX = position%MainActivity.NAM_OF_COLUMN;
        final int positionY = position/MainActivity.NAM_OF_COLUMN;
        if(data[positionX][positionY] == DISABLE)//find the chosen cell, If can not compare, test with temp.equal(DISABLE)
        {
            cell.setVisibility(View.INVISIBLE);
        }
        else
        {
            cell.setVisibility(View.VISIBLE);
            cell.setText(data[positionX][positionY]);
        }

        if(color[positionX][positionY] == NORMAL)
        {
            cell.setBackgroundColor(Color.GRAY);
        }
        else if(color[positionX][positionY] == MAIN_CLICKED)
        {
            cell.setBackgroundColor(Color.RED);
        }
        else if(color[positionX][positionY] == SUB_CLICKED)
        {
            cell.setBackgroundColor(Color.BLUE);
        }

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCell(positionX,positionY);
            }
        });
        return gridView;
    }

    private void onClickCell(int x,int y)
    {
        //Reset color of the grid
        resetColor();

        WordObject obj = objManager.getObjectAt(x, y);
        if(obj != null)// If clicked inside a word
        {
            obj.setClickedPosition(x,y);
            clickedOject = obj;
            colorSurroundCells(x,y);
//            Log.e("Adapter", "Clicked Word Cell; x,y = "+x+" , "+y);
        }
//        else
//        {
//            Log.e("Adapter","Clicked Blank Cell; x,y = "+x+" , "+y);
//            resetColor();
//        }
    }

    private void colorSurroundCells(int x, int y)// x,y is the starting point, cell is the work obj to check its length
    {
        color[x][y]=MAIN_CLICKED;
        int tempX=x+1;// color the subclicked on the right
        while (tempX<data.length&&clickedOject.isInsideWord(tempX,y))
        {
            color[tempX][y]=SUB_CLICKED;
            tempX++;
        }
        tempX=x-1;// color the subclicked on the right
        while (tempX>=0&&clickedOject.isInsideWord(tempX,y))
        {
            color[tempX][y]=SUB_CLICKED;
            tempX--;
        }

        int tempY=y+1;// color the subclicked downward
        while (tempY<data[0].length&&clickedOject.isInsideWord(x,tempY))
        {
            color[x][tempY]=SUB_CLICKED;
            tempY++;
        }
        tempY=y-1;// color the subclicked upward
        while (tempY>=0&&clickedOject.isInsideWord(x,tempY))
        {
            color[x][tempY]=SUB_CLICKED;
            tempY--;
        }
        notifyDataSetChanged();
    }

    public void nextClickedPosition()
    {
        int lastClickedX=WordObject.getClickedPositionX();
        int lastClickedY=WordObject.getClickedPositionY();
        //Horizontal and stop at last digit
        if(clickedOject.getOrientation()==WordObject.HORIZONTAL
                &&WordObject.getClickedPositionX()<=clickedOject.startX+clickedOject.getResult().length()-2)
        {
            lastClickedX++;
        }

        //Vertical and stop at last digit
        if(clickedOject.getOrientation()==WordObject.VERTICAL
                &&WordObject.getClickedPositionY()<=clickedOject.startY+clickedOject.getResult().length()-2)
        {
            lastClickedY++;
        }
        clickedOject.setClickedPosition(lastClickedX, lastClickedY);

//        onClickCell(WordObject.getClickedPositionX(), WordObject.getClickedPositionY());

        colorSurroundCells(lastClickedX,lastClickedY);
    }
}
