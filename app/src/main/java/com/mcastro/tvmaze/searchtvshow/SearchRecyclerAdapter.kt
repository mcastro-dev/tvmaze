package com.mcastro.tvmaze.searchtvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcastro.tvmaze.common.TvShowPreviewClickListener
import com.mcastro.tvmaze.databinding.ItemSearchTvShowBinding
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.squareup.picasso.Picasso

class SearchRecyclerAdapter(
    private val clickListener: TvShowPreviewClickListener
) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSearchTvShowBinding) : RecyclerView.ViewHolder(binding.root)

    private val previews = mutableListOf<TvShowPreview>()

    override fun getItemCount() = previews.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchTvShowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = previews[position]

        val onItemClicked = View.OnClickListener {
            clickListener.onTvShowClicked(item)
        }

        holder.binding.run {
            root.setOnClickListener(onItemClicked)
            txtName.text = item.name
            Picasso.get().load(item.posterUrl).into(imgPoster)
        }
    }

    fun setData(previews: List<TvShowPreview>) {
        this.previews.clear()
        this.previews.addAll(previews)
        notifyDataSetChanged()
    }
}