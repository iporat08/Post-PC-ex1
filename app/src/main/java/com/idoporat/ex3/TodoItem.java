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
    }

    /**
     * An empty constructor, needed for FireStore
     */
    TodoItem(){}

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
     * Sets isDone
     * @param isDone the new isDone value
     */
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * changes the description of this TodoItem
     * @param s - the new description
     */
    public void setDescription(String s){
        description = s;
    }

    /**
     * Sets the TodoItem's id
     * @param id the new id
     */
    void setId(String id){
        this.id = id;
    }

    /**
     * changes the editTimestamp to the current date and time.
     */
    public void setEditTimestamp(){
        editTimestamp = new Date(System.currentTimeMillis());
    }

    /**
     * sets creationTimeStamp
     * @param d the new creationTimeStamp
     */
    public void setCreationTimestamp(Date d){
        creationTimestamp = d;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}



