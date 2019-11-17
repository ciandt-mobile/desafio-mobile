package br.com.victoroliveira.cit.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.victoroliveira.cit.BuildConfig
import br.com.victoroliveira.cit.R
import br.com.victoroliveira.cit.data.model.Cast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_main_cast.view.*
import kotlinx.android.synthetic.main.item_movie_presentation.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    lateinit var context: Context
    private val castList: MutableList<Cast> = mutableListOf()

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cast: Cast) {
            Glide.with(context).load(BuildConfig.API_IMAGE_POSTER + cast.profile_path)
                .into(itemView.item_cast_image)

            itemView.item_cast_actor.text = cast.name
        }
    }

    fun addCast(listCast: List<Cast>) {
        castList.clear()
        castList.addAll(listCast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_cast, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    private fun getItem(position: Int): Cast {
        return castList[position]
    }
}