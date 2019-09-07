package ru.cyber_eagle_owl.homework3.clean.data.entities.mappers

import ru.cyber_eagle_owl.homework3.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.data.PhotoDataEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import timber.log.Timber

fun PhotoDataEntity.mapPhotoFromDataToPresentation(): PhotoPresentationEntity =
    run {
        Timber.d("0=(====> PhotoDataEntity.mapPhotoFromDataToPresentation(): PhotoPresentationEntity")
        return PhotoPresentationEntity(id, title, thumbnailUrl)
    }

fun ArrayList<PhotoDataEntity>.mapListOfPhotosFromDataToPresentation(): ArrayList<PhotoPresentationEntity> =
    run {
        Timber.d("0=(====> ArrayList<PhotoDataEntity>.mapListOfPhotosFromDataToPresentation(): ArrayList<PhotoPresentationEntity>")
        val listOfPhotoPresentationEntityes = ArrayList<PhotoPresentationEntity>()
        forEach { photoDataEntity ->
            listOfPhotoPresentationEntityes.add(photoDataEntity.mapPhotoFromDataToPresentation())
        }
        return listOfPhotoPresentationEntityes
    }

fun mapPhotoDataDetailsToPresentation(
    photo: PhotoDataEntity,
    album: AlbumDataEntity
): PhotoDetailsPresentationEntity {
    return PhotoDetailsPresentationEntity(
        photo.id,
        photo.title,
        album.title,
        photo.url
    )
}