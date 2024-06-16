package com.gamecodeschool.photogallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gamecodeschool.photogallery.RecyclerViewAdapter.RecyclerViewHolder
import com.squareup.picasso.Picasso
import java.io.File

// Adapter for RecyclerView to display a gallery of images
class RecyclerViewAdapter
    (
    private val context: Context, var imagePathArrayList: ArrayList<String>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    // Inflate the item layout and create the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    // Bind data to the view holder
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val imgFile = File(imagePathArrayList[position])

        // Check if the image file exists
        if (imgFile.exists()) {
            // Load the image into the ImageView using Picasso
            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageIV)

            // Set click listener to open ImageDetailActivity with the image path
            holder.itemView.setOnClickListener {
                val i = Intent(context, ImageDetailActivity::class.java)
                i.putExtra("imgPath", imagePathArrayList[position])
                context.startActivity(i)
            }
        }
    }

    // Return the total number of items
    override fun getItemCount(): Int {
        return imagePathArrayList.size
    }

    // ViewHolder class to hold the views for each item
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageIV: ImageView = itemView.findViewById(R.id.idIVImage)
    }
}