package com.pranav.randomquotegenerator.data.repository

import com.pranav.randomquotegenerator.data.api.NetworkService
import com.pranav.randomquotegenerator.data.model.QuoteDataItem
import com.pranav.randomquotegenerator.di.ActivityScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityScope
class QuoteRepository @Inject constructor(private val networkService: NetworkService) {
    fun getRandomQuote(): Flow<QuoteDataItem> {
        return flow {
            val quotes = networkService.getRandomQuote()
            emit(quotes[0])
        }

    }
}