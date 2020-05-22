package com.idoporat.ex3;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.TreeSet;

public class TodoItem {
    ///////////////////////////////////// data members /////////////////////////////////////////////
    /** A String describing the task to be done. **/
    private String description;

    /** A boolean value, marks whether or not the task of the todoItem is done. **/
    private boolean isDone;

    /** a unique id **/
    private String id;

    /** the creation time of the TodoItem **/
    private Date creationTimestamp;

    /** the date in which this TodoItem was last edited **/
    private Date editTimestamp;

    ///////////////////////////////////// constructors /////////////////////////////////////////////
    /**
     * Constructor
     * @param todoMessage A String describing the task to be done.
     */
    TodoItem(String todoMessage){
        this.description = todoMessage;
        isDone = false;
        creationTimestamp = new Date(System.currentTimeMillis());
        editTimestamp = creationTimestamp;
//        this.id = id;
    }

    /**
     * Todo
     */
    TodoItem(){

    }

    /**
     * A constructor from Parcel
     * @param source a Parcel to be reconstructed
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private TodoItem(Parcel source){
        this.description = source.readString();
        isDone = source.readBoolean();
        id = source.readString();
        creationTimestamp = new Date(source.readLong());
        editTimestamp = new Date(source.readLong());

    }

    //////////////////////////////// Parcelable interface methods //////////////////////////////////
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(description);
//        dest.writeBoolean(isDone);
//        dest.writeString(id);
//        dest.writeLong(creationTimestamp.getTime());
//        dest.writeLong(editTimestamp.getTime());
//    }
//
//    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>(){
//        @RequiresApi(api = Build.VERSION_CODES.Q)
//        @Override
//        public TodoItem createFromParcel(Parcel source) {
//            return new TodoItem(source);
//        }
//
//        @Override
//        public TodoItem[] newArray(int size) {
//            return new TodoItem[size];
//        }
//    };

    ///////////////////////////////////// getters //////////////////////////////////////////////////
    /**
     * @return the TodoItem's description field.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the TodoItem's isDone field.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * returns the TodoItems' id
     */
    public String getId(){
        return id;
    }

    /**
     * @return creationTimestamp
     */
    public Date getCreationTimestamp(){
        return creationTimestamp;
    }

    /**
     * @return editTimestamp
     */
    public Date getEditTimestamp(){
        return editTimestamp;
    }


    ///////////////////////////////////// setters //////////////////////////////////////////////////
    /**
     * Sets the TodoItem's isDone field to true.
     */
    void markAsDone(){
        isDone = true;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * Sets the TodoItem's isDone field to false.
     */
    void markUndone(){
        isDone = false;
    }

    /**
     * changes the description of this TodoItem
     * @param s - the new description
     */
    public void setDescription(String s){
        description = s;
    }

    void setId(String id){
        this.id = id;
    }

    /**
     * changes the editTimestamp to the current date and time.
     */
    public void setEditTimestamp(){
        editTimestamp = new Date(System.currentTimeMillis());
    }

    public void setCreationTimestamp(Date d){
        creationTimestamp = d;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}



