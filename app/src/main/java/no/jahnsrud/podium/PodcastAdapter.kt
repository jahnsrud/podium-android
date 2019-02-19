package no.jahnsrud.podium

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.podcast_list_item.view.*

class PodcastAdapter(val arrayListeomthin: List<String>) : RecyclerView.Adapter<PodcastAdapter.PodcastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PodcastHolder {
       return PodcastHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_podcast_list, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayListeomthin.count() //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: PodcastHolder, position: Int) {
       val episodeItem = arrayListeomthin[position]
        holder.bindEpisodeItem(episodeItem)
    }

    class PodcastHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var view: View = view
        var episodeItem: String = "Test"
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            Log.d(javaClass.simpleName, "Hallo")
        }

        fun bindEpisodeItem(episodeItem: String){
            this.episodeItem = episodeItem
            // view.list_title.setText(episodeItem)
        }
    }


}
