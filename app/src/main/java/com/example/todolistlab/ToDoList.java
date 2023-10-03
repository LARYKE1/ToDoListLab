package com.example.todolistlab;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    public static final String FILENAME ="todolist.txt";
    private Context mContext;
    private List<String> mTaskList;


    public ToDoList(Context mContext) {
        this.mContext = mContext;
        mTaskList= new ArrayList<>();
    }

    public void addItem(String item) throws IllegalArgumentException{
        mTaskList.add(item);
    }

    public String[] getItems(){
        String[]items=new String[mTaskList.size()];
        return mTaskList.toArray(items);
    }

    public void clear(){
        mTaskList.clear();
    }

    public void save() throws IOException {
        try {
            FileOutputStream outputStream=mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            writeListToStream(outputStream);
            Toast.makeText(mContext, "The content is saved to the"+mContext.getFilesDir()+"/n"+FILENAME, Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void load() throws IOException{
        FileInputStream inputStream=mContext.openFileInput(FILENAME);
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream))){
            mTaskList.clear();
            StringBuilder sb=new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                mTaskList.add(line);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void writeListToStream(FileOutputStream outputStream){
        PrintWriter writer=new PrintWriter(outputStream);
        for(String item: mTaskList){
            writer.println(item);
        }
        writer.close();
    }
}
