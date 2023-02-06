package me.brandon.budgetthing.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.brandon.budgetthing.db.dao.TransactionCategoryDao
import me.brandon.budgetthing.db.dao.TransactionDao
import me.brandon.budgetthing.db.entity.Transaction
import me.brandon.budgetthing.db.entity.TransactionCategory
import me.brandon.budgetthing.util.DateTypeConverter

@Database(
    entities = [
        Transaction::class,
        TransactionCategory::class
    ], version = 1, exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionCategoryDao(): TransactionCategoryDao
}
