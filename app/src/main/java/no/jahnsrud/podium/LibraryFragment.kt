package no.jahnsrud.podium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onStart() {
        super.onStart()

        podcastButton.setOnClickListener({
            openPodcast()
        })


    }

    fun openPodcast() {
        Navigation.findNavController(this.view!!).navigate(R.id.action_libraryFragment_to_podcastFragment)
    }



}
