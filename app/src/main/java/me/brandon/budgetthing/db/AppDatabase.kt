package me.brandon.budgetthing.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.db.dao.TransactionDao
import me.brandon.budgetthing.db.entity.Transaction
import me.brandon.budgetthing.db.entity.TransactionCategory

@Database(
    entities = [
        Transaction::class,
        TransactionCategory::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionCategoryDao(): TransactionCategoryDao
}
