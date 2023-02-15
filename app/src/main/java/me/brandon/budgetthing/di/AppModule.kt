package me.brandon.budgetthing.di

import androidx.room.Room
import me.brandon.budgetthing.db.AppDatabase
import me.brandon.budgetthing.ui.categoryform.CategoryFormViewModel
import me.brandon.budgetthing.ui.categorypage.CategoryPageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_db").build() }
    factory { get<AppDatabase>().transactionDao() }
    factory { get<AppDatabase>().transactionCategoryDao() }
    viewModelOf(::CategoryFormViewModel)
    viewModelOf(::CategoryPageViewModel)
}
