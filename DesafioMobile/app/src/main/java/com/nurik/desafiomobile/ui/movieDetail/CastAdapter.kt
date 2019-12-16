package com.nurik.desafiomobile.ui.movieDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nurik.desafiomobile.R
import com.nurik.desafiomobile.databinding.CastItemListBinding
import com.nurik.desafiomobile.pojo.Cast

class CastAdapter (private val castList: List<Cast>): RecyclerView.Adapter<CastAdapter.CastViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
            CastViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.cast_item_list,
                    parent, false
            ))

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = castList[position]
        holder.recyclerViewBinding.cast = castList[position]
    }

    override fun getItemCount() = castList.size

    inner class CastViewHolder(val recyclerViewBinding: CastItemListBinding)
        : RecyclerView.ViewHolder(recyclerViewBinding.root)
}
