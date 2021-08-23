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
import com.dabolink.rickandmorty.databinding.FragmentTextListBinding
import com.dabolink.rickandmorty.viewmodels.MainViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class LocationListFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentTextListBinding? = null

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
    ): View? {

        _binding = FragmentTextListBinding.inflate(inflater, container, false)
        val root = binding.root

        val textAdapter = TextAdapter()
        val llManager = LinearLayoutManager(context)

        with(binding.recyclerview) {
            layoutManager = llManager
            adapter = textAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if(llManager.findLastVisibleItemPosition() == (adapter?.itemCount ?: 0) - 1) {
                        mainViewModel.loadLocations()
                    }
                }
            })
        }

        mainViewModel.locations.observe(viewLifecycleOwner, Observer {
            println("$it")
            textAdapter.setItems(it)
        })

        return root
    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance(): LocationListFragment {
            return LocationListFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}