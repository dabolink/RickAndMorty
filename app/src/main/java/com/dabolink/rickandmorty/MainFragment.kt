package com.dabolink.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dabolink.rickandmorty.databinding.FragmentMainBinding
import com.dabolink.rickandmorty.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private lateinit var mAdapter: SectionsPagerAdapter
    private lateinit var mViewPager: ViewPager2
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val supportFragmentManager = childFragmentManager
        mAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        mViewPager = binding.viewPager
        mViewPager.adapter = mAdapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, mViewPager){ tab, position ->
            tab.text = requireContext().getText(TAB_TITLES[position])
        }.attach()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mViewPager.adapter = mAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}