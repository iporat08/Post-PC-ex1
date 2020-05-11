package com.idoporat.ex3;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * A class representing a task to be done
 */
class TodoItemHolder extends RecyclerView.ViewHolder {
    ///////////////////////////////////// data members /////////////////////////////////////////////
    /**
     * A String describing the task to be done.
     **/
    final private TextView todoMessage;

    ///////////////////////////////////// constructors /////////////////////////////////////////////

    /**
     * Constructor
     *
     * @param v - a view to be held by the TodoItemHolder
     */
    TodoItemHolder(View v) {
        super(v);
        todoMessage = v.findViewById(R.id.todo_item_text);
    }

    ///////////////////////////////////// getters //////////////////////////////////////////////////

    /**
     * @return the todoMessage of the holder (and the TodoItem)
     */
    TextView getTodoMessage() {
        return todoMessage;
    }

    ///////////////////////////////////// setters //////////////////////////////////////////////////

    /**
     * Marks the todoItem as done by marking its' text as strikethrough.
     */
    void deleteText() {
        todoMessage.setPaintFlags(todoMessage.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    void unDeleteText(){
        todoMessage.setPaintFlags(todoMessage.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
    }
}

