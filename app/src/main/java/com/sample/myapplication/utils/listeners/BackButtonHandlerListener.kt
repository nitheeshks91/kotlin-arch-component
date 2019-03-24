package com.sample.myapplication.utils.listeners

interface BackButtonHandlerListener {
    fun addBackPressListener(listener: BackPressListener)
    fun removeBackPressListener(listener: BackPressListener)
}