package com.pranav.randomquotegenerator.di.component

import com.pranav.randomquotegenerator.di.ActivityScope
import com.pranav.randomquotegenerator.di.module.ActivityModule
import com.pranav.randomquotegenerator.ui.quoteDisplay.QuoteActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: QuoteActivity)
}