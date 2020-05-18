package com.idoporat.ex3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A class that holds and manages a todoList for the app.
 */
class TodoItemsManager {
    //////////////////////////////////////// data members //////////////////////////////////////////
    /** A list of TodoItems **/
    private ArrayList<TodoItem> todoList;

    /** The key of the json of the todoList **/
    private static final String SP_TODO_LIST = "todoList";

    /** A Gson object of the class **/
    private static Gson gson;

    /** A SharedPreferences object of the class **/
    private static SharedPreferences sp;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A constructor
     * @param context the context of the instance.
     */
    TodoItemsManager(Context context){
        gson = new Gson();
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(SP_TODO_LIST, "");
        Type type = new TypeToken<List<TodoItem>>(){}.getType();
        todoList = gson.fromJson(json, type);
    }

    /**
     * Updates the SP with the TodoManager's data.
     */
    private void updateSP(){
        SharedPreferences.Editor editor = sp.edit();
        String json = gson.toJson(todoList);
        editor.putString(SP_TODO_LIST, json).apply();
    }

    //////////////////////////////////////// getters ///////////////////////////////////////////////

    /** returns the todoList **/
    ArrayList<TodoItem> getTodoList(){
        return todoList;
    }

    TodoItem getTodoItem(int id){
        for(TodoItem t : todoList){ //todo - temp, should change after firebase
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    //////////////////////////////////////// setters ///////////////////////////////////////////////

    /**
     * Adds a TodoItem to the todoList.
     * @param t the TodoItem to be added.
     */
    void addTodoItem(TodoItem t){
        todoList.add(t);
        updateSP();
    }

    /**
     * Removes a TodoItem to the todoList.
     * @param t the TodoItem to be removed.
     */
    void removeTodoItem(TodoItem t){
        todoList.remove(t);
        updateSP();
    }

    /**
     * Replaces todoList with a new todoList.
     * @param list the new todoList.
     */
    void setTodoList(ArrayList<TodoItem> list){
        todoList = list;
        updateSP();
    }

    /**
     * todo
     */
    void updateTodoList(){
        updateSP();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
