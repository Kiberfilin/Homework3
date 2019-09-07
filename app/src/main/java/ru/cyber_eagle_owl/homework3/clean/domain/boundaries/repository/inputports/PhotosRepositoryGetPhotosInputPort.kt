package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity

interface PhotosRepositoryGetPhotosInputPort {

    fun getPhotos(): Single<ArrayList<PhotoPresentationEntity>>
}