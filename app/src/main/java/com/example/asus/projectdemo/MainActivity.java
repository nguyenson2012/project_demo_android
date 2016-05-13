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
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final int NUM_OF_COLLUMN = 5;
    public static final int NUM_OF_ROW = 6;
    private GridView gridView;
    private TextView txtView_question;
    private ImageView imgView_question;
    private Context context;
    private KeyboardButton[] keyboard_btn;
    private String [][] gridViewData = new String[NUM_OF_COLLUMN][NUM_OF_ROW];//gridViewData[x][y]
    private WordObjectsManager objManger = WordObjectsManager.getInstance();
    private GridviewAdapter adapter;

    private Button btCheckAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        setupGridView();
        adapter = new GridviewAdapter(getApplicationContext(), gridViewData);
        gridView = (GridView) findViewById(R.id.layout_gridview);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(NUM_OF_COLLUMN);

        setupKeyboard();
        setOnTouchKeyboard();

        txtView_question = (TextView)findViewById(R.id.textViewQuestion);
        imgView_question = (ImageView)findViewById(R.id.imageView);
        btCheckAnswer=(Button)findViewById(R.id.btcheckAnswer);
        btCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer())
                    Toast.makeText(getApplicationContext(),"You Win",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"You Lose",Toast.LENGTH_SHORT).show();
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
            setOnClickEachButton(keyboard_btn[i]);
        }
//        adapter.notifyDataSetChanged();
    }

    private void setOnClickEachButton(final KeyboardButton kbtn)
    {
        kbtn.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView_question.setText(txtView_question.getText() + kbtn.key);
                try {
                    gridViewData[WordObject.getClickedPositionX()][WordObject.getClickedPositionY()]=kbtn.key;
                    adapter.nextClickedPosition();
                    adapter.notifyDataSetChanged();
                }
                catch (Exception e)
                {
                    Log.e("MainActivity","Not Clicked any cell yet.");
                }
            }
        });
    }

    private void initializeQuestion()
    {
        objManger.add(new WordObject(0,0,"I am ...... a book","READ",WordObject.HORIZONTAL));
        objManger.add(new WordObject(0,2,"..... up!","STAND",WordObject.HORIZONTAL));
        objManger.add(new WordObject(1, 0, "I ... a humberger", "EAT", WordObject.VERTICAL));
    }
    private void setupGridView()
    {
        initializeQuestion();
        //Reset gridview
        for(int i = 0;i< gridViewData.length;i++)
        {
            for(int j = 0;j< gridViewData[0].length;j++)//the board is rectangular
            {
                WordObject temp = objManger.getObjectAt(i,j);
                if(temp!=null)
                {
                    gridViewData[i][j]=GridviewAdapter.ENABLE;
//                    Log.e("OBJ","i,j = "+i+" , "+j);
                }
                else
                {
                    gridViewData[i][j]=GridviewAdapter.DISABLE;
//                    Log.e("NULL","i,j = "+i+" , "+j);
                }
            }
        }
    }
    private boolean checkAnswer(){
        boolean checkAnswer=true;
        for(WordObject question:objManger.getObjectArrayList()){
            String answer="";
            int firstX=question.startX;
            int firstY=question.startY;
            if(question.getOrientation()==WordObject.HORIZONTAL){
                for(int i=0;i<question.getResult().length();i++){
                    answer=answer.concat(gridViewData[firstX+i][firstY]);
                }
                if(!answer.equals(question.getResult()))
                    checkAnswer=false;
            }else {
                for(int i=0;i<question.getResult().length();i++){
                    answer=answer.concat(gridViewData[firstX][firstY+i]);
                }
                if(!answer.equals(question.getResult()))
                    checkAnswer=false;
            }

        }
        return checkAnswer;
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
