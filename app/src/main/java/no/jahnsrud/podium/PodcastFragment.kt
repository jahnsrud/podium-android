package no.jahnsrud.podium


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_podcast.*
import no.jahnsrud.podium.Models.Podcast

var podcast: Podcast? = null

class PodcastFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_podcast, container, false)
    }

    override fun onStart() {
        super.onStart()

        /* if (this.intent.getSerializableExtra("podcast") != null) {
            podcast = intent.getSerializableExtra("podcast") as? Podcast
        } else {

            //
            this.podcast = Podcast("hey01", "MockPod™", "", "https://i.pinimg.com/originals/33/07/37/330737871eb6b5258ff38f4d441bfc1e.png")
        }
*/


        populateData()
    }


    fun populateData() {
        titleTextView.setText(podcast?.title)
        Glide.with(coverImageView).load(podcast?.coverImageUrl).into(coverImageView)


    }

}
