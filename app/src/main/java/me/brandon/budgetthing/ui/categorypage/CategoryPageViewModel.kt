package me.brandon.budgetthing.ui.categorypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.util.CategoryType

class CategoryPageViewModel(categoryDao: TransactionCategoryDao) : ViewModel() {
    val categoriesListState = categoryDao.getAllCategoriesOfType(CategoryType.INCOME)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
