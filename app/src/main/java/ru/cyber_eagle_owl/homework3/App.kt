package ru.cyber_eagle_owl.homework3

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.cyber_eagle_owl.homework3.di.AppComponent
import ru.cyber_eagle_owl.homework3.di.AppModule
import ru.cyber_eagle_owl.homework3.di.DaggerAppComponent
import timber.log.Timber

class App : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        prepareTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        return appComponent
    }

    private fun prepareTimber() {
        Timber.plant(Timber.DebugTree())
    }
}