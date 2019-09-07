package ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.interactors.GetPhotoDetailsInteractor
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class PhotoDetailsPresenter @Inject constructor() :
    BasePresenter<PhotoDetailsMvp.View>(),
    PhotoDetailsMvp.Presenter {

    private val bag = CompositeDisposable()

    @Inject
    lateinit var getPhotoDetails: GetPhotoDetailsInputPort

    override fun onPhotoIdReceived(photoId: Int) {

        getPhotoDetails.execute(photoId)
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