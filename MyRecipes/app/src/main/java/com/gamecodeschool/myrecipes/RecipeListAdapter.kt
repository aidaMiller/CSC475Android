package com.gamecodeschool.myrecipes

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecipeListAdapter(private val context: Context, private val viewModel: RecipeListViewModel) :

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
        private val image: ImageView = itemView.findViewById(R.id.recipe_image)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            val drawable = if (recipe.isFavorite) itemView.resources.getDrawable(R.drawable.baseline_bookmark_24) else  itemView.resources.getDrawable(R.drawable.baseline_bookmark_border_24)
            favorite.setImageDrawable(drawable)
            favorite.setBackgroundDrawable(null)
//            if (recipe.image.isEmpty()) {

                val decodedString: ByteArray = Base64.decode(recipe.image, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                image.setImageBitmap(decodedByte)
//            }

            favorite.setOnClickListener {
                val isFavorite = recipe.isFavorite.not()
                val updatedRecipe = Recipe(recipe.id, recipe.title, recipe.image, recipe.ingredients, recipe.instructions, isFavorite)
                viewModel.update(updatedRecipe)
            }

            itemView.setOnClickListener{
//                val newRecipeActivity = AddRecipe()
                val bundle = Bundle()
                bundle.putInt("RecipeId", recipe.id)
                val intent = Intent(context, AddRecipe::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)

            }

        }
    }
}