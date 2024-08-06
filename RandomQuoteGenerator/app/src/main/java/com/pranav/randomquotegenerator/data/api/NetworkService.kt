package com.pranav.randomquotegenerator.data.api

import com.pranav.randomquotegenerator.data.model.QuoteDataItem
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("quotes")
    suspend fun getRandomQuote(): List<QuoteDataItem>

}