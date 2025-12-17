package com.example.trackdailyexpenses.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.trackdailyexpenses.data.local.Converters
import com.example.trackdailyexpenses.data.local.dao.TransactionDao
import com.example.trackdailyexpenses.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExpenseDatabase: RoomDatabase(){
    abstract fun transactionDao (): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null
        fun getDatabase(context: Context) : ExpenseDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}