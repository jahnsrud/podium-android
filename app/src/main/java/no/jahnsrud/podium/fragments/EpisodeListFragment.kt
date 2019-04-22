package no.jahnsrud.podium.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.fragment_podcast_list.*
import no.jahnsrud.podium.adapters.EpisodeAdapter
import no.jahnsrud.podium.database.EpisodeViewModel
import no.jahnsrud.podium.R

class EpisodeListFragment : androidx.fragment.app.Fragment() {

    private lateinit var episodeViewModel: EpisodeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_episodes_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

    }

    fun configureRecyclerView() {

        val ctx = context ?: return

        val adapter = EpisodeAdapter(ctx)
        list_recycler_view.adapter = adapter
        list_recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)

        episodeViewModel = ViewModelProviders.of(this).get(EpisodeViewModel::class.java)

        episodeViewModel.allEpisodes.observe(this, Observer { episodes ->
            // Update the cached copy of the pods in the adapter.
            episodes?.let { adapter.setEpisodes(it) }
        })

    }

    override fun onResume() {
        super.onResume()

    }



}