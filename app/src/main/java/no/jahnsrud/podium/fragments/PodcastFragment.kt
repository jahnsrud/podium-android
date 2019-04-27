package no.jahnsrud.podium.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_podcast.*
import no.jahnsrud.podium.models.Podcast
import no.jahnsrud.podium.R
import no.jahnsrud.podium.database.PodcastDao
import no.jahnsrud.podium.database.PodcastViewModel


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

        actionButton.setOnClickListener({
            actionButtonPressed()
        })

        this.podcast = arguments?.getSerializable("podcast") as Podcast?


        populateData()
    }

    fun actionButtonPressed() {

        // TODO: Fix
        if (isSubscribed()) {

        } else {
        }

        subscribe()



    }

    fun delete() {

        /*
        val model = PodcastViewModel(application = Application())
        this.podcast?.let { model.delete(it) }
        */

    }

    fun subscribe() {
        val model = PodcastViewModel(application = Application())
        this.podcast?.let { model.insert(it) }


    }




    fun populateData() {
        titleTextView.setText(podcast?.title)
        Glide.with(coverImageView).load(podcast?.coverImageUrl)
            .placeholder(R.drawable.placeholder_cover)
            .into(coverImageView)

        if (isSubscribed()) {
            actionButton.text = "Play"
        } else {
            actionButton.text = "+ Subscribe"

        }


    }

    fun isSubscribed() : Boolean {

        // TODO: Implement

        return true;
    }

}
