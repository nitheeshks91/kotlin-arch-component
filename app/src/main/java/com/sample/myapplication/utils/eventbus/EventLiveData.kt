package com.sample.myapplication.utils.eventbus

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

class EventLiveData(private var mSubject: Int) : LiveData<Any>() {

    fun update(`object`: Any) {
        postValue(`object`)
    }

    override fun removeObserver(observer: Observer<Any>) {
        super.removeObserver(observer)
        if (!hasObservers()) {
            LiveDataBus.unregister(mSubject)
        }
    }

}