package ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen

import dagger.Binds
import dagger.Module
import ru.cyber_eagle_owl.homework3.di.scopes.ActivityScope

@Module
abstract class PhotoDetailsModule {

    @Binds
    @ActivityScope
    abstract fun view(activity: PhotoDetailsActivity): PhotoDetailsMvp.View

    @Binds
    @ActivityScope
    abstract fun presenter(presenter: PhotoDetailsPresenter): PhotoDetailsMvp.Presenter

}