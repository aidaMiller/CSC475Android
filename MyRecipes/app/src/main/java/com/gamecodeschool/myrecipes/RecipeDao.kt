package com.gamecodeschool.myrecipes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    suspend fun getRecipe(id: Int): Recipe

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)
}