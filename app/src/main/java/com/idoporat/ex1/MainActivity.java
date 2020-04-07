package com.idoporat.ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    static String inputMessage;
    private EditText inputEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button createButton = (Button)findViewById(R.id.createButton);
        inputMessage = getString(R.string.initial_input_string);
        inputEditor = (EditText)findViewById(R.id.textInput);
        inputEditor.setText(inputMessage);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(inputEditor.getText().toString().equals("")){
                    int duration =  Snackbar.LENGTH_SHORT;
                    String empty_toodo_message = getString(R.string.empt_toodo_message);
                    Snackbar.make(createButton, empty_toodo_message, duration).show();
                }
                else{
                    inputEditor.setText(getString(R.string.initial_input_string));
                    inputMessage = getString(R.string.initial_input_string);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("inputMessage", inputMessage);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("inputMessage");
    }
}
