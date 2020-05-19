package com.idoporat.ex3;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.TreeSet;

public class TodoItem implements Parcelable {
    ///////////////////////////////////// data members /////////////////////////////////////////////
    /** A String describing the task to be done. **/
    private String description;

    /** A boolean value, marks whether or not the task of the todoItem is done. **/
    private boolean isDone;

    /** a unique id **/
    private int id;

    /** the creation time of the TodoItem **/
    private Date creationTimestamp;

    /** the date in which this TodoItem was last edited **/
    private Date editTimestamp;

    ///////////////////////////////////// constructors /////////////////////////////////////////////
    /**
     * Constructor
     * @param todoMessage A String describing the task to be done.
     */
    TodoItem(String todoMessage, int id){
        this.description = todoMessage;
        isDone = false;
        creationTimestamp = new Date(System.currentTimeMillis());
        editTimestamp = creationTimestamp;
        this.id = id;
    }

    /**
     * A constructor from Parcel
     * @param source a Parcel to be reconstructed
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private TodoItem(Parcel source){
        this.description = source.readString();
        isDone = source.readBoolean();
        id = source.readInt();
        creationTimestamp = new Date(source.readLong());
        editTimestamp = new Date(source.readLong());

    }

    //////////////////////////////// Parcelable interface methods //////////////////////////////////
    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeBoolean(isDone);
        dest.writeInt(id);
        dest.writeLong(creationTimestamp.getTime());
        dest.writeLong(editTimestamp.getTime());
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>(){
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    ///////////////////////////////////// getters //////////////////////////////////////////////////
    /**
     * @return the TodoItem's description field.
     */
    String getDescription() {
        return description;
    }

    /**
     * @return the TodoItem's isDone field.
     */
    boolean isDone() {
        return isDone;
    }

    /**
     * returns the TodoItems' id
     */
    int getId(){
        return id;
    }

    /**
     * @return creationTimestamp
     */
    Date getCreationTime(){
        return creationTimestamp;
    }

    /**
     * @return editTimestamp
     */
    Date getEditTimestamp(){
        return editTimestamp;
    }


    ///////////////////////////////////// setters //////////////////////////////////////////////////
    /**
     * Sets the TodoItem's isDone field to true.
     */
    void markAsDone(){
        isDone = true;
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
    void setDescription(String s){
        description = s;
    }

    /**
     * changes the editTimestamp to the current date and time.
     */
    void setEditTimestamp(){
        editTimestamp = new Date(System.currentTimeMillis());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}



