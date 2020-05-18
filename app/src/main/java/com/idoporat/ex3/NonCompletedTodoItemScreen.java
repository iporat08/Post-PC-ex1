package com.idoporat.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class NonCompletedTodoItemScreen extends AppCompatActivity {
    private TodoItem t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_completed_todo_item_screen);

        TextView createdOn = (TextView)findViewById(R.id.created_on);
        TextView lastModified = (TextView)findViewById(R.id.last_modified);
        Button applyButton = (Button)findViewById(R.id.apply_button);
        final EditText contentEditor = (EditText)findViewById(R.id.content_editor);

        Intent callingIntent = getIntent();
        int id = callingIntent.getIntExtra("id", -1); //todo
        final MyApp app = (MyApp)getApplicationContext();
        t = app.getTodoItem(id);
        if(t != null){ //todo
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
            createdOn.setText(getString(R.string.created_on, formatter.format(t.getCreationTime()))); //todo
            lastModified.setText(getString(R.string.last_modified, formatter.format(t.getEditTimestamp())));
            contentEditor.setText(t.getDescription());
        }

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t != null){
                    t.setDescription(contentEditor.getText().toString());
                    app.todoManager.updateTodoList(); // todo
                }
                finish();
            }
        });



    }
}
