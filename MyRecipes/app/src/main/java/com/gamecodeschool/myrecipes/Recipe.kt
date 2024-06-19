package com.gamecodeschool.myrecipes.com.gamecodeschool.myrecipes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val task: String,
    val isCompleted: Boolean = false
)