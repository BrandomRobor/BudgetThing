package me.brandon.budgetthing.ui.categoryform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.db.entity.TransactionCategory
import me.brandon.budgetthing.util.CategoryType

class CategoryFormViewModel(private val categoryDao: TransactionCategoryDao) : ViewModel() {
    fun submitCategory(name: String, type: CategoryType, description: String) =
        viewModelScope.launch {
            categoryDao.insertCategory(
                TransactionCategory(
                    name,
                    type,
                    description.ifBlank { null })
            )
        }
}
