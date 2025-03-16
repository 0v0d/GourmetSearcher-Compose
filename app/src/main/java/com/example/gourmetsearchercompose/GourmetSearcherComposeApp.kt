package com.example.gourmetsearchercompose

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class GourmetSearcherComposeApp : Application() {
    // Workaround for Dagger issue #3601
    @Inject
    @ApplicationContext
    lateinit var context: Context
}
