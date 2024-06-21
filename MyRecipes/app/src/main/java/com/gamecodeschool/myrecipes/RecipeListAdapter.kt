package com.gamecodeschool.myrecipes

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gamecodeschool.myrecipes.com.gamecodeschool.myrecipes.Recipe



//class RecipeListAdapter(private val viewModel: RecipeListViewModel) :
class RecipeListAdapter() :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    var recipes = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, parent, false)
        return RecipeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val current = recipes[position]
        holder.bind(current)
    }

    override fun getItemCount() = recipes.size

    inner class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favorite: ImageButton = itemView.findViewById(R.id.favorite)
        private val title: TextView = itemView.findViewById(R.id.recipe_title)
        private val image: ImageView = itemView.findViewById(R.id.recipeImage)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            val drawable = if (recipe.isFavorite) itemView.resources.getDrawable(R.drawable.outline_bookmark_heart_24) else  itemView.resources.getDrawable(R.drawable.outline_bookmark_heart_24)
            favorite.setImageDrawable(drawable)
            val decodedString: ByteArray = Base64.decode(recipe.image, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            image.setImageBitmap(decodedByte)

            favorite.setOnClickListener {
                val updatedRecipe = Recipe(recipe.id, recipe.title, recipe.image, recipe.ingredients, recipe.instructions, !recipe.isFavorite)
                //viewModel.update(updatedRecipe)

            }

        }
    }
}