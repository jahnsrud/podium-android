package no.jahnsrud.podium.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.*
import no.jahnsrud.podium.FeedParser
import no.jahnsrud.podium.R
import no.jahnsrud.podium.adapters.PodcastAdapter
import no.jahnsrud.podium.models.Podcast

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
        adapter.setPodcasts(betaPodcasts())

        podcastsRecyclerView.adapter = adapter
        podcastsRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx) as RecyclerView.LayoutManager?

        podcastsRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context!!,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        FeedParser().requestFeaturedPodcasts {
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

    fun betaPodcasts() : ArrayList<Podcast> {

        return arrayListOf(
            Podcast("ID1", "TITLE_1", "FEED_URL", "https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "DESCRIPTION"),
            Podcast("ID2", "TITLE_2", "FEED_URL", "https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "DESCRIPTION"),
            Podcast("ID3", "TITLE_3", "FEED_URL", "https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "DESCRIPTION"),
            Podcast("ID4", "TITLE_4", "FEED_URL", "https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "DESCRIPTION"),
            Podcast("ID5", "TITLE_5", "FEED_URL", "https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "DESCRIPTION")
        )

    }

    fun searchForPodcasts() {

        val search = searchTextField.text.toString()
        FeedParser().searchPodcastDirectory(search)
    }


}
