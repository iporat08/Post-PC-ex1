package com.idoporat.ex2;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TodoItemHolder extends RecyclerView.ViewHolder{
    final private TextView todo_message;
    public TodoItemHolder(View v){
        super(v);
        todo_message = v.findViewById(R.id.todo_item_text);
    }

    public TextView getTodoMessage() {
        return todo_message;
    }

    public void deleteText() {
        todo_message.setPaintFlags(todo_message.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
