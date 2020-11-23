package org.potados.todo

import androidx.lifecycle.ViewModel
import org.potados.todo.data.TodoRepository

class MainViewModel : ViewModel() {

    private val todoRepo = TodoRepository()

    val allTodo = todoRepo.getAllTodo()

    fun addTodo(thingToDo: String) {
        todoRepo.addTodo(
            TodoItem(thingToDo = thingToDo, isDone = false)
        )
    }

    fun toggleDone(todo: TodoItem, isDone: Boolean) {
        todoRepo.updateTodo(todo.copy(isDone = isDone))
    }

    fun deleteTodo(todo: TodoItem) {
        todoRepo.deleteTodo(todo)
    }

}