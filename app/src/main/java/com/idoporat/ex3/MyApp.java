package com.idoporat.ex3;

import android.app.Application;

public class MyApp extends Application {

    public TodoItemsManager todoManager;

    public void onCreate(){
        super.onCreate();

        todoManager = new TodoItemsManager(this);
        int len = todoManager.
        android.util.Log.d("MyApp", "Current size of TODOs list:" + len)
    }
}
