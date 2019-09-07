package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports

import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort

interface PhotosRepositoryGetPhotosInputPort {

    fun setGetPhotosOutputPort(outputPort: PhotosRepositoryGetPhotosOutputPort)

    fun getPhotos()
}