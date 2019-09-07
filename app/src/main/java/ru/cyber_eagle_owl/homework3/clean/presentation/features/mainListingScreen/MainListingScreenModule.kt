package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import dagger.Binds
import dagger.Module
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.interactors.GetPhotosInteractor
import ru.cyber_eagle_owl.homework3.di.scopes.ActivityScope

@Module
abstract class MainListingScreenModule {

    @Binds
    @ActivityScope
    abstract fun view(activity: MainListingScreenActivity): MainListingScreenMvp.View

    @Binds
    @ActivityScope
    abstract fun presenter(presenter: MainListingScreenPresenter): MainListingScreenMvp.Presenter

    @Binds
    @ActivityScope
    abstract fun interactor(interactor: GetPhotosInteractor): GetPhotosInputPort
}