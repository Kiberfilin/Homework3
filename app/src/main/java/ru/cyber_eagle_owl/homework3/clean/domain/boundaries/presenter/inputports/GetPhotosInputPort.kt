package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity

interface GetPhotosInputPort {

    fun execute(): Single<ArrayList<PhotoPresentationEntity>>
}