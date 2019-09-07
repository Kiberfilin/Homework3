package ru.cyber_eagle_owl.homework3.clean.domain.interactors

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import timber.log.Timber
import javax.inject.Inject

class GetPhotoDetailsInteractor @Inject constructor(var dataInputPort: PhotosRepositoryGetPhotoDetailsInputPort) :
    GetPhotoDetailsInputPort {

    override fun execute(photoId: Int): Single<PhotoDetailsPresentationEntity> {
        Timber.d("0=(====> execute()")

        return dataInputPort.getPhotoDetails(photoId)
    }
}