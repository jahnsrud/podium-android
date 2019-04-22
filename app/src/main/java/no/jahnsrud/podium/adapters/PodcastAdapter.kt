package no.jahnsrud.podium.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import no.jahnsrud.podium.models.Podcast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.list_item_podcast.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import no.jahnsrud.podium.R


class PodcastAdapter internal constructor(
    context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var podcasts = emptyList<Podcast>() // Cached copy of pods

    inner class PodcastViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val podcastItemView: TextView = itemView.findViewById(no.jahnsrud.podium.R.id.list_title)
        val podcastDescriptionView: TextView = itemView.findViewById(no.jahnsrud.podium.R.id.list_description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.podium.R.layout.list_item_podcast, parent, false)

        return PodcastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val current = podcasts[position]

        holder.podcastItemView.text = current.title
        holder.podcastDescriptionView.text = current.id

        Glide.with(holder.itemView).load(current.coverImageUrl).apply {
            RequestOptions.bitmapTransform(RoundedCorners(14))
        } .into(holder.itemView.imageView)



        holder.itemView.setOnClickListener() {

            // TODO: ADD ARGS!
            Navigation.findNavController(holder.itemView!!).navigate(R.id.podcastFragment)
        }

    }


    internal fun setPodcasts(podcasts: List<Podcast>) {
        this.podcasts = podcasts
        notifyDataSetChanged()
    }

    override fun getItemCount() = podcasts.size
}