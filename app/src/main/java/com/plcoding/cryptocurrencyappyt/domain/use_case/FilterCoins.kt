package com.plcoding.cryptocurrencyappyt.domain.use_case

import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import javax.inject.Inject

class FilterCoins @Inject constructor(

) {
    suspend operator fun invoke(filterText: String): List<Coin> {
        return emptyList()
    }
}