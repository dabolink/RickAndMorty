package com.dabolink.rickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dabolink.rickandmorty.databinding.FragmentMainBinding
import com.dabolink.rickandmorty.viewmodels.MainViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class EpisodeListFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val recyclerView: RecyclerView = binding.recyclerview
        val adapter = TextAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        mainViewModel.episodes.observe(viewLifecycleOwner, Observer {
            println("$it")
            adapter.setItems(it)
        })
        return root
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): EpisodeListFragment {
            return EpisodeListFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}