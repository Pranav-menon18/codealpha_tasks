package com.pranav.randomquotegenerator.ui

import android.app.Application
import com.pranav.randomquotegenerator.di.component.ApplicationComponent
import com.pranav.randomquotegenerator.di.module.ApplicationModule
import com.pranav.randomquotegenerator.di.component.DaggerApplicationComponent

class QuoteApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}