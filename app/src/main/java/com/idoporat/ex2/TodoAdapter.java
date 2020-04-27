package com.idoporat.ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter {

    private ArrayList<TodoItem> todoList;
    private TodoClickListener todoClickListener;

    public TodoAdapter(ArrayList<TodoItem> todoList){
        this.todoList = todoList;
    }

    public void setTodoItems(ArrayList<TodoItem> todoList){
//        this.todoList.clear(); // todo might be needed?
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void setTodoClickListener(TodoClickListener t){
        this.todoClickListener = t;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false); //TODO understand
        return new TodoItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //TODO this is a place to check if item is done or not yet
        final TodoItemHolder h = (TodoItemHolder) holder; //TODO needed?
        final TodoItem todoItem= todoList.get(position);

        h.getTodoMessage().setText(todoItem.getTodoMessage());
        if(todoItem.isDone()){
            h.deleteText();
        }

        h.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(todoClickListener != null){
                    todoClickListener.onTodoClick(todoItem);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
