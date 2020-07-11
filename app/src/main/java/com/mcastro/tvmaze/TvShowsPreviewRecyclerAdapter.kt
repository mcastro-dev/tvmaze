package com.mcastro.tvmaze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcastro.tvmaze.databinding.ItemTvShowPreviewBinding
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.squareup.picasso.Picasso

interface TvShowClickListener {
    fun onTvShowClicked(tvShowPreview: TvShowPreview)
}

class TvShowsPreviewRecyclerAdapter(
    private val clickListener: TvShowClickListener
) : RecyclerView.Adapter<TvShowsPreviewRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemTvShowPreviewBinding) : RecyclerView.ViewHolder(binding.root)

    private val previews = mutableListOf<TvShowPreview>()

    override fun getItemCount() = previews.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTvShowPreviewBinding.inflate(
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
            Picasso.get().load(item.posterUrl).into(imgPoster);
        }
    }

    fun addData(previews: List<TvShowPreview>) {
        val startPosition = this.previews.size
        this.previews.addAll(previews)
        notifyItemRangeInserted(startPosition, previews.size)
    }

    fun resetData() {
        this.previews.clear()
    }
}