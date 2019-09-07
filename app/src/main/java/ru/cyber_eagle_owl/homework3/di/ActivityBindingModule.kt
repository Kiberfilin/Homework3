package ru.cyber_eagle_owl.homework3.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen.PhotoDetailsActivity
import ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen.PhotoDetailsModule
import ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen.PhotoDetailsRepoModule
import ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen.MainListingScreenActivity
import ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen.MainListingScreenModule
import ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen.MainListingScreenRepoModule
import ru.cyber_eagle_owl.homework3.di.scopes.ActivityScope

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainListingScreenModule::class, MainListingScreenRepoModule::class])
    abstract fun bindMainActivity(): MainListingScreenActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PhotoDetailsModule::class, PhotoDetailsRepoModule::class])
    abstract fun bindPhotoDetailsActivity(): PhotoDetailsActivity
}