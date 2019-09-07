package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import dagger.Module
import dagger.Provides
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import ru.cyber_eagle_owl.homework3.di.scopes.ActivityScope

@Module
class MainListingScreenRepoModule {

    @Provides
    @ActivityScope
    fun providePhotosRepositoryGetPhotosInputPort(apiService: Api): PhotosRepositoryGetPhotosInputPort {
        return PhotosRepository(apiService)
    }
}