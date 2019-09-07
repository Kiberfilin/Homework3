package ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen

import dagger.Module
import dagger.Provides
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.di.scopes.ActivityScope

@Module
class PhotoDetailsRepoModule {


    @Provides
    @ActivityScope
    fun providePhotosRepositoryGetPhotoDetailsInputPort(apiService: Api): PhotosRepositoryGetPhotoDetailsInputPort {
        return PhotosRepository(apiService)
    }

}