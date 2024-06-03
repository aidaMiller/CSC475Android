// Todo.kt
package com.gamecodeschool.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val task: String,
    val isCompleted: Boolean = false
)