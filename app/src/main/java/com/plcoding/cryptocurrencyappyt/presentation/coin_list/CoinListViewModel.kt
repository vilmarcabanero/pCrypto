package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    private val _coinTextFieldState = mutableStateOf(CoinTextFieldState(hint = "Search for coin"))
    val coinTextFieldState: State<CoinTextFieldState> = _coinTextFieldState


    init {
        getCoins()
    }

    private fun getCoins(filter: String? = null) {
        getCoinsUseCase(filter).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAndSetFilteredCoins(filter: String) {
        if (filter.isNotBlank()) {
            getCoins(filter)
        } else {
            getCoins()
        }
    }


    fun setCoinTextFieldText(text: String) {
        _coinTextFieldState.value = _coinTextFieldState.value.copy(text = text)
    }

    fun toggleTextFieldHint(focusState: FocusState) {
        _coinTextFieldState.value = _coinTextFieldState.value.copy(
            isHintVisible = !focusState.isFocused &&
                    _coinTextFieldState.value.text.isBlank()
        )
    }
}