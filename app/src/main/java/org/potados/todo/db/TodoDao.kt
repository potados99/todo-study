package org.potados.todo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.potados.todo.TodoItem

@Dao
interface TodoDao {
    @Query("SELECT * FROM todoitem")
    fun getAllTodo(): LiveData<List<TodoItem>>

    @Insert
    fun insertTodo(todo: TodoItem)

    @Update
    fun updateTodo(todo: TodoItem)

    @Delete
    fun deleteTodo(todo: TodoItem)
}