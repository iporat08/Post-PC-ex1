package com.idoporat.ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /** The message in the EditText **/
    private String inputMessage;

    /** The MainActivity's EditText into which new TodoItem's messages will be written**/
    private EditText inputEditor;

    /** A list of TodoItems **/
    private ArrayList<TodoItem> todoList = new ArrayList<TodoItem>();

    /** The MainActivity's Adapter **/
    private TodoAdapter adapter = new TodoAdapter(todoList);

    /** The MainActivity's LayoutManager **/
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    /** The Application running this activity **/
    private MyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (MyApp) getApplicationContext();
        todoList = app.todoManager.getTodoList();
        adapter.setTodoItems(todoList);
        RecyclerView todoRecycler = findViewById(R.id.todo_recycler);
        todoRecycler.setAdapter(adapter);
        todoRecycler.setLayoutManager(layoutManager);
        final Button createButton = (Button)findViewById(R.id.createButton);
        inputMessage = getString(R.string.initial_input_string);
        inputEditor = (EditText)findViewById(R.id.textInput);
        inputEditor.setText(inputMessage);
        createButton.setOnClickListener(new ButtonListener());
        adapter.setTodoClickListener(new TodoClickListener());
    }

    /**
     * A class implementing an interface whose purpose is to enable the adapter pass information to
     * the MainActivity
     */
    class TodoClickListener implements com.idoporat.ex3.TodoClickListener {
        @Override
        public void onTodoClick(TodoItem t) {
            if (!t.isDone()) {
                int duration = Snackbar.LENGTH_SHORT;
                String done_message = getString(R.string.done_message)
                        .replace("userMessage", t.getDescription());
                Snackbar.make(findViewById(R.id.todo_recycler), done_message, duration).show();
                t.setIsDone();
                adapter.setTodoItems(todoList);
                app.updateTodoList(todoList);
            }
        }

        @Override
        public void onTodoLongClick(TodoItem t) {
            final TodoItem tTag = t;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are You Sure to delete?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            app.removeTodoItem(tTag);
                            todoList.remove(tTag);
                            adapter.setTodoItems(todoList);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    /**
     * A listener for the create button.
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(inputEditor.getText().toString().equals("")){
                int duration =  Snackbar.LENGTH_SHORT;
                String emptyTodoMessage = getString(R.string.empty_todo_message);
                Snackbar.make(v, emptyTodoMessage, duration).show();
            }
            else{
                TodoItem itemToAdd = new TodoItem(inputEditor.getText().toString());
                adapter.setTodoItems(todoList);
                inputEditor.setText(getString(R.string.initial_input_string));
                inputMessage = getString(R.string.initial_input_string);
                app.addTodoItem(itemToAdd);
            }
        }
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