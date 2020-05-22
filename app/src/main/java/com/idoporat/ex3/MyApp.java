package com.idoporat.ex3;

import android.app.Application;

import java.util.ArrayList;

/** A class inherits Application  */
public class MyApp extends Application {

    //////////////////////////////////////// data members //////////////////////////////////////////
    /** A list of TodoItems **/
    public TodoItemsManager todoManager;

    /** The prefix of the message which will be logged with every launch of the app **/
    private static final String SIZE_MESSAGE_PREFIX = "Current size of TODOs list: ";

    /////////////////////////////////// Application methods ////////////////////////////////////////
    public void onCreate(){
        super.onCreate();

        todoManager = new TodoItemsManager(this);
        int len = todoManager.getTodoList().size();
        android.util.Log.i("MyApp", SIZE_MESSAGE_PREFIX + len);
    }
    //////////////////////////////////////// setters ///////////////////////////////////////////////
    public TodoItem getTodoItem(String id){
        return todoManager.getTodoItem(id);
    }

    //////////////////////////////////////// setters ///////////////////////////////////////////////

    /**
     * Instructs the app's TodoManager instance to add t to its' todoList
     * @param description the description of the new TodoItem
     */
    public void addTodoItem(String description){//TodoItem t){
        todoManager.addTodoItem(description);
    }

    /**
     * Instructs the app's TodoManager instance to remove t to its' todoList.
     * @param id the id of the TodoItem to be removed.
     */
    public void removeTodoItem(String id){
        todoManager.removeTodoItem(id);
    }

    /**
     * Instructs the app's TodoManager instance to replace its' todoList with list.
     * @param list the new todoList.
     */
    public void setTodoList(ArrayList<TodoItem> list){
        todoManager.setTodoList(list);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
