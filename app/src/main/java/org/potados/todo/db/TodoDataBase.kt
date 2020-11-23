package org.potados.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.potados.todo.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDataBase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: createInstance(context)
        }

        private fun createInstance(context: Context) =
                Room.databaseBuilder(
                        context,
                        TodoDataBase::class.java,
                        "todo-database"
                ).build().apply {
                    instance = this
                }
    }
}