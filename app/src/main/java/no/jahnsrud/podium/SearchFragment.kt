package no.jahnsrud.podium


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    val FEATURED_URL:String = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"

    override fun onStart() {
        super.onStart()

        requestFeaturedPodcasts()
    }

    fun requestFeaturedPodcasts() {

        val feedParser = FeedParser()
        feedParser.requestFeaturedPodcasts(FEATURED_URL)



    }


}
