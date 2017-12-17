package com.nomad5.sparkle.app

import android.app.Application
import com.nomad5.sparkle.injection.AppComponent
import com.nomad5.sparkle.injection.DaggerAppComponent
import com.nomad5.sparkle.injection.PresenterModule
import com.nomad5.sparkle.injection.StorageModule

/****************************************************************************************************************************
 * The main app entry point.
 *
 * AppComponent for dependency injection is created here.
 */
open class SparkleApplication : Application() {

    /****************************************************************************************************************************
     * Globally accessible injection graph
     */
    companion object {
        @JvmStatic lateinit var graph: AppComponent
    }

    /****************************************************************************************************************************
     */
    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    /****************************************************************************************************************************
     * Is is already designed to be open for being overwritten during unit / instrumentation tests
     */
    open fun createComponent() {
        graph = DaggerAppComponent.builder()
                .presenterModule(PresenterModule())
                .storageModule(StorageModule())
                .build()
    }
}
