
import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class GridViewAdapter(
    private val context: Context,
    private val layoutResourceId: Int,
    data: ArrayList<*>
) :
    ArrayAdapter<Any?>(context, layoutResourceId, data) {
    private var data: ArrayList<*> = ArrayList<Any?>()

    init {
        this.data = data
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        var holder: ViewHolder? = null

        if (row == null) {
            val inflater = (context as Activity).layoutInflater
            row = inflater.inflate(layoutResourceId, parent, false)
            holder = ViewHolder()
            holder.imageTitle = row.findViewById<View>(R.id.text) as TextView
            holder.image = row.findViewById<View>(R.id.image) as ImageView
            row.tag = holder
        } else {
            holder = row.tag as ViewHolder
        }

        val item: ImageItem = data[position]
        holder.imageTitle.setText(item.getTitle())
        holder!!.image!!.setImageBitmap(item.getImage())
        return row!!
    }

    internal class ViewHolder {
        var imageTitle: TextView? = null
        var image: ImageView? = null
    }
}
