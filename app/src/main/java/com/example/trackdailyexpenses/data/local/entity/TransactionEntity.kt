package com.example.trackdailyexpenses.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
* - What data to store
* - How to organize it
* -What each field means
*/
enum class TransactionType{
    INCOME,
    EXPENSE
}
@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val amount: Double,
    val type: TransactionType,
    val note: String = "",
    val timeStamp: Long = System.currentTimeMillis()
)
