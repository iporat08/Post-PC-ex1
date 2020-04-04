package com.idoporat.ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String inputMessage = " ";
    static String textViewMessage = "Type below and press CREATE!";
    private TextView textView;
    private EditText inputEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        inputEditor = (EditText)findViewById(R.id.textInput);
        Button createButton = (Button)findViewById(R.id.createButton);
        textView.setText(textViewMessage);
        inputEditor.setText(inputMessage);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textView.setText(inputEditor.getText().toString());
                textViewMessage = inputEditor.getText().toString();
                inputEditor.setText(getString(R.string.initial_input_string));
                inputMessage = getString(R.string.initial_input_string);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("textView", textViewMessage);
        outState.putString("inputMessage", inputMessage);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("textView");
        savedInstanceState.getString("inputMessage");
    }
}
