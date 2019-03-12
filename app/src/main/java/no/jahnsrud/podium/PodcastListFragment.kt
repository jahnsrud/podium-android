package no.jahnsrud.podium

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.fragment_podcast_list.*
import no.jahnsrud.podium.Database.PodcastAdapter

class PodcastListFragment : Fragment() {

    lateinit var layoutManager:LinearLayoutManager
    lateinit var adapter: PodcastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_podcast_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        list_recycler_view.layoutManager = layoutManager

        val podcastManager = PodcastManager()
        val podcasts = podcastManager.getAllPodcasts()

        adapter = PodcastAdapter(podcasts)
        list_recycler_view.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        val podcastManager = PodcastManager()
        podcastManager.betaPrintAllPodcasts()


    }




}