package ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen

import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.MvpPresenter
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.MvpView

interface PhotoDetailsMvp {

    interface View : MvpView {

        fun showPhotoInfo(photo: PhotoDetailsPresentationEntity)
    }

    interface Presenter : MvpPresenter<View> {

        fun onPhotoIdReceived(photoId: Int)

        fun onStop()
    }

    interface Model {

    }
}