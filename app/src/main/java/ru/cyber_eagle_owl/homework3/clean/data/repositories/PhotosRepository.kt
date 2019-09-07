package ru.cyber_eagle_owl.homework3.clean.data.repositories

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.cyber_eagle_owl.homework3.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.mappers.mapListOfPhotosFromDataToPresentation
import ru.cyber_eagle_owl.homework3.clean.data.entities.mappers.mapPhotoDataDetailsToPresentation
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotoDetailsOutputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.outputports.PhotosRepositoryGetPhotosOutputPort
import timber.log.Timber
import javax.inject.Inject

class PhotosRepository (var apiService: Api):
    PhotosRepositoryGetPhotosInputPort,
    PhotosRepositoryGetPhotoDetailsInputPort {

    private lateinit var getPhotosInteractor: PhotosRepositoryGetPhotosOutputPort
    private lateinit var getPhotoDetailsInteractor: PhotosRepositoryGetPhotoDetailsOutputPort

    override fun setGetPhotosOutputPort(outputPort: PhotosRepositoryGetPhotosOutputPort) {
        Timber.d("0=(====> setGetPhotosOutputPort")

        getPhotosInteractor = outputPort
    }

    override fun setGetPhotoDetailsOutputPort(outputPort: PhotosRepositoryGetPhotoDetailsOutputPort) {
        Timber.d("0=(====> setGetPhotoDetailsOutputPort")

        getPhotoDetailsInteractor = outputPort
    }

    override fun getPhotos() {
        Timber.d("0=(====> getPhotos")

        getPhotosInteractor.onPhotosSingleHasGotten(apiService.getPhotosSingle().map { listOfPhotosDataEntityes ->
            listOfPhotosDataEntityes.mapListOfPhotosFromDataToPresentation()
        })
    }

    override fun getPhotoDetails(photoId: Int) {
        Timber.d("getPhotoDetails(photoId: Int)")

        getPhotoDetailsInteractor.onPhotoDetailsSingleHasGotten(
            apiService.getPhotoByIdSingle(photoId.toString())
                .flatMap { photoDataEntity ->
                    getAlbumSingle(photoDataEntity.albumId)
                        .map { albumDataEntity ->
                            mapPhotoDataDetailsToPresentation(photoDataEntity, albumDataEntity)
                        }
                }
        )
    }

    private fun getAlbumSingle(albumId: Int): Single<AlbumDataEntity> {
        Timber.d("getAlbum(albumId: Int): AlbumDataEntity")

        return apiService.getAlbumByIdSingle(albumId.toString())
    }
}