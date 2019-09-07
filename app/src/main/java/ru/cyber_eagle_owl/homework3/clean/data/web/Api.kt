package ru.cyber_eagle_owl.homework3.clean.data.web

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.cyber_eagle_owl.homework3.clean.data.entities.data.AlbumDataEntity
import ru.cyber_eagle_owl.homework3.clean.data.entities.data.PhotoDataEntity

interface Api {

    @GET("photos")
    fun getPhotosSingle() : Single<ArrayList<PhotoDataEntity>>

    @GET("photos/{photoId}")
    fun getPhotoByIdSingle(@Path("photoId") photoId: String): Single<PhotoDataEntity>

    @GET("albums/{albumId}")
    fun getAlbumByIdSingle(@Path("albumId") albumId: String): Single<AlbumDataEntity>
}