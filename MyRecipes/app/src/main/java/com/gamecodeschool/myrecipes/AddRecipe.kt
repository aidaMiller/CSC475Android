package com.gamecodeschool.myrecipes

import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import com.gamecodeschool.myrecipes.databinding.ActivityAddRecipeBinding
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class AddRecipe : AppCompatActivity() {
    private val recipeListViewModel: RecipeListViewModel by viewModels()

    private lateinit var binding: ActivityAddRecipeBinding
    private lateinit var image: ImageView
    private lateinit var name: EditText
    private lateinit var ingredients: EditText
    private lateinit var instruction: EditText
    private lateinit var save: Button
    private  var isEditView = false
    private var recipeId = 0
    private lateinit var delete: Button
    private lateinit var cancel: Button


    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val galleryUri = it
        try{
            image.setImageURI(galleryUri)

        }catch(e:Exception){
            e.printStackTrace()
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        image = findViewById<ImageView>(R.id.recipeImage)
        name = findViewById<EditText>(R.id.recipeName)
        ingredients = findViewById(R.id.recipeIngredients)
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

        image.setOnClickListener{
            galleryLauncher.launch("image/*")
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
            image = getImageBase64())
    }

    fun getImageBase64(): String {
        val bitmap = image.drawable.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()
        return Base64.encodeToString(byteArray,0)

    }

    fun setImageFromBase64(string: String) {
        val decodedString: ByteArray = Base64.decode(string, Base64.DEFAULT)
        val decodedByte =
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        image.setImageBitmap(decodedByte)
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
        if (recipe.image.isNotEmpty()) {
            setImageFromBase64(recipe.image)
        }

    }
}