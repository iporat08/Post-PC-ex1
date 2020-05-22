package com.idoporat.ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    private TodoAdapter adapter;

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
        adapter = app.todoManager.getAdapter();
//        adapter.setTodoItems(app.todoManager.getAdapter());
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
            if (t.getIsDone()) {
                Intent finishedTodoIntent = new Intent(getApplicationContext(),
                        CompletedTodoActivity.class); // todo
                finishedTodoIntent.putExtra("id", t.getId());
                startActivity(finishedTodoIntent);
            }
            else{
                Intent unfinishedTodoIntent = new Intent(getApplicationContext(),
                                                            NonCompletedTodoItemScreen.class); // todo
                unfinishedTodoIntent.putExtra("id", t.getId());
                startActivity(unfinishedTodoIntent);
            }
        }

        @Override
        public void onTodoLongClick(TodoItem t) {
            int a = 1;
            return;
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
                app.addTodoItem(inputEditor.getText().toString());
                adapter.setTodoItems(todoList);
                inputEditor.setText(getString(R.string.initial_input_string));
                inputMessage = getString(R.string.initial_input_string);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        app.todoManager.updateData();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputMessage", inputMessage);
//        outState.putParcelableArrayList("todoList", todoList); // todo will not survive this way
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputMessage = savedInstanceState.getString("inputMessage");
//        todoList = savedInstanceState.getParcelableArrayList("todoList");
        adapter.setTodoItems(todoList);
    }
}
