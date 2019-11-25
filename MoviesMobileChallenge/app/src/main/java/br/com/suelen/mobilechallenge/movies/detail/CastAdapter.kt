package br.com.suelen.mobilechallenge.movies.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.suelen.mobilechallenge.data.model.Person
import br.com.suelen.mobilechallenge.databinding.CastItemBinding

class CastAdapter :  ListAdapter<Person, CastAdapter.CastViewHolder>(
    PersonDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

    class CastViewHolder(private val binding : CastItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent : ViewGroup) : CastViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CastItemBinding.inflate(layoutInflater, parent, false)
                return CastViewHolder(
                    binding
                )
            }
        }

        fun bind(item : Person) {
            binding.apply {
                person = item
                executePendingBindings()
            }
        }
    }

}

private class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

}