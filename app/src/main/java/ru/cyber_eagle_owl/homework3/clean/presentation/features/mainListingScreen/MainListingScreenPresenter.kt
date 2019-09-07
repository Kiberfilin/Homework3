package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.presenter.inputports.GetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.interactors.GetPhotosInteractor
import ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class MainListingScreenPresenter @Inject constructor() :
    BasePresenter<MainListingScreenMvp.View>(),
    MainListingScreenMvp.Presenter {

    private val bag = CompositeDisposable()

    @Inject
    lateinit var getPhotos: GetPhotosInputPort

    override fun onViewCreated() {
        Timber.d("0=(====> onViewCreated")

        bag.add(getPhotos.execute()
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