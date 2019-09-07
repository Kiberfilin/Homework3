package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity

interface PhotosRepositoryGetPhotoDetailsOutputPort {

    fun onPhotoDetailsSingleHasGotten(photoDetailsSingle : Single<PhotoDetailsPresentationEntity>)

}