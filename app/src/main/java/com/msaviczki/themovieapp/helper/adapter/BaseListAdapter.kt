package com.msaviczki.themovieapp.helper.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter : RecyclerView.Adapter<BaseListAdapter.BaseViewHolder<BaseListAdapter.ItemView>>() {

    private val itemsList: MutableList<ItemView> = mutableListOf()

    open fun addItems(items: List<ItemView>) {
        val initPosition = getLastPosition()

        if(itemsList.isNotEmpty()) {
            itemsList.removeAt(initPosition)
            notifyItemRemoved(initPosition)
            clearItems()
        }

        itemsList.addAll(items)
        notifyItemRangeInserted(initPosition, itemsList.size + 1)
    }

    fun clearItems() {
        val size = itemCount

        itemsList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun isEmpty() = itemsList.isEmpty()

    private fun getLastPosition() = if(itemsList.lastIndex == -1) 0 else itemsList.lastIndex

    override fun getItemCount() = itemsList.size

    override fun getItemViewType(position: Int) = itemsList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemView> =
        getItemViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<ItemView>, position: Int) {
        holder.bind(itemsList[position])
    }

    abstract fun getItemViewHolder(parent: ViewGroup): BaseViewHolder<ItemView>

    abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = itemView
        abstract fun bind(item: T)
    }

    interface ItemView {
        val type: Int
    }
}