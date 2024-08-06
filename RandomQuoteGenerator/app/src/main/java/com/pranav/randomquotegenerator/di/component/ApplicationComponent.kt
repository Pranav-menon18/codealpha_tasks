package com.pranav.randomquotegenerator.di.component

import android.content.Context
import com.pranav.randomquotegenerator.data.api.NetworkService
import com.pranav.randomquotegenerator.di.ApplicationContext
import com.pranav.randomquotegenerator.di.module.ApplicationModule
import com.pranav.randomquotegenerator.ui.QuoteApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: QuoteApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService
}