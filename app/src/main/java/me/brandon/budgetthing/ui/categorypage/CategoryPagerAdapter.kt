package me.brandon.budgetthing.ui.categorypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.brandon.budgetthing.util.CategoryType

class CategoryPagerAdapter(
    fragment: Fragment, private val tabCount: Int
) : FragmentStateAdapter(fragment) {
    companion object {
        const val CATEGORY_TYPE_KEY = "CATEGORY_TYPE_KEY"
    }

    override fun getItemCount() = tabCount
    override fun createFragment(position: Int): Fragment {
        val fragment = CategoryListFragment()
        val categoryType = when (position) {
            0 -> CategoryType.EXPENSE
            1 -> CategoryType.INCOME
            else -> CategoryType.EXPENSE
        }
        fragment.arguments = Bundle().apply {
            putSerializable(CATEGORY_TYPE_KEY, categoryType)
        }
        return fragment
    }
}
