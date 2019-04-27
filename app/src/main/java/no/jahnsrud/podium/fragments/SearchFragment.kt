package no.jahnsrud.podium.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.*
import no.jahnsrud.podium.parsers.FeaturedContentParser
import no.jahnsrud.podium.R
import no.jahnsrud.podium.adapters.PodcastAdapter

class SearchFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onStart() {
        super.onStart()

        searchButton.setOnClickListener({
            searchForPodcasts()
        })

        requestFeaturedPodcasts()
        configureRecyclerView()

    }


    fun requestFeaturedPodcasts() {



    }

    fun configureRecyclerView() {

        val ctx = context ?: return

        val adapter = PodcastAdapter(ctx)

        podcastsRecyclerView.adapter = adapter
        podcastsRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx) as RecyclerView.LayoutManager?

        podcastsRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context!!,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        FeaturedContentParser().requestFeaturedPodcasts {
            println(" ----  SearchFragment er følgende:")
            it.forEach {
                println("TEST");
                println("${it.title}")
            }

            this.activity?.runOnUiThread(Runnable {
                adapter.setPodcasts(it)
            })


        }
    }

    fun searchForPodcasts() {

        val search = searchTextField.text.toString()
        FeaturedContentParser().searchPodcastDirectory(search)
    }


}
