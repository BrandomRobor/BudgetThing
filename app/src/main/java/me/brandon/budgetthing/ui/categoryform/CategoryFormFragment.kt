package me.brandon.budgetthing.ui.categoryform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.brandon.budgetthing.databinding.FragmentCategoryFormBinding
import me.brandon.budgetthing.util.CategoryType
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFormFragment : Fragment() {
    private var _binding: FragmentCategoryFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryFormViewModel by viewModel()

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

            catgoryFormDoneFab.setOnClickListener {
                onFormSubmit()
            }
            categoryFormDescriptionField.editText!!.setOnEditorActionListener { _, action, _ ->
                if (action == EditorInfo.IME_ACTION_DONE) {
                    onFormSubmit()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun onFormSubmit() {
        binding.apply {
            val selectedType = when (categoryFormButtonGroup.checkedButtonId) {
                categoryFormIncomeButton.id -> CategoryType.INCOME
                categoryFormExpenseButton.id -> CategoryType.EXPENSE
                else -> CategoryType.INCOME
            }

            viewModel.submitCategory(
                categoryFormNameField.editText!!.text.toString(),
                selectedType,
                categoryFormDescriptionField.editText!!.text.toString()
            )
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
