package com.movies.appmoviescit.ui.movieDetail.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviescit.R
import com.movies.appmoviescit.model.Cast
import com.movies.appmoviescit.utils.MoviesImageBuilder

class MovieCastAdapter(val context: Context, var contentList: List<Cast>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val holder: ViewHolder
        val castItem = contentList[position]

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.cast_item, null, true)

            holder.castName = convertView!!.findViewById(R.id.cast_name) as TextView
            holder.castCharacter = convertView!!.findViewById(R.id.cast_character) as TextView
            holder.castImage = convertView.findViewById(R.id.cast_image) as ImageView

            Glide.with(convertView!!.findViewById(R.id.cast_view) as View)
                .load(castItem.profile_path?.let { src ->
                    MoviesImageBuilder().buildPosterUrl(src)
                })
                .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
                .into(holder.castImage!!)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.castName?.text = castItem.name
        holder.castCharacter?.text = castItem.character

        return convertView
    }

    override fun getItem(position: Int): Any {
        return contentList[position]
    }

    override fun getItemId(position: Int): Long {
        return contentList[position].id
    }

    override fun getCount(): Int {
        return contentList.size
    }

    private inner class ViewHolder {
        var castName: TextView? = null
        var castCharacter: TextView? = null
        internal var castImage: ImageView? = null
    }
}