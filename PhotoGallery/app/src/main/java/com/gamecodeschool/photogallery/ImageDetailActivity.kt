package com.gamecodeschool.photogallery

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.File
import kotlin.math.max
import kotlin.math.min

class ImageDetailActivity : AppCompatActivity() {

    // Path of the image to be displayed
    var imgPath: String? = null
    private var imageView: ImageView? = null
    private var scaleGestureDetector: ScaleGestureDetector? = null

    // Scale factor for pinch-to-zoom functionality
    private var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        // Get the image path from the intent
        imgPath = intent.getStringExtra("imgPath")

        // Find the ImageView in the layout
        imageView = findViewById<ImageView>(R.id.idIVImage)

        // Initialize the scale gesture detector
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        // Create a File object for the image
        val imgFile = File(imgPath)

        // If the image file exists, load it into the ImageView using Picasso
        if (imgFile.exists()) {
            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }

    // Handle touch events to detect scaling gestures
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {

        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    // Inner class to handle scale gestures
    private inner class ScaleListener : SimpleOnScaleGestureListener() {

        // Method called during a scale gesture
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {

            // Update the scale factor based on the gesture
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1, min(mScaleFactor.toDouble(), 10.0)).toFloat()

            // Apply the scale factor to the ImageView
            imageView!!.scaleX = mScaleFactor
            imageView!!.scaleY = mScaleFactor
            return true
        }
    }
}