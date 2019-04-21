package no.jahnsrud.podium.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.jahnsrud.podium.FeedParser
import no.jahnsrud.podium.R

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

        // requestFeaturedPodcasts()
    }

    fun requestFeaturedPodcasts() {

        val feedParser = FeedParser()
        feedParser.requestFeaturedPodcasts()



    }


}
