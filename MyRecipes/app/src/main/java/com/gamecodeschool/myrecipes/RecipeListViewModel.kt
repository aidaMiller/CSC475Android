package com.gamecodeschool.myrecipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    val allRecipes: LiveData<List<Recipe>>

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        allRecipes = repository.allRecipes
    }

    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }

    fun update(recipe: Recipe) = viewModelScope.launch {
        repository.update(recipe)
    }

    fun delete(recipe: Recipe) = viewModelScope.launch {
        repository.delete(recipe)
    }
    suspend fun getRecipe(id:Int): Recipe{
        return repository.getRecipe(id)
    }
}