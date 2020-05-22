package com.idoporat.ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompletedTodoItemActivity extends AppCompatActivity {

    /** the completed TodoItem **/
    private TodoItem t;

    /** true if a dialog box is shown, false otherwise **/
    boolean dialogOn = false;

    /** In case a TodoItem was long-clicked and the device was rotated, we'll store the lonclicked
     * item in order to restore the dialog box **/
    TodoItem wasLongClicked = null;

    /** The Application running this activity **/
    private MyApp app;

    final static String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_todo);

        app = (MyApp) getApplicationContext();
        TextView content = (TextView)findViewById(R.id.done_content);
        Button undoButton = (Button)findViewById(R.id.undo_button);
        Button deleteButton = (Button)findViewById(R.id.delete_button);

        Intent callingIntent = getIntent();
        String id = callingIntent.getStringExtra(ID);
        app = (MyApp)getApplicationContext();
        t = app.getTodoItem(id);
        if(t != null){
            String s = getString(R.string.content_message, t.getDescription());
            content.setText(s);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t != null){
                    AlertDialog alert = createTodoDialog(t);
                    dialogOn = true;
                    alert.show();
                }
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t != null){
                    app.todoManager.markTodoItemAsUndone(t.getId(), getApplicationContext());
                }
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
    }

    /**
     * Creates a dialog box ( an AlertDialog ) for when a TodoItem is long-clicked.
     * @param t the long-clicked TodoItem.
     * @return the AlertDialog
     */
    private AlertDialog createTodoDialog(TodoItem t){
        wasLongClicked = t;
        final TodoItem tTag = t;
        AlertDialog.Builder builder = new AlertDialog.Builder(CompletedTodoItemActivity.this);
        builder.setMessage("Are You Sure to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        app.removeTodoItem(tTag.getId());
                        wasLongClicked = null;
                        dialogOn = false;
                        Intent intentBack = new Intent();
                        setResult(RESULT_OK, intentBack);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        wasLongClicked = null;
                        dialogOn = false;
                    }
                });
        return builder.create();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("dialogOn", dialogOn);
        outState.putString("wasLongClicked", wasLongClicked.getId());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restoring a dialog box, if one is presented on screen:
        dialogOn = savedInstanceState.getBoolean("dialogOn");
        wasLongClicked = app.todoManager.getTodoItem(savedInstanceState.getString("wasLongClicked"));
        if(dialogOn){
            AlertDialog alert = createTodoDialog(wasLongClicked);
            alert.show();
        }
    }
}
