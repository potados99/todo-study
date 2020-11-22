package org.potados.todo.data

import android.content.Context
import android.util.Log
import org.potados.todo.TodoItem
import org.potados.todo.db.TodoDataBase

class TodoRepository(
    private val context: Context
) {

    private val db = TodoDataBase.getInstance(context)
    private val todoDao = db.todoDao()

    fun getAllTodo() = todoDao.getAllTodo()

    fun addTodo(todo: TodoItem) {
        Thread {
            Log.d("Another thread", Thread.currentThread().name)
            todoDao.insertTodo(todo)
        }.start()
    }

}