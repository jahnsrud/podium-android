package no.jahnsrud.podium

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.RealmResults
import kotlinx.android.synthetic.main.podcast_list_item.view.*
import no.jahnsrud.podium.Models.Podcast

class PodcastAdapter(var podcasts: RealmResults<Podcast>) : RecyclerView.Adapter<PodcastAdapter.PodcastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PodcastHolder {

       return PodcastHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_podcast_list, parent, false))
    }

    fun refreshEvents(podcasts: RealmResults<Podcast>) {
        this.podcasts = podcasts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("Hvor mange: ${podcasts.count()}", "Hey")
        return podcasts.count() //To change body of created functions use File | Settings | File Templates.
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
