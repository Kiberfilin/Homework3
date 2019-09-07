package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.outputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity

interface GetPhotosOutputPort {

    fun onPhotosSingleHasGotten(photosSingle: Single<ArrayList<PhotoPresentationEntity>>)
}