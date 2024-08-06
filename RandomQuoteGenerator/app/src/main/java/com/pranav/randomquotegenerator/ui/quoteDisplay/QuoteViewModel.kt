package com.pranav.randomquotegenerator.ui.quoteDisplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pranav.randomquotegenerator.data.model.QuoteDataItem
import com.pranav.randomquotegenerator.data.repository.QuoteRepository
import com.pranav.randomquotegenerator.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<QuoteDataItem>>(UiState.Loading)
    val uiState: StateFlow<UiState<QuoteDataItem>> = _uiState

    fun getRandomQuote() {
        viewModelScope.launch {
            repository.getRandomQuote()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}