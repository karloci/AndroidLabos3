package hr.tvz.android.mvpstjepanovic

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MVPStjepanovic : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}