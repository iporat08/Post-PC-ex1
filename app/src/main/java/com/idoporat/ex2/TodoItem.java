package com.idoporat.ex2;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class TodoItem implements Parcelable {
    final private String todoMessage;
    private boolean isDone;

    public TodoItem(String todoMessage){
        this.todoMessage = todoMessage;
        isDone = false;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q) //todo - is this ok..?
    public TodoItem(Parcel source){
        this.todoMessage = source.readString();
        isDone = source.readBoolean(); //todo int?
    }

    public String getTodoMessage() {
        return todoMessage;
    }
    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(){
        isDone = !isDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q) //todo ok..?
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(todoMessage);
        dest.writeBoolean(isDone); //todo maybe int?
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>(){

        @RequiresApi(api = Build.VERSION_CODES.Q) //todo ok..?
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source); // todo - maybe int?

        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };
}



