package no.jahnsrud.podium.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_podcast.*
import no.jahnsrud.podium.models.Podcast
import no.jahnsrud.podium.R


class PodcastFragment : androidx.fragment.app.Fragment() {

    var podcast: Podcast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_podcast, container, false)
    }

    override fun onStart() {
        super.onStart()

        this.podcast = arguments?.getSerializable("podcast") as Podcast?

        populateData()
    }


    fun populateData() {
        titleTextView.setText(podcast?.title)
        Glide.with(coverImageView).load(podcast?.coverImageUrl).into(coverImageView)

        if (isSubscribed()) {

        } else {

        }


    }

    fun isSubscribed() : Boolean {

        // TODO: Implement

        return true;
    }

}
