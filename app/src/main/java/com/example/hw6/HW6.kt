package com.example.hw6

import android.app.Application
import com.google.android.material.color.DynamicColors

class HW6 : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}