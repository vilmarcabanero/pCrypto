package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cryptocurrencyappyt.data.local.model.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: CoinDao

    companion object {
        const val DATABASE_NAME = "coin_db"
    }
}