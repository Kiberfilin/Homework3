package ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.outputports.GetPhotoDetailsOutputPort
import ru.cyber_eagle_owl.homework3.clean.domain.interactors.GetPhotoDetailsInteractor
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class PhotoDetailsPresenter @Inject constructor(photosRepo: PhotosRepository) :
    BasePresenter<PhotoDetailsMvp.View>(),
    PhotoDetailsMvp.Presenter,
    GetPhotoDetailsOutputPort {

    private val bag = CompositeDisposable()

    private val getPhotoDetails: GetPhotoDetailsInputPort =
        GetPhotoDetailsInteractor(this, photosRepo)

    override fun onPhotoIdReceived(photoId: Int) {

        getPhotoDetails.execute(photoId)
    }

    override fun onPhotoDetailsSingleHasGotten(photoDetailsSingle: Single<PhotoDetailsPresentationEntity>) {

        photoDetailsSingle
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photoDetails ->
                view.showPhotoInfo(photoDetails)
            }, { error ->
                Timber.e(error)
            }).addTo(bag)
    }

    override fun onStop() {
        bag.dispose()
    }
}