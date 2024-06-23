package com.gamecodeschool.photos

import GridViewAdapter
import ImageItem
import android.R
import android.content.res.TypedArray
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.GridView


class MainActivity : ActionBarActivity() {
    private var gridView: GridView? = null
    private var gridAdapter: GridViewAdapter? = null

    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView) as GridView?
        gridAdapter = GridViewAdapter(this, R.layout.grid_item_layout, data)
        gridView!!.adapter = gridAdapter
    }

    private val data: ArrayList<ImageItem>
        // Prepare some dummy data for gridview
        get() {
            val imageItems = ArrayList<ImageItem>()
            val imgs: TypedArray = getResources().obtainTypedArray(R.array.image_ids)
            for (i in 0 until imgs.length()) {
                val bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1))
                imageItems.add(ImageItem(bitmap, "Image#$i"))
            }
            return imageItems
        }
}