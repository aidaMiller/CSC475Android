package com.gamecodeschool.myrecipes

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gamecodeschool.myrecipes.databinding.ActivityAddRecipeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.ProcessingInstruction

class AddRecipe : AppCompatActivity() {
    private val recipeListViewModel: RecipeListViewModel by viewModels()

    private lateinit var binding: ActivityAddRecipeBinding
//    private lateinit var image: ImageView
    private lateinit var name: EditText
    private lateinit var ingredients: EditText
    private lateinit var instruction: EditText
    private lateinit var save: Button
    private  var isEditView = false
    private var recipeId = 0
    private lateinit var delete: Button
    private lateinit var cancel: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        image = findViewById<ImageView>(R.id.recipe_image)
        name = findViewById<EditText>(R.id.recipeName)
        ingredients = findViewById(R.id.recipeName)
        instruction = findViewById(R.id.recipeInstructions)
        save = findViewById(R.id.buttonSave)
        delete = findViewById(R.id.buttonDelete)
        cancel = findViewById(R.id.buttonCancel)

        save.setOnClickListener{
            val newRecipe = getRecipeFromView()
            save(newRecipe)
            finish()
        }

        delete.setOnClickListener{
            recipeListViewModel.delete(getRecipeFromView())
        finish()
        }
        cancel.setOnClickListener{
            finish()
        }

        val bundle = intent.extras
        val bundleRecipeId = bundle?.getInt("RecipeId")
        if (bundleRecipeId != null) {
            lifecycleScope.launch {
                setViewAsEdit(bundleRecipeId)
            }

        }
    }

    fun getRecipeFromView(): Recipe{
       return Recipe(
            id = recipeId,
            title = name.text.toString(),
            ingredients = ingredients.text.toString(),
            instructions = instruction.text.toString(),
            image = "")
    }

    private fun save(recipe: Recipe){
        recipeListViewModel.insert(recipe)
        if (isEditView){
            recipeListViewModel.update(recipe)
        } else {
            recipeListViewModel.insert(recipe)
        }
    }

    private suspend fun setViewAsEdit(recipeId : Int){
        isEditView = true
        this.recipeId = recipeId
        delete.visibility = View.VISIBLE
        val recipe = recipeListViewModel.getRecipe(recipeId)
        name.setText(recipe.title)
        ingredients.setText(recipe.ingredients)
        instruction.setText(recipe.instructions)

    }
}