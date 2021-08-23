package com.dabolink.rickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dabolink.rickandmorty.R
import com.dabolink.rickandmorty.databinding.FragmentTextListBinding
import com.dabolink.rickandmorty.viewmodels.MainViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class CharacterListFragment : Fragment() {
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

        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            mainViewModel.reloadCharacters()
            swipeRefresh.isRefreshing = false
        }
        val textAdapter = TextAdapter(object: OnItemClicked{
            override fun onItemClicked(item: TextItem) {
                activity?.supportFragmentManager?.commit {
                    replace(R.id.fragment_container, CharacterFragment.newInstance(item.id))
                    addToBackStack(null)
                }
            }

        })
        val llManager = LinearLayoutManager(context)

        with(binding.recyclerview) {
            layoutManager = llManager
            adapter = textAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if(llManager.findLastVisibleItemPosition() == (adapter?.itemCount ?: 0) - 1) {
                        mainViewModel.loadCharacters()
                    }
                }
            })
        }

        mainViewModel.characters.observe(viewLifecycleOwner, Observer {
            println("Characters :: $it")
            textAdapter.setItems(it)
        })

        return root
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): CharacterListFragment {
            return CharacterListFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}