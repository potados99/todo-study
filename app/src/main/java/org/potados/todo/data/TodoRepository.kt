package org.potados.todo.data

import android.content.Context
import android.util.Log
import org.potados.todo.MyApp
import org.potados.todo.TodoItem
import org.potados.todo.db.TodoDataBase

class TodoRepository {

    private val db = TodoDataBase.getInstance(MyApp.getAppContext())
    private val todoDao = db.todoDao()

    fun getAllTodo() = todoDao.getAllTodo()

    fun addTodo(todo: TodoItem) {
        Thread {
            Log.d("Another thread", Thread.currentThread().name)
            todoDao.insertTodo(todo)
        }.start()
    }

}