package com.idoporat.ex3;

/**
 * An interface whose purpose's to enable the adapter to pass information about events to th
 * e MainActivity
 */
public interface TodoClickListener{
    /**
     * Constructor
     * @param t - a TodoItem object.
     */
    public void onTodoClick(TodoItem t);
}