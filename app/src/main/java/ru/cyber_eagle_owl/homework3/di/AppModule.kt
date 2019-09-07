package ru.cyber_eagle_owl.homework3.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import io.reactivex.schedulers.Schedulers
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.cyber_eagle_owl.homework3.clean.data.repositories.PhotosRepository
import ru.cyber_eagle_owl.homework3.clean.data.web.Api
import ru.cyber_eagle_owl.homework3.di.scopes.ApplicationScope
import timber.log.Timber

@Module
class AppModule(private val application: DaggerApplication) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context = application.applicationContext

    @Provides
    @ApplicationScope
    fun provideRetrofitService(): Api {
        Timber.d("provideRetrofitService(): Api")

        val crtPinner = CertificatePinner.Builder()
            //ssllabs.com
            .add("jsonplaceholder.typicode.com", "sha256/58qRu/uxh4gFezqAcERupSkRYBlBAvfcw7mEjGPLnNU=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(crtPinner)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(Api::class.java)
    }

    @Provides
    @ApplicationScope
    fun providePhotosRepository(apiService: Api): PhotosRepository {
        return PhotosRepository(apiService)
    }
}