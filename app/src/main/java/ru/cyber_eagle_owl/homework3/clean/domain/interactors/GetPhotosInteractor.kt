package ru.cyber_eagle_owl.homework3.clean.domain.interactors

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.outputports.GetPhotosOutputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort
import ru.cyber_eagle_owl.homework3.commons.NullBox
import timber.log.Timber

class GetPhotosInteractor(
    private val presentationOutputPort: GetPhotosOutputPort,
    private val dataInputPort: PhotosRepositoryGetPhotosInputPort
) : GetPhotosInputPort, PhotosRepositoryGetPhotosOutputPort {

    init {
        dataInputPort.setGetPhotosOutputPort(this)
    }

    override fun execute() {
        Timber.d("0=(====> execute()")

        dataInputPort.getPhotos()
    }

    override fun onPhotosSingleHasGotten(photosSingle: Single<ArrayList<PhotoPresentationEntity>>) {
        Timber.d("0=(====> onPhotosSingleHasGotten()")

        presentationOutputPort.onPhotosSingleHasGotten(photosSingle)
    }
}