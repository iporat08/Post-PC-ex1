package com.idoporat.ex3;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class TodoItem implements Parcelable {
    ///////////////////////////////////// data members /////////////////////////////////////////////
    /** A String describing the task to be done. **/
    final private String description;

    /** A boolean value, marks whether or not the task of the todoItem is done. **/
    private boolean isDone;

    ///////////////////////////////////// constructors /////////////////////////////////////////////
    /**
     * Constructor
     * @param todoMessage A String describing the task to be done.
     */
    TodoItem(String todoMessage){
        this.description = todoMessage;
        isDone = false;
    }

    /**
     * A constructor from Parcel
     * @param source a Parcel to be reconstructed
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private TodoItem(Parcel source){
        this.description = source.readString();
        isDone = source.readBoolean();
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

    ///////////////////////////////////// setters //////////////////////////////////////////////////
    /**
     * Sets the TodoItem's isDone field to true.
     */
    void setIsDone(){
        isDone = true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}



