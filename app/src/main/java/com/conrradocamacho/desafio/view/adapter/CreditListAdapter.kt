package com.conrradocamacho.desafio.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.network.bean.Cast
import kotlinx.android.synthetic.main.item_credit.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.imageView

/**
 * Created by Conrrado Camacho on 09/09/2019.
 * con.webmaster@gmail.com
 */
class CreditListAdapter(private val castList: MutableList<Cast>):
    RecyclerView.Adapter<CreditListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(castList[position])
    }

    fun updateList(list: List<Cast>) {
        castList.clear()
        castList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView) {

        fun onBind(cast: Cast) {
            itemView.name.text = cast.name
            itemView.character.text = cast.character
            Glide.with(context)
                .load("${BuildConfig.BASE_IMAGE_URL}t/p/w185/${cast.profilePath}")
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(itemView.imageView)
        }
    }
}