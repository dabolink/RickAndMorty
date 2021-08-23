package com.dabolink.rickandmorty.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dabolink.rickandmorty.databinding.CharacterFragmentBinding
import com.dabolink.rickandmorty.viewmodels.CharacterViewModel

class CharacterFragment : Fragment() {
    private var mCharID: Int = 0

    private var _binding: CharacterFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var characterVM: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mCharID = it.getInt(ARG_CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterFragmentBinding.inflate(inflater, container, false)
        val root = binding.root

        val vmFactory = CharacterViewModel.Factory(mCharID)
        characterVM = ViewModelProvider(this, vmFactory).get(CharacterViewModel::class.java)

        characterVM.character.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.image).into(binding.image)
            binding.nameTV.text = it.name
        })

        return root
    }

    companion object {
        const val ARG_CHARACTER_ID: String = "CHARACTER_ID"

        @JvmStatic
        fun newInstance(id: Int) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHARACTER_ID, id)
                }
            }
    }
}