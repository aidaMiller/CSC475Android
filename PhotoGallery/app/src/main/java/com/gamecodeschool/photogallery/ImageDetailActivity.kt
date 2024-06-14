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
    // creating a string variable, image view variable
    // and a variable for our scale gesture detector class.
    var imgPath: String? = null
    private var imageView: ImageView? = null
    private var scaleGestureDetector: ScaleGestureDetector? = null

    // on below line we are defining our scale factor.
    private var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)


        // on below line getting data which we have passed from our adapter class.
        imgPath = intent.getStringExtra("imgPath")


        // initializing our image view.
        imageView = findViewById<ImageView>(R.id.idIVImage)


        // on below line we are initializing our scale gesture detector for zoom in and out for our image.
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())


        // on below line we are getting our image file from its path.
        val imgFile = File(imgPath)


        // if the file exists then we are loading that image in our image view.
        if (imgFile.exists()) {
            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        // inside on touch event method we are calling on
        // touch event method and passing our motion event to it.
        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        // on below line we are creating a class for our scale
        // listener and extending it with gesture listener.
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            // inside on scale method we are setting scale
            // for our image in our image view.

            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1, min(mScaleFactor.toDouble(), 10.0)).toFloat()


            // on below line we are setting
            // scale x and scale y to our image view.
            imageView!!.scaleX = mScaleFactor
            imageView!!.scaleY = mScaleFactor
            return true
        }
    }
}