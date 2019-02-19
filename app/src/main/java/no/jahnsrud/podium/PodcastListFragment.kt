package no.jahnsrud.podium

import android.R.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.fragment_podcast_list.*


class PodcastListFragment : Fragment() {

    lateinit var layoutManager:LinearLayoutManager
    lateinit var adapter:PodcastAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_podcast_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)

        list_recycler_view.layoutManager = layoutManager
        adapter = PodcastAdapter(arrayListOf("String", "String to", "Hello"))
        list_recycler_view.adapter = adapter

    }




}