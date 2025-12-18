package com.example.trackdailyexpenses.data.local.repository

import com.example.trackdailyexpenses.data.local.dao.TransactionDao
import com.example.trackdailyexpenses.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val transactionDao: TransactionDao) {
    val allTransaction : Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()

    suspend fun insert(transaction: TransactionEntity){
        transactionDao.insert(transaction)
    }
    suspend fun delete(transaction: TransactionEntity){
        transactionDao.delete(transaction)
    }
    suspend fun deleteAll(){
        transactionDao.deleteAll()
    }
}