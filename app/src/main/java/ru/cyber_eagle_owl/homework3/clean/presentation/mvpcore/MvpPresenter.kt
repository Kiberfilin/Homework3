package ru.cyber_eagle_owl.homework3.clean.presentation.mvpcore

interface MvpPresenter<V : MvpView> {
    val view: V
}