package me.brandon.budgetthing.ui.categorypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import me.brandon.budgetthing.R
import me.brandon.budgetthing.databinding.FragmentCategoryPageBinding

class CategoryPageFragment : Fragment() {
    private var _binding: FragmentCategoryPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val tabsArray = resources.getStringArray(R.array.category_tabs_names)
            val adapter = CategoryPagerAdapter(
                this@CategoryPageFragment, categoryPageTabLayout.tabCount
            )
            categoryPagePager.adapter = adapter
            TabLayoutMediator(categoryPageTabLayout, categoryPagePager) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()

            categoryPageAddFab.setOnClickListener {
                val action =
                    CategoryPageFragmentDirections.actionNavDrawerCategoryEntryToCategoryFormFragment()
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
