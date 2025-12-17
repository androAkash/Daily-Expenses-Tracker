package com.example.trackdailyexpenses.data.local

import androidx.room.TypeConverter
import com.example.trackdailyexpenses.data.local.entity.TransactionType

class Converters {
    @TypeConverter
    fun fromTransactionType(type: TransactionType): String{
        return type.name
    }
    @TypeConverter
    fun toTransactionType(name: String): TransactionType{
        return TransactionType.valueOf(name)
    }
}
/**
**Why needed?**
- Room can store: Int, String, Long, Boolean, etc.
- Room CANNOT store: Enums directly
- Converters translate: `INCOME` To `"INCOME"` (String)
*/
