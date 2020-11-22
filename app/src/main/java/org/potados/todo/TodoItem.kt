package org.potados.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @ColumnInfo(name = "thing_to_do") val thingToDo: String,
    @ColumnInfo(name = "is_done") val isDone: Boolean,

    @PrimaryKey(autoGenerate = true) val id: Int? = null
)