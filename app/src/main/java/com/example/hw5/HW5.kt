package com.example.hw5

import android.app.Application
import com.google.android.material.color.DynamicColors

class HW5 : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}