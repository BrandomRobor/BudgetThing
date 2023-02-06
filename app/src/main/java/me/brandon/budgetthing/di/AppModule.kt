package me.brandon.budgetthing.di

import androidx.room.Room
import me.brandon.budgetthing.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_db").build() }
    factory { get<AppDatabase>().transactionDao() }
    factory { get<AppDatabase>().transactionCategoryDao() }
}