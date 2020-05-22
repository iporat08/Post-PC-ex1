package com.idoporat.ex3;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A class that holds and manages a todoList for the app.
 */
class TodoItemsManager {
    //////////////////////////////////////// data members //////////////////////////////////////////
    /** A list of TodoItems **/
    private ArrayList<TodoItem> todoList = new ArrayList<TodoItem>();

    /** The key of the json of the todoList **/
    private static final String SP_TODO_LIST = "todoList";

    private static final String TODO_COLLECTION = "todoCollection";

    /** A Gson object of the class **/
    private static Gson gson;

    /** A SharedPreferences object of the class **/
    private static SharedPreferences sp;


    private FirebaseFirestore db;

    private CollectionReference todoRef;

    private TodoAdapter adapter;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A constructor
     * @param context the context of the instance.
     */
    TodoItemsManager(Context context){
        db = FirebaseFirestore.getInstance();
        todoRef = db.collection(TODO_COLLECTION);
        adapter = new TodoAdapter(todoList);
    }

    public void updateData(){
        todoList.clear();
        todoRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    TodoItem todoItem = documentSnapshot.toObject(TodoItem.class);
                    todoList.add(todoItem);
                }
                Log.i(TAG, "current todoList size is: " + todoList.size());
                adapter.notifyDataSetChanged();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding collection", e);
                    }
                });;
    }

    //////////////////////////////////////// getters ///////////////////////////////////////////////

    /** returns the todoList **/
    ArrayList<TodoItem> getTodoList(){
        return todoList;
    }

    TodoItem getTodoItem(String id){
        //todo
        for(TodoItem t : todoList){ //todo - temp, should change after firebase
            if(t.getId() != null) {
                if (t.getId().equals(id)) {
                    return t;
                }
            }
        }
        return null;
    }

    public TodoAdapter getAdapter(){
        return adapter;
    }

    //////////////////////////////////////// setters ///////////////////////////////////////////////


    /**
     * Adds a TodoItem to the todoList.
     * @param description the TodoItems' description
     */
    void addTodoItem(String description){//TodoItem t){
        final TodoItem t = new TodoItem(description);

        db.collection(TODO_COLLECTION)
                .add(t)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String id = documentReference.getId();
                        t.setId(id);
                        todoList.add(t); // todo
                        todoRef.document(id).set(t, SetOptions.merge());
                        adapter.notifyDataSetChanged();
                    }
                })
                 .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    /**
     * Removes a TodoItem to the todoList.
     * @param id the id of the TodoItem to be removed.
     */
    void removeTodoItem(String id){
        todoRef.document(id).delete();
        TodoItem t = getTodoItem(id);
        todoList.remove(t);
    }

    /**
     * Replaces todoList with a new todoList.
     * @param list the new todoList.
     */
    void setTodoList(ArrayList<TodoItem> list){
        todoList = list;
    }

    /**
     * Marks a TodoItem as undone
     * @param id the id of the TodoItem to be marked as done
     */
    void markTodoItemAsDone(String id, Context context){
        TodoItem t = getTodoItem(id);
        t.markAsDone();
        updateTodoItem(t, context);
    }

    /**
     * Changes th description of a TodoItem
     * @param id the id of the TodoItem to be edited
     * @param description the new description
     */
    void setTodoItemsDescription(String id, String description, Context context){
        TodoItem t = getTodoItem(id);
        t.setDescription(description);
        updateTodoItem(t, context);
    }

    /**
     * Marks a TodoItem as undone
     * @param id the id of the TodoItem to be marked as undone
     */
    void markTodoItemAsUndone(String id, Context context){
        TodoItem t = getTodoItem(id);
        t.markUndone();
        updateTodoItem(t, context);
    }

    /**
     * updates t's editTimeStamp and updates the SP
     * @param t the TodoItm to be updated
     */
    private void updateTodoItem(TodoItem t, final Context context){
        t.setEditTimestamp();
        todoRef.document(t.getId()).set(t, SetOptions.merge())
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( context, "failed to get data", Toast
                                .LENGTH_SHORT).show();
                    }
                });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
