package com.idoporat.ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;

public class UncompletedTodoItemActivity extends AppCompatActivity {

    /** the non-completed TodoItem **/
    private TodoItem t;

    /** the EditText in which the user can edit the TodoItem's description **/
    private EditText contentEditor;

    final static String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_completed_todo_item_screen);

        TextView createdOn = (TextView)findViewById(R.id.created_on);
        TextView lastModified = (TextView)findViewById(R.id.last_modified);
        Button applyButton = (Button)findViewById(R.id.apply_button);
        Button deletionButton = (Button)findViewById(R.id.deletion_button);
        contentEditor = (EditText)findViewById(R.id.content_editor);

        Intent callingIntent = getIntent();
        String id = callingIntent.getStringExtra(ID);
        final MyApp app = (MyApp)getApplicationContext();
        t = app.getTodoItem(id);
        if(t != null){
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyy 'at' HH:mm:ss");
            createdOn.setText(getString(R.string.created_on, formatter.format(t.getCreationTimestamp())));
            String new_edit_timestamp = formatter.format(t.getEditTimestamp());
            lastModified.setText(getString(R.string.last_modified, new_edit_timestamp));
            contentEditor.setText(t.getDescription());
        }

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t != null){
                    final Context context = UncompletedTodoItemActivity.this;
                    String description = contentEditor.getText().toString();
                    if(description.toString().equals("")){
                        int duration =  Snackbar.LENGTH_SHORT;
                        String emptyTodoMessage = getString(R.string.empty_todo_message);
                        Snackbar.make(v, emptyTodoMessage, duration).show();
                    }
                    else{
                        app.todoManager.setTodoItemsDescription(t.getId(), description, context);
                        int duration =  Snackbar.LENGTH_SHORT;
                        Snackbar.make(findViewById(R.id.created_on),
                                getString(R.string.description_changed), duration).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String description = contentEditor.getText().toString();
                                app.todoManager.setTodoItemsDescription(t.getId(), description, context);
                                finish();
                            }
                        }, 2000);
                    }
                }
            }
        });

        deletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t != null){
                   Context context = UncompletedTodoItemActivity.this;
                   app.todoManager.markTodoItemAsDone(t.getId(), context);
                }
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String inputMessage = contentEditor.getText().toString();
        outState.putString("inputMessage", inputMessage);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contentEditor.setText(savedInstanceState.getString("inputMessage"));
    }
}
