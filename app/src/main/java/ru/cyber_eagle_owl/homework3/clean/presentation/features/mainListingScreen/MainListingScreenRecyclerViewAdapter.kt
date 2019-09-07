package ru.cyber_eagle_owl.homework3.clean.presentation.features.mainListingScreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxrelay2.BehaviorRelay
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.main_recycler_view_item.view.*
import ru.cyber_eagle_owl.homework3.R
import ru.cyber_eagle_owl.homework3.clean.data.entities.presentation.PhotoPresentationEntity
import timber.log.Timber

class MainListingScreenRecyclerViewAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainListingScreenRecyclerViewAdapter.PhotosViewHolder>() {

    internal val bag = CompositeDisposable()

    internal var photos = BehaviorRelay.createDefault(ArrayList<PhotoPresentationEntity>())

    init {
        photos.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                notifyDataSetChanged()
            }.addTo(bag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_view_item, parent, false)
        return PhotosViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return photos.value.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        Timber.d("onBindViewHolder")
        photos.value[position].also {
            holder.view.setTag(R.string.photo_item_tag, it.id)
            holder.bind(it)
        }
    }

    class PhotosViewHolder(var view: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val tmpPhotoId = it.getTag(R.string.photo_item_tag) as Int
                onItemClickListener.onItemClick(tmpPhotoId)
            }
        }

        fun bind(item: PhotoPresentationEntity) {
            view.apply {
                main_recycler_view_item_tv.text = item.title
                Picasso.get().load(item.thumbnailUrl).into(item_image_view)
            }
        }
    }

    interface OnItemClickListener {

        fun onItemClick(photoId: Int)
    }
}