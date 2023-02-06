package me.brandon.budgetthing.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.brandon.budgetthing.db.entity.TransactionCategory
import me.brandon.budgetthing.util.CategoryType

@Dao
interface TransactionCategoryDao {
    @Insert
    suspend fun insertCategory(category: TransactionCategory): Long

    @Update
    suspend fun updateCategory(category: TransactionCategory): Int

    @Delete
    suspend fun deleteCategory(category: TransactionCategory)

    @Query("SELECT * FROM TransactionCategory WHERE type = :type")
    fun getAllCategoriesOfType(type: CategoryType): Flow<List<TransactionCategory>>
}
