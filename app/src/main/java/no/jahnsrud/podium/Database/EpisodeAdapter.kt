package no.jahnsrud.podium.Database

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import no.jahnsrud.podium.Models.Podcast
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import no.jahnsrud.podium.AudioPlayer
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.PlaybackActivity
import no.jahnsrud.podium.PodcastActivity


class EpisodeAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var episodes = emptyList<Episode>() // Cached copy of pods

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeItemView: TextView = itemView.findViewById(no.jahnsrud.podium.R.id.list_title)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.podium.R.layout.episode_list_item, parent, false)
        return EpisodeViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val current = episodes[position]
        holder.episodeItemView.text = current.title
        holder.itemView.setOnClickListener() {

            AudioPlayer.playFromEpisode(current, Podcast("", "", "", ""))
            val intent = Intent(holder.itemView.context, PlaybackActivity::class.java)
            startActivity(holder.itemView.context, intent, null)

        }

    }


    internal fun setEpisodes(episodes: List<Episode>) {
        this.episodes = episodes
        notifyDataSetChanged()
    }

    override fun getItemCount() = episodes.size
}