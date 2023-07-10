package com.plcoding.cryptocurrencyappyt.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

@Entity(tableName = "coin")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
) {
    fun toCoin(): Coin {
        return Coin(
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol
        )
    }
}
