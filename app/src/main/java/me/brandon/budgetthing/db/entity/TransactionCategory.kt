package me.brandon.budgetthing.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.brandon.budgetthing.util.CategoryType

@Entity
data class TransactionCategory(
    val name: String,
    val type: CategoryType,
    val description: String? = null,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
