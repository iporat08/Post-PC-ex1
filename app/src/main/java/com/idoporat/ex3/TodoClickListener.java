package com.idoporat.ex3;

/**
 * An interface whose purpose is to enable the adapter to pass information about events to the
 * MainActivity
 */
public interface TodoClickListener{
    /**
     * Constructor
     * @param t - a TodoItem object.
     */
    public void onTodoClick(TodoItem t);

    /**
     * The method enabling the adapter to pass information to the MainActivity when a TodoItem is
     * being clicked.
     * @param t a TodoBoom item which the user clicked. //todo delete
     */
    public void onTodoLongClick(TodoItem t);
}