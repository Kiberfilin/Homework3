package ru.cyber_eagle_owl.homework3.clean.data.repositories

import io.reactivex.Single
import ru.cyber_eagle_owl.homework3.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.mappers.mapListOfPhotosFromDataToPresentation
import ru.cyber_eagle_owl.homework3.clean.data.entities.mappers.mapPhotoDataDetailsToPresentation
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotoDetailsInputPort
import ru.cyber_eagle_owl.homework3.clean.domain.boundaries.repository.inputports.PhotosRepositoryGetPhotosInputPort
import timber.log.Timber

class PhotosRepository (var apiService: Api):
    PhotosRepositoryGetPhotosInputPort,
    PhotosRepositoryGetPhotoDetailsInputPort {

    override fun getPhotos(): Single<ArrayList<PhotoPresentationEntity>> {
        Timber.d("0=(====> getPhotos(): Single<ArrayList<PhotoPresentationEntity>>")

        return apiService.getPhotosSingle().map { listOfPhotosDataEntityes ->
            listOfPhotosDataEntityes.mapListOfPhotosFromDataToPresentation()
        }
    }

    override fun getPhotoDetails(photoId: Int): Single<PhotoDetailsPresentationEntity> {
        Timber.d("getPhotoDetails(photoId: Int): Single<PhotoDetailsPresentationEntity>")

        return apiService.getPhotoByIdSingle(photoId.toString())
                .flatMap { photoDataEntity ->
                    getAlbumSingle(photoDataEntity.albumId)
                        .map { albumDataEntity ->
                            mapPhotoDataDetailsToPresentation(photoDataEntity, albumDataEntity)
                        }
                }
    }

    private fun getAlbumSingle(albumId: Int): Single<AlbumDataEntity> {
        Timber.d("getAlbum(albumId: Int): AlbumDataEntity")

        return apiService.getAlbumByIdSingle(albumId.toString())
    }
}