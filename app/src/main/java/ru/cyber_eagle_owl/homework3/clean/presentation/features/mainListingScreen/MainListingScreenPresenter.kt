package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.outputports.GetPhotosOutputPort
import ru.cyber_eagle_owl.homework3.clean.domain.interactors.GetPhotosInteractor
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.BasePresenter
import ru.cyber_eagle_owl.homework3.commons.NullBox
import timber.log.Timber
import javax.inject.Inject

class MainListingScreenPresenter @Inject constructor(photosRepo: PhotosRepository) :
    BasePresenter<MainListingScreenMvp.View>(),
    MainListingScreenMvp.Presenter,
    GetPhotosOutputPort {

    private val bag = CompositeDisposable()

    private val getPhotos: GetPhotosInputPort = GetPhotosInteractor(this, photosRepo)

    override fun onViewCreated() {
        Timber.d("0=(====> onViewCreated")

        getPhotos.execute()
    }

    override fun onPhotosSingleHasGotten(photosSingle: Single<ArrayList<PhotoPresentationEntity>>) {
        Timber.d("0=(====> onPhotosSingleHasGotten")

        bag.add(photosSingle
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listOfPhotos ->
                listOfPhotos?.let { photos ->
                    view.showListOfPhotos(photos)
                }
            }, { error ->
                Timber.e(error)
            })
        )
    }

    override fun onStop() {
        bag.dispose()
    }
}