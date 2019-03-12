package no.jahnsrud.podium.Database

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jahnsrud.podium.Models.Podcast
import no.jahnsrud.podium.R

class PodcastAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PodcastAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var podcasts = emptyList<Podcast>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.list_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.podcast_list_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = podcasts[position]
        holder.wordItemView.text = current.title
    }

    internal fun setPodcasts(podcasts: List<Podcast>) {
        this.podcasts = podcasts
        notifyDataSetChanged()
    }

    override fun getItemCount() = podcasts.size
}