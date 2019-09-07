package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity

interface GetPhotoDetailsInputPort {

    fun execute(photoId: Int): Single<PhotoDetailsPresentationEntity>
}