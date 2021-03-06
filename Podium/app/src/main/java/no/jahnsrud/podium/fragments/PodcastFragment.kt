package no.jahnsrud.podium.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_podcast.*
import kotlinx.coroutines.CoroutineScope
import no.jahnsrud.podium.models.Podcast
import no.jahnsrud.podium.R
import no.jahnsrud.podium.adapters.EpisodeAdapter
import no.jahnsrud.podium.database.*
import kotlin.coroutines.CoroutineContext


class PodcastFragment : androidx.fragment.app.Fragment() {

    var podcast: Podcast? = null
    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var podcastViewModel: PodcastViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_podcast, container, false)
    }

    override fun onStart() {
        super.onStart()

        actionButton.setOnClickListener({
            actionButtonPressed()
        })

        this.podcast = arguments?.getSerializable("podcast") as Podcast?

        populateData()
        configureRecyclerView()
    }


    fun configureRecyclerView() {

        val ctx = context ?: return

        val adapter = EpisodeAdapter(ctx)
        list_recycler_view.adapter = adapter
        list_recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)
        adapter.podcast = this.podcast!!

        episodeViewModel = ViewModelProviders.of(this).get(EpisodeViewModel::class.java)

        episodeViewModel.allEpisodes.observe(this, Observer { episodes ->
            // Update the cached copy of the pods in the adapter.
            episodes?.let { adapter.setEpisodes(it) }
        })

        list_recycler_view.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context!!,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

    }

    fun actionButtonPressed() {
        subscribe()

    }

    fun subscribe() {
        val model = PodcastViewModel(application = Application())
        this.podcast?.let { model.insert(it) }


    }


    fun populateData() {
        titleTextView.setText(podcast?.title)
        Glide.with(coverImageView)
            .load(podcast?.coverImageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .placeholder(R.drawable.placeholder_cover)
            .into(coverImageView)

        checkSubscription()


    }

    fun checkSubscription(){

        podcastViewModel = ViewModelProviders.of(this).get(PodcastViewModel::class.java)
        podcastViewModel.allPodcasts.observe(this, Observer { podcasts ->

            var contains = false

            podcasts.forEach({

                this.activity?.runOnUiThread(Runnable {

                    if (it.title.equals(this.podcast!!.title)) {
                        contains = true
                    } else {
                    }
                })


            })

            updateUI(contains)

        })

    }

    fun updateUI(isSubscribed:Boolean) {

        if (isSubscribed) {

            actionButton.visibility = View.GONE
        } else {
            actionButton.visibility = View.VISIBLE
        }


    }

}
