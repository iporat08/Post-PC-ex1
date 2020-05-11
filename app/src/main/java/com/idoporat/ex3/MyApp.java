package com.idoporat.ex3;

import android.app.Application;

public class MyApp extends Application {

    public TodoItemsManager todoManager;

    public void onCreate(){
        super.onCreate();

        todoManager = new TodoItemsManager(this);
        int len = todoManager.getTodoList().size();
        android.util.Log.i("MyApp", "Current size of TODOs list: " + len); // todo - maybe  android.util.Log.i
    }

    public void addTodoItem(TodoItem t){
        todoManager.addTodoItem(t, this);
    }

    public void removeTodoItem(TodoItem t){
        todoManager.removeTodoItem(t, this);
    }
}
