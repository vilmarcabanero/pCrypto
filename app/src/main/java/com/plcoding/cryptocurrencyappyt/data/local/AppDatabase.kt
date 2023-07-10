package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.cryptocurrencyappyt.data.local.model.CoinDetailEntity
import com.plcoding.cryptocurrencyappyt.data.local.model.CoinEntity

@Database(
    entities = [CoinEntity::class, CoinDetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: CoinDao

    companion object {
        const val DATABASE_NAME = "coin_db"
    }
}