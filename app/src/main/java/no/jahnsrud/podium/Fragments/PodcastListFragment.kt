package no.jahnsrud.podium.Fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.fragment_podcast_list.*
import no.jahnsrud.podium.Database.PodcastAdapter
import no.jahnsrud.podium.Database.PodcastViewModel
import no.jahnsrud.podium.R

class PodcastListFragment : androidx.fragment.app.Fragment() {

    private lateinit var podcastViewModel: PodcastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_podcast_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

    }

    fun configureRecyclerView() {

        val ctx = context ?: return

        val adapter = PodcastAdapter(ctx)
        list_recycler_view.adapter = adapter
        list_recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx) as androidx.recyclerview.widget.RecyclerView.LayoutManager?

        podcastViewModel = ViewModelProviders.of(this).get(PodcastViewModel::class.java)

        podcastViewModel.allPodcasts.observe(this, Observer { podcasts ->
            // Update the cached copy of the pods in the adapter.
            podcasts?.let { adapter.setPodcasts(it) }
        })



    }

    override fun onResume() {
        super.onResume()

    }



}