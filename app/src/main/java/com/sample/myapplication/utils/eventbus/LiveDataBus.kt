package com.sample.myapplication.utils.eventbus

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.util.SparseArray

class LiveDataBus {

    companion object {

        private val sSubjectMap = SparseArray<EventLiveData>()

        /**
         * Get the live data or create it if it's not already in memory.
         */
        private fun getLiveData(subjectCode: Int): EventLiveData {
            var liveData: EventLiveData? = sSubjectMap.get(subjectCode)
            if (liveData == null) {
                liveData = EventLiveData(subjectCode)
                sSubjectMap.put(subjectCode, liveData)
            }
            return liveData
        }

        /**
         * Subscribe to the specified subject and listen for updates on that subject.
         */
        fun subscribe(subject: Int, lifecycle: LifecycleOwner, action: Observer<Any>) {
            getLiveData(subject).observe(lifecycle, action)
        }

        /**
         * Removes this subject when it has no observers.
         */
        fun unregister(subject: Int) {
            sSubjectMap.remove(subject)
        }

        /**
         * Publish an object to the specified subject for all subscribers of that subject.
         */
        fun publish(subject: Int, message: Any) {
            getLiveData(subject).update(message)
        }
    }
}