package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.cryptocurrencyappyt.data.local.model.CoinDetailEntity
import com.plcoding.cryptocurrencyappyt.data.local.model.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin")
    suspend fun getAllCoins(): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCoins(coins: List<CoinEntity>)

    @Query("SELECT * FROM coin_detail WHERE id = :id")
    suspend fun getCoinDetails(id: String): CoinDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coin: CoinDetailEntity)
}