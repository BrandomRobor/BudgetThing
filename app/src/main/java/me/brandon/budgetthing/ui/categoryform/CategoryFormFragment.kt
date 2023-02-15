package me.brandon.budgetthing.ui.categoryform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.brandon.budgetthing.databinding.FragmentCategoryFormBinding

class CategoryFormFragment : Fragment() {
    private var _binding: FragmentCategoryFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            categoryFormNameField.requestFocus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
