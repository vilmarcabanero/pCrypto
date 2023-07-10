package com.plcoding.cryptocurrencyappyt.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail

@Entity(tableName = "coin_detail")
data class CoinDetailEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    var tags: List<String>,
    var team: List<TeamMember>
) {
    fun toCoinDetail(): CoinDetail {
        return CoinDetail(
            coinId = id,
            name = name,
            description = description,
            symbol = symbol,
            rank = rank,
            isActive = isActive,
            tags = tags,
            team = team
        )
    }
}