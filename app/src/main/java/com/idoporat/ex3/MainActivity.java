package com.idoporat.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /** The message in the EditText **/
    private String inputMessage;
    private EditText inputEditor;
    private ArrayList<TodoItem> todoList = new ArrayList<TodoItem>();
    private TodoAdapter adapter = new TodoAdapter(todoList);
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter.setTodoItems(todoList);

        RecyclerView todoRecycler = findViewById(R.id.todo_recycler);
        todoRecycler.setAdapter(adapter);
        todoRecycler.setLayoutManager(layoutManager);


        final Button createButton = (Button)findViewById(R.id.createButton);
        inputMessage = getString(R.string.initial_input_string);
        inputEditor = (EditText)findViewById(R.id.textInput);
        inputEditor.setText(inputMessage);

        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(inputEditor.getText().toString().equals("")){
                    int duration =  Snackbar.LENGTH_SHORT;
                    String emptyTodoMessage = getString(R.string.empty_todo_message);
                    Snackbar.make(createButton, emptyTodoMessage, duration).show();
                }
                else{
                    todoList.add(new TodoItem(inputEditor.getText().toString()));
                    adapter.setTodoItems(todoList);
                    inputEditor.setText(getString(R.string.initial_input_string));
                    inputMessage = getString(R.string.initial_input_string);
                }
            }
        });

        adapter.setTodoClickListener(new TodoClickListener() {
            @Override
            public void onTodoClick(TodoItem t) {
                if(!t.isDone()) {
                    int duration =  Snackbar.LENGTH_SHORT;
                    String done_message = getString(R.string.done_message)
                            .replace("userMessage", t.getDescription());
                    Snackbar.make(findViewById(R.id.todo_recycler), done_message, duration).show();

                }
                t.setIsDone();
                adapter.setTodoItems(todoList);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputMessage", inputMessage);
        outState.putParcelableArrayList("todoList", todoList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputMessage = savedInstanceState.getString("inputMessage");
        todoList = savedInstanceState.getParcelableArrayList("todoList");
        adapter.setTodoItems(todoList);
    }
}
