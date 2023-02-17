package me.brandon.budgetthing.ui.categorypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.util.CategoryType

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryPageViewModel(categoryDao: TransactionCategoryDao) : ViewModel() {
    private val _categoryTab = MutableStateFlow(CategoryType.INCOME)
    val categoriesListState =
        _categoryTab.flatMapLatest {
            categoryDao.getAllCategoriesOfType(it)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setCategoryTypeOfTab(categoryType: CategoryType) = viewModelScope.launch {
        _categoryTab.value = categoryType
    }
}
