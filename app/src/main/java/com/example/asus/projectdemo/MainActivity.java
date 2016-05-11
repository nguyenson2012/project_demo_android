package com.example.asus.projectdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static final int NUM_OF_COLLUMN = 5;
    public static final int NUM_OF_ROW = 6;
    private GridView gridView;
    private TextView txtView_question;
    private ImageView imgView_question;
    Context context;
    private KeyboardButton[] keyboard_btn;
    static String [][] myDataset = new String[NUM_OF_COLLUMN][NUM_OF_ROW];//myDataset[x][y]
    private TextView testKeyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        setupKeyboard();
        setOnTouchKeyboard();
        setupData();
        testKeyboard = (TextView)findViewById(R.id.testKeyboard);
        testKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testKeyboard.setText("");
            }
        });

        txtView_question = (TextView)findViewById(R.id.textView);
        imgView_question = (ImageView)findViewById(R.id.imageView);

        gridView = (GridView) findViewById(R.id.layout_gridview);
        gridView.setAdapter(new GridviewAdapter(getApplicationContext(),myDataset));
        gridView.setNumColumns(NUM_OF_COLLUMN);
    }

    private void setupKeyboard()
    {
        keyboard_btn = new KeyboardButton[26];
        keyboard_btn[0]= new KeyboardButton((Button)findViewById(R.id.btnQ),"Q");
        keyboard_btn[1]= new KeyboardButton((Button)findViewById(R.id.btnW),"W");
        keyboard_btn[2]= new KeyboardButton((Button)findViewById(R.id.btnE),"E");
        keyboard_btn[3]= new KeyboardButton((Button)findViewById(R.id.btnR),"R");
        keyboard_btn[4]= new KeyboardButton((Button)findViewById(R.id.btnT),"T");
        keyboard_btn[5]= new KeyboardButton((Button)findViewById(R.id.btnY),"Y");
        keyboard_btn[6]= new KeyboardButton((Button)findViewById(R.id.btnU),"U");
        keyboard_btn[7]= new KeyboardButton((Button)findViewById(R.id.btnI),"I");
        keyboard_btn[8]= new KeyboardButton((Button)findViewById(R.id.btnO),"O");
        keyboard_btn[9]= new KeyboardButton((Button)findViewById(R.id.btnP),"P");
        keyboard_btn[10]= new KeyboardButton((Button)findViewById(R.id.btnA),"A");
        keyboard_btn[11]= new KeyboardButton((Button)findViewById(R.id.btnS),"S");
        keyboard_btn[12]= new KeyboardButton((Button)findViewById(R.id.btnD),"D");
        keyboard_btn[13]= new KeyboardButton((Button)findViewById(R.id.btnF),"F");
        keyboard_btn[14]= new KeyboardButton((Button)findViewById(R.id.btnG),"G");
        keyboard_btn[15]= new KeyboardButton((Button)findViewById(R.id.btnH),"H");
        keyboard_btn[16]= new KeyboardButton((Button)findViewById(R.id.btnJ),"J");
        keyboard_btn[17]= new KeyboardButton((Button)findViewById(R.id.btnK),"K");
        keyboard_btn[18]= new KeyboardButton((Button)findViewById(R.id.btnL),"L");
        keyboard_btn[19]= new KeyboardButton((Button)findViewById(R.id.btnZ),"Z");
        keyboard_btn[20]= new KeyboardButton((Button)findViewById(R.id.btnX),"X");
        keyboard_btn[21]= new KeyboardButton((Button)findViewById(R.id.btnC),"C");
        keyboard_btn[22]= new KeyboardButton((Button)findViewById(R.id.btnV),"V");
        keyboard_btn[23]= new KeyboardButton((Button)findViewById(R.id.btnB),"B");
        keyboard_btn[24]= new KeyboardButton((Button)findViewById(R.id.btnN),"N");
        keyboard_btn[25]= new KeyboardButton((Button)findViewById(R.id.btnM),"M");
    }

    private void setOnTouchKeyboard()
    {
        for(int i = 0;i<keyboard_btn.length;i++)
        {
            setOnClickEachButton(keyboard_btn[i]);
        }
    }

    private void setOnClickEachButton(final KeyboardButton kbtn)
    {
        kbtn.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testKeyboard.setText(testKeyboard.getText() + kbtn.key);
            }
        });
    }

    private void setupData()
    {
        for(int i = 0;i<myDataset.length;i++)
        {
            for(int j = 0;j<myDataset[0].length;j++)//the board is rectangular
            {
                myDataset[i][j]="A";
            }
        }
        myDataset[2][4] = GridviewAdapter.DISABLE;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class KeyboardButton
    {
        Button button;
        String key;
        KeyboardButton(Button btn,String k)
        {
            button = btn;
            key = k;
        }
    }
}
