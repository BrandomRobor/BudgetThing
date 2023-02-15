package me.brandon.budgetthing.ui.categoryform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.brandon.budgetthing.R
import me.brandon.budgetthing.databinding.FragmentCategoryFormBinding
import me.brandon.budgetthing.util.CategoryType
import me.brandon.budgetthing.util.ValidationStatus
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
            categoryFormNameField.editText!!.addTextChangedListener {
                categoryFormNameField.error = null
            }

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

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.validationState.collectLatest {
                        when (it) {
                            ValidationStatus.CLEAR -> {}
                            ValidationStatus.PASS -> findNavController().navigateUp()
                            ValidationStatus.ERROR -> {
                                if (categoryFormNameField.editText!!.text.toString().isBlank()) {
                                    categoryFormNameField.error =
                                        getString(R.string.required_field_hint)
                                }
                            }
                        }
                    }
                }
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
