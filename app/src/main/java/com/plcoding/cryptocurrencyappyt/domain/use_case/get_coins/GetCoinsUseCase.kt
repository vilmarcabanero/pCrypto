package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.repository.CoinRepositoryImpl
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(filter: String?): Flow<Resource<List<Coin>>> = flow {
        var finalCoins: List<Coin>
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins()
            finalCoins = coins
            filter?.let {
                finalCoins = filterCoins(filter, coins)
            }
            emit(Resource.Success<List<Coin>>(finalCoins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }

    private fun filterCoins(filter: String, coins: List<Coin>) = coins.filter {
        val output = "${it.rank}. ${it.name} (${it.symbol})"
        output.contains(filter, ignoreCase = true)
    }
}