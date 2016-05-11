package com.example.asus.projectdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RecyclerView mRecycleView;
    Context context;
    private KeyboardButton[] keyboard_btn;
    static String [] myDataset = {
            "Test text 1",
            "Test text 2",
            "Test text 3",
            "Test text 4",
            "Test text 5",
            "Test text 6",
    };
    private TextView testKeyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mRecycleView = (RecyclerView) findViewById(R.id.layout_recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(new MyRecyclerAdapter(this, myDataset));

        setupKeyboard();
        setOnTouchKeyboard();
        testKeyboard = (TextView)findViewById(R.id.testKeyboard);
        testKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testKeyboard.setText("");
            }
        });
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
            setOnClickButton(keyboard_btn[i]);
        }
    }

    private void setOnClickButton(final KeyboardButton kbtn)
    {
        kbtn.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testKeyboard.setText(testKeyboard.getText() + kbtn.key);
            }
        });
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
