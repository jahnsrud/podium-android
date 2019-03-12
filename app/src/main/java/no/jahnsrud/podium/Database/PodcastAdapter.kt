package no.jahnsrud.podium.Database

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.jahnsrud.podium.Models.Podcast
import no.jahnsrud.podium.R

class PodcastAdapter(var podcasts: ArrayList<Podcast>) : RecyclerView.Adapter<PodcastAdapter.PodcastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PodcastHolder {

       return PodcastHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.fragment_podcast_list,
               parent,
               false
           )
       )
    }

    override fun getItemCount(): Int {
        return podcasts.count()
    }

    override fun onBindViewHolder(holder: PodcastHolder, index: Int) {
       val podcastItem:Podcast? = podcasts.get(index)
        holder.bindEpisodeItem(podcastItem)
    }

    class PodcastHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var view: View = view

        var episodeItem:Podcast? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            Log.d(javaClass.simpleName, "Hallo")
        }

        fun bindEpisodeItem(podcast:Podcast?){
            this.episodeItem = episodeItem
            // view.list_title.setText(episodeItem)
        }
    }


}
