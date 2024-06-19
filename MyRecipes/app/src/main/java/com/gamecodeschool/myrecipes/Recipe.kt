package com.gamecodeschool.myrecipes.com.gamecodeschool.myrecipes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val title: String,
    val image: String,
    val ingredients: String,
    val instructions: String,
    val isFavorite: Boolean = false

)