package me.brandon.budgetthing.ui.categoryform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.db.entity.TransactionCategory
import me.brandon.budgetthing.util.CategoryType
import me.brandon.budgetthing.util.ValidationStatus

class CategoryFormViewModel(private val categoryDao: TransactionCategoryDao) : ViewModel() {
    private val _validationState = MutableStateFlow(ValidationStatus.CLEAR)
    val validationState = _validationState.asStateFlow()

    fun submitCategory(name: String, type: CategoryType, description: String) =
        viewModelScope.launch {
            _validationState.value = ValidationStatus.CLEAR
            name.ifBlank { _validationState.value = ValidationStatus.ERROR }

            if (_validationState.value == ValidationStatus.CLEAR) {
                categoryDao.insertCategory(
                    TransactionCategory(
                        name,
                        type,
                        description.ifBlank { null })
                )
                _validationState.value = ValidationStatus.PASS
            }
        }
}
