package com.brunodiegom.movies.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunodiegom.movies.R
import com.brunodiegom.movies.model.Cast
import com.brunodiegom.movies.model.Movie.Companion.THUMBNAIL_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_cast.view.cast_name
import kotlinx.android.synthetic.main.adapter_cast.view.cast_picture
import kotlinx.android.synthetic.main.adapter_cast.view.cast_row

/**
 * [RecyclerView.Adapter] to apply the [Cast] content on the list.
 */
class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var castList: List<Cast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_cast,
                parent,
                false
            )
        )

    override fun getItemCount() = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        castList[position].let { cast ->
            holder.itemView.apply {
                cast_name.text = cast.name
                cast_row.text = cast.character
                Picasso.get()
                    .load("$THUMBNAIL_BASE_URL${cast.picture}")
                    .fit().centerCrop()
                    .placeholder(R.mipmap.poster_placeholder)
                    .error(R.mipmap.poster_placeholder)
                    .into(cast_picture)
            }
        }
    }

    /**
     * Update the [Cast] list on [CastAdapter].
     */
    fun replaceItems(list: List<Cast>) {
        castList = list
        notifyDataSetChanged()
    }

    class CastViewHolder(view: View) : RecyclerView.ViewHolder(view)
}