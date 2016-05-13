package com.example.asus.projectdemo;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ThangDuong on 12-May-16.
 */
public class WordObjectsManager {
    private static WordObjectsManager ourInstance = new WordObjectsManager();

    public static WordObjectsManager getInstance() {
        return ourInstance;
    }

    private ArrayList<WordObject> objectArrayList;
    private static boolean isCorrectAll ;
    private WordObjectsManager() {
        isCorrectAll = false;
        objectArrayList = new ArrayList<>();
    }

    public void add(WordObject wordObject)
    {
        objectArrayList.add(wordObject);
    }

    public WordObject get(int position)
    {
        return objectArrayList.get(position);
    }

    public String[][] checkResult(String[][] data)
    {
        String[][] myResult = data;
        isCorrectAll = true;
        for(int i =0;i<objectArrayList.size();i++)
        {
            if(!objectArrayList.get(i).isCorrect(data))
            {
                myResult = objectArrayList.get(i).clearWrong(data);
                isCorrectAll = false;
            }
        }
        return  myResult;
    }

    public static boolean isCorrectAll() {
        return isCorrectAll;
    }

    public WordObject getObjectAt(int x,int y) {
        WordObject obj = null;
        for(int i =0;i<objectArrayList.size();i++)
        {
            if(objectArrayList.get(i).isInsideWord(x,y))
            {
                obj = objectArrayList.get(i);
            }
        }
        return obj;
    }

    public ArrayList<WordObject> getObjectArrayList() {
        return objectArrayList;
    }

    public void setObjectArrayList(ArrayList<WordObject> objectArrayList) {
        this.objectArrayList = objectArrayList;
    }
}
