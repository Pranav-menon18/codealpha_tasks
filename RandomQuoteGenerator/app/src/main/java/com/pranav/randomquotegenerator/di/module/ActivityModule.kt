package com.pranav.randomquotegenerator.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pranav.randomquotegenerator.data.repository.QuoteRepository
import com.pranav.randomquotegenerator.di.ActivityContext
import com.pranav.randomquotegenerator.di.ActivityScope
import com.pranav.randomquotegenerator.ui.base.ViewModelProviderFactory
import com.pranav.randomquotegenerator.ui.quoteDisplay.QuoteViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideQuoteViewModel(repository: QuoteRepository): QuoteViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(QuoteViewModel::class) {
                QuoteViewModel(repository)
            })[QuoteViewModel::class.java]
    }
}