package ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore

import javax.inject.Inject

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
    @Inject override lateinit var view : V
}