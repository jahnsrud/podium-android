package no.jahnsrud.podium.Database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.jahnsrud.podium.Models.Podcast
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import kotlinx.android.synthetic.main.episode_list_item.view.*
import no.jahnsrud.podium.AudioPlayer
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Fragments.PlaybackFragment
import no.jahnsrud.podium.PlaybackActivity


class EpisodeAdapter internal constructor(
    context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var episodes = emptyList<Episode>() // Cached copy of pods

    inner class EpisodeViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.podium.R.layout.episode_list_item, parent, false)
        return EpisodeViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val current = episodes[position]

        holder.itemView.list_title.text = current.title
        holder.itemView.list_description.text = current.description
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