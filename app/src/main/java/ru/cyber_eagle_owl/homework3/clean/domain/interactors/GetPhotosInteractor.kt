package ru.cyber_eagle_owl.homework3.clean.domain.interactors

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import timber.log.Timber
import javax.inject.Inject

class GetPhotosInteractor @Inject constructor(var dataInputPort: PhotosRepositoryGetPhotosInputPort) :
    GetPhotosInputPort {

    override fun execute(): Single<ArrayList<PhotoPresentationEntity>> {
        Timber.d("0=(====> execute()")

        return dataInputPort.getPhotos()
    }
}