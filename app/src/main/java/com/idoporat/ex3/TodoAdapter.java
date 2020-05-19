package com.idoporat.ex3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * An adapter class
 */
public class TodoAdapter extends RecyclerView.Adapter {

    ///////////////////////////////////// data members /////////////////////////////////////////////
    /** An arrayList of TodoItems, holds the tasks to be done (and those who were already done)**/
    private ArrayList<TodoItem> todoList;

    /** An instance of a class that implements the TodoClickListener interface, in order for the
     *  adapter to pass information to the MainActivity**/
    private TodoClickListener todoClickListener;

    ///////////////////////////////////// constructors /////////////////////////////////////////////
    /**
     * Constructor
     * @param todoList An arrayList of TodoItems, holds the tasks to be done (and those already done)
     */
    TodoAdapter(ArrayList<TodoItem> todoList){
        this.todoList = todoList;
    }

    ///////////////////////////////////// setters //////////////////////////////////////////////////
    /**
     * Sets the TodoAdapter's todoList field to be the input parameter todoList
     * @param todoList An arrayList of TodoItems, holds the tasks to be done (and those already done)
     */
    void setTodoItems(ArrayList<TodoItem> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    /**
     * Sets the TodoAdapter's todoClickListener field to be the input parameter t.
     * @param t - An instance of a class that implements the TodoClickListener interface.
     */
    void setTodoClickListener(TodoClickListener t){
        this.todoClickListener = t;
    }

    /////////////////////////// RecyclerView.Adapter related methods ///////////////////////////////
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context)
                        .inflate(R.layout.todo_item, parent, false);
        return new TodoItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final TodoItemHolder h = (TodoItemHolder) holder;
        final TodoItem todoItem= todoList.get(position);

        if(todoItem.isDone()){
            h.deleteText();
        }
        else{
            h.unDeleteText();
        }
        h.getTodoMessage().setText(todoItem.getDescription());

        h.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
