package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_listing_screen.*
import ru.cyber_eagle_owl.homework3.R
import ru.cyber_eagle_owl.homework3.base.BaseActivity
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework3.clean.presentation.features.itemDetailsScreen.PhotoDetailsActivity
import timber.log.Timber
import javax.inject.Inject

class MainListingScreenActivity : BaseActivity(), MainListingScreenMvp.View,
    MainListingScreenRecyclerViewAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: MainListingScreenMvp.Presenter

    private lateinit var photosAdapter: MainListingScreenRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_listing_screen)
        setSupportActionBar(toolbar)

        prepareRecyclerView()
        presenter.onViewCreated()
    }

    private fun prepareRecyclerView() {
        Timber.d("0=(====> prepareRecyclerView()")

        photosAdapter = MainListingScreenRecyclerViewAdapter(this)
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.adapter = photosAdapter
        photosAdapter.notifyDataSetChanged()
    }

    override fun showListOfPhotos(photos: ArrayList<PhotoPresentationEntity>) {
        Timber.d("0=(====> showListOfPhotos")

        photosAdapter.photos.accept(photos)
    }

    override fun onItemClick(photoId: Int) {
        /*Timber.d("onItemClick(photoId: Int) and photoId is: $photoId")
        val intent = Intent(this, PhotoDetailsActivity::class.java)
        intent.putExtra(AppConstants.PHOTO_ID_KEY, photoId)
        startActivity(intent)*/
        startFrom(this, photoId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_listing_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_exit -> {
                Timber.d("Menu item Exit was picked.")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        photosAdapter.bag.dispose() //todo спросить Denis Zhuravlev правильно ли идеологически в onStop() чистить диспозаблы адаптеров напрямую или надо искать способ запихнуть их внутрь presenter.onStop()?
        presenter.onStop()
        super.onStop()
    }

    companion object {
        const val PHOTO_ID_KEY: String = "PhotoId"
        fun startFrom(context: Context, photoId: Int) {
            val intent = Intent(context, PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_ID_KEY, photoId)
            context.startActivity(intent)
        }
    }
}
