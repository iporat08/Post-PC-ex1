package com.idoporat.ex3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    /** todo **/
    private static final String SP_HIGHEST_ID = "highestId";

    /** A Gson object of the class **/
    private static Gson gson;

    /** A SharedPreferences object of the class **/
    private static SharedPreferences sp;

    /** the highest id of a TodoItem that was created**/
    private int highestId;
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
        highestId = sp.getInt(SP_HIGHEST_ID, 0);
    }

    /**
     * Updates the SP with the TodoManager's data.
     */
    private void updateSP(){
        SharedPreferences.Editor editor = sp.edit();
        String json = gson.toJson(todoList);
        editor.putString(SP_TODO_LIST, json).putInt(SP_HIGHEST_ID, highestId).apply();
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
     * @param description the TodoItems' description
     */
    void addTodoItem(String description){//TodoItem t){
        TodoItem t = new TodoItem(description, highestId);
        ++highestId;
        todoList.add(t);
        updateSP();
    }

    /**
     * Removes a TodoItem to the todoList.
     * @param id the id of the TodoItem to be removed.
     */
    void removeTodoItem(int id){
        TodoItem t = getTodoItem(id);
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
     * Marks a TodoItem as undone
     * @param id the id of the TodoItem to be marked as done
     */
    void markTodoItemAsDone(int id){
        TodoItem t = getTodoItem(id);
        t.markAsDone();
        updateTodoItem(t);
    }

    /**
     * Changes th description of a TodoItem
     * @param id the id of the TodoItem to be edited
     * @param description the new description
     */
    void setTodoItemsDescription(int id, String description){
        TodoItem t = getTodoItem(id);
        t.setDescription(description);
        updateTodoItem(t);
    }

    /**
     * Marks a TodoItem as undone
     * @param id the id of the TodoItem to be marked as undone
     */
    void markTodoItemAsUndone(int id){
        TodoItem t = getTodoItem(id);
        t.markUndone();
        updateTodoItem(t);
    }

    /**
     * updates t's editTimeStamp and updates the SP
     * @param t the TodoItm to be updated
     */
    private void updateTodoItem(TodoItem t){
        t.setEditTimestamp();
        updateSP();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
