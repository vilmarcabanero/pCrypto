package com.plcoding.cryptocurrencyappyt.data.repository

import android.util.Log
import com.plcoding.cryptocurrencyappyt.data.local.CoinDao
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val dao: CoinDao
) : CoinRepository {

    override suspend fun getCoins(): List<Coin> {
        val localCoins = dao.getAllCoins().map { it.toCoin() }
        if (localCoins.isNotEmpty()) {
            Log.d("CoinRepoImpl", "LocalCoins is not empty.")
            return localCoins
        }
        val remoteCoins = api.getCoins()
        dao.insertAllCoins(remoteCoins.map { it.toCoinEntity() })

        Log.d("CoinRepoImpl", "LocalCoins is empty.")
        return remoteCoins.map { it.toCoin() }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        return api.getCoinById(coinId).toCoinDetail()
    }
}