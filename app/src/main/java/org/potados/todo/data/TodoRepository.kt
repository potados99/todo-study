package org.potados.todo.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.potados.todo.MyApp
import org.potados.todo.TodoItem
import org.potados.todo.db.TodoDataBase

class TodoRepository {

    private val db = TodoDataBase.getInstance(MyApp.getAppContext())
    private val todoDao = db.todoDao()

    fun getAllTodo() = todoDao.getAllTodo()

    fun addTodo(todo: TodoItem) {
        launchOnBackground {
            todoDao.insertTodo(todo)
        }
    }

    fun updateTodo(todo: TodoItem) {
        launchOnBackground {
            todoDao.updateTodo(todo)
        }
    }

    private fun launchOnBackground(action: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            action()
        }
    }
}