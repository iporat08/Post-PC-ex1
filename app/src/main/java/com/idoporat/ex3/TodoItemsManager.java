package com.idoporat.ex3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class TodoItemsManager {
    private ArrayList<TodoItem> todoList;
    private static final String SP_TODO_LIST = "todoList";
    private Gson gson;
    private SharedPreferences sp;

    TodoItemsManager(Context context){
        gson = new Gson();
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(SP_TODO_LIST, "");
        Type type = new TypeToken<List<TodoItem>>(){}.getType();
        todoList = gson.fromJson(json, type);// todo what happens when list's empty
    }

    ArrayList<TodoItem> getTodoList(){
        return todoList;
    }

    void addTodoItem(TodoItem t, Context context){
        todoList.add(t);
        updateSP();
    }

    void removeTodoItem(TodoItem t, Context context){
        todoList.remove(t);
        updateSP();
    }

    private void updateSP(){
        SharedPreferences.Editor editor = sp.edit();
        String json = gson.toJson(todoList);
        editor.putString(SP_TODO_LIST, json).apply();
    }
}
