package org.potados.todo

import androidx.lifecycle.ViewModel
import org.potados.todo.data.TodoRepository

class MainViewModel : ViewModel() {

    private val todoRepo = TodoRepository()

    val allTodo = todoRepo.getAllTodo()

    fun addDummyData() {
        todoRepo.addTodo(
            TodoItem("aaaadadada", false)
        )
    }

    fun addTodo(thingToDo: String) {
        todoRepo.addTodo(
            TodoItem(thingToDo = thingToDo, isDone = false)
        )
    }

}