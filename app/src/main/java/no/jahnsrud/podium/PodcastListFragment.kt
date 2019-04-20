package no.jahnsrud.podium

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_podcast_list.*
import no.jahnsrud.podium.Database.PodcastAdapter
import no.jahnsrud.podium.Database.PodcastViewModel

class PodcastListFragment : Fragment() {

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
        list_recycler_view.layoutManager = LinearLayoutManager(ctx) as RecyclerView.LayoutManager?

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