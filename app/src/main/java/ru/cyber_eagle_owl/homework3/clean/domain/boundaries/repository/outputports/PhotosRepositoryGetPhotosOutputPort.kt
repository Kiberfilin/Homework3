package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity

interface PhotosRepositoryGetPhotosOutputPort {

    fun onPhotosSingleHasGotten(photosSingle: Single<ArrayList<PhotoPresentationEntity>>)
}