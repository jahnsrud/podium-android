package no.jahnsrud.podium.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import no.jahnsrud.podium.models.Podcast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.list_item_podcast.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import no.jahnsrud.podium.R
import no.jahnsrud.podium.fragments.PodcastFragment


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
        holder.podcastDescriptionView.text = current.publisher

        Glide.with(holder.itemView).
            load(current.coverImageUrl)
            .placeholder(R.drawable.placeholder_cover)
            .apply {
            RequestOptions.bitmapTransform(RoundedCorners(14))
        } .into(holder.itemView.imageView)


        // Pass the podcast to the receiving Fragment

        holder.itemView.setOnClickListener() {

            val args = bundleOf(
                "podcast" to current
            )


            findNavController(holder.itemView)
                .navigate(R.id.podcastFragment, args)
        }

    }


    internal fun setPodcasts(podcasts: List<Podcast>) {
        this.podcasts = podcasts
        notifyDataSetChanged()
    }

    override fun getItemCount() = podcasts.size
}