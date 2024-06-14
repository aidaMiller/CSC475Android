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

class RecyclerViewAdapter // on below line we have created a constructor.
    (// creating a variable for our context and array list.
    private val context: Context, var imagePathArrayList: ArrayList<String>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

//    val imagePaths: ArrayList<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout in this method which we have created.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // on below line we are getting the file from the
        // path which we have stored in our list.

        val imgFile = File(imagePathArrayList[position])

        // on below line we are checking if the file exists or not.
        if (imgFile.exists()) {
            // if the file exists then we are displaying that file in our image view using picasso library.

            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageIV)

            // on below line we are adding click listener to our item of recycler view.
            holder.itemView.setOnClickListener { // inside on click listener we are creating a new intent
                val i = Intent(context, ImageDetailActivity::class.java)

                // on below line we are passing the image path to our new activity.
                i.putExtra("imgPath", imagePathArrayList[position])

                // at last we are starting our activity.
                context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        // this method returns 
        // the size of recyclerview
        return imagePathArrayList.size
    }

    // View Holder Class to handle Recycler View.
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        // initializing our views with their ids.
        internal val imageIV: ImageView = itemView.findViewById(R.id.idIVImage)
    }
}