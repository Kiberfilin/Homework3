package ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports

interface GetPhotoDetailsInputPort {

    fun execute(photoId: Int)
}