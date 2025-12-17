package com.example.trackdailyexpenses.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trackdailyexpenses.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert (transaction : TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY timeStamp DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()
}
/*// When user clicks "Save" in dialog
transactionDao.insert(
    TransactionEntity(amount = 100.0, type = TransactionType.EXPENSE, note = "Food")
)*/