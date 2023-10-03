package com.example.todolistlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ToDoList mToDoList;
    private EditText mItemEdit;
    private TextView mItemListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemEdit=findViewById(R.id.todo_item);
        mItemListText=findViewById(R.id.item_list);

        findViewById(R.id.add_button).setOnClickListener(view ->
                addButtonClick());
        findViewById(R.id.clear_button).setOnClickListener(view ->
                clearButton());

        mToDoList=new ToDoList(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mToDoList.load();
            displayList();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mToDoList.save();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }}


    private void addButtonClick(){
        String item=mItemEdit.getText().toString().trim();
        mItemEdit.setText("");

        if(item.length()>0){
            mToDoList.addItem(item);
            displayList();
        }
    }

    private void displayList(){
        StringBuffer itemText=new StringBuffer();
        String[] items=mToDoList.getItems();
        for(int i=0;i<items.length;i++){
            itemText.append(i+1).append(". ").append(items[i]).append("\n");
        }

        mItemListText.setText(itemText);
    }

    private void clearButton(){
        mToDoList.clear();
        displayList();
    }


}