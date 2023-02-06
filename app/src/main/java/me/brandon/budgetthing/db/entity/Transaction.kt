package me.brandon.budgetthing.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    val amount: Long,
    val date: Date,
    val categoryId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
