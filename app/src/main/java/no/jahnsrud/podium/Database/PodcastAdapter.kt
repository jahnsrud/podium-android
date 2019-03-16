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
import kotlinx.android.synthetic.main.episode_list_item.view.*
import kotlinx.android.synthetic.main.podcast_list_item.view.*
import no.jahnsrud.podium.PlaybackActivity
import no.jahnsrud.podium.PodcastActivity
import android.R.attr.src
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions








class PodcastAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var podcasts = emptyList<Podcast>() // Cached copy of pods

    inner class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val podcastItemView: TextView = itemView.findViewById(no.jahnsrud.podium.R.id.list_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.podium.R.layout.podcast_list_item, parent, false)

        return PodcastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val current = podcasts[position]

        holder.podcastItemView.text = current.title
        Glide.with(holder.itemView).load(current.coverImageUrl).apply {
            RequestOptions.bitmapTransform(RoundedCorners(14))
        } .into(holder.itemView.imageView)



        holder.itemView.setOnClickListener() {
            val intent = Intent(holder.itemView.context, PodcastActivity::class.java)
            intent.putExtra("podcast", current)
            startActivity(holder.itemView.context, intent, null)
        }

    }


    internal fun setPodcasts(podcasts: List<Podcast>) {
        this.podcasts = podcasts
        notifyDataSetChanged()
    }

    override fun getItemCount() = podcasts.size
}