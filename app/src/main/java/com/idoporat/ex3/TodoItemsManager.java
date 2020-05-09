package com.idoporat.ex3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TodoItemsManager {
    public ArrayList<TodoItem> todoList;
    private static final String SP_TODO_LIST = "todoList";

    public TodoItemsManager(Context context){
        Gson gson = new Gson();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(SP_TODO_LIST, "");
        Type type = new TypeToken<List<TodoItem>>(){}.getType();
        todoList = gson.fromJson(json, type);
    }
}
