package ru.cyber_eagle_owl.homework3.clean.domain.interactors

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.outputports.GetPhotoDetailsOutputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotoDetailsOutputPort
import timber.log.Timber

class GetPhotoDetailsInteractor (
    private val presentationOutputPort: GetPhotoDetailsOutputPort,
    private val dataInputPort: PhotosRepositoryGetPhotoDetailsInputPort
) : GetPhotoDetailsInputPort, PhotosRepositoryGetPhotoDetailsOutputPort {

    init {
        dataInputPort.setGetPhotoDetailsOutputPort(this)
    }

    override fun execute(photoId: Int) {
        Timber.d("0=(====> execute()")

        dataInputPort.getPhotoDetails(photoId)
    }

    override fun onPhotoDetailsSingleHasGotten(photoDetailsSingle: Single<PhotoDetailsPresentationEntity>) {

        presentationOutputPort.onPhotoDetailsSingleHasGotten(photoDetailsSingle)
    }

}