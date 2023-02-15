package me.brandon.budgetthing.ui.categorypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.brandon.budgetthing.databinding.FragmentCategoryPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryPageFragment : Fragment() {
    private var _binding: FragmentCategoryPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryPageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val adapter = CategoryListAdapter()
            categoryPageRecyclerView.setHasFixedSize(true)
            categoryPageRecyclerView.adapter = adapter

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.categoriesListState.collectLatest {
                        adapter.submitList(it)
                    }
                }
            }

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
