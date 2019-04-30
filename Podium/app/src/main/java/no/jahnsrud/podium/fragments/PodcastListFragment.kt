package no.jahnsrud.podium.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_podcast_list.*
import no.jahnsrud.podium.adapters.PodcastAdapter
import no.jahnsrud.podium.database.PodcastViewModel
import no.jahnsrud.podium.R
import no.jahnsrud.podium.SwipeToDeleteCallback

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

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = list_recycler_view.adapter as PodcastAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(list_recycler_view)


    }

    override fun onResume() {
        super.onResume()

    }



}