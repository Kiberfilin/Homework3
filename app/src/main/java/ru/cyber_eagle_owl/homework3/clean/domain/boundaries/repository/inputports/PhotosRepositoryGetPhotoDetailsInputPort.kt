package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity

interface PhotosRepositoryGetPhotoDetailsInputPort {

    fun getPhotoDetails(photoId: Int): Single<PhotoDetailsPresentationEntity>
}