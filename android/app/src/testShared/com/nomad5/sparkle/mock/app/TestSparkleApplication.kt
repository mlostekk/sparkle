package com.nomad5.sparkle.mock.app

import com.nomad5.sparkle.app.SparkleApplication
import com.nomad5.sparkle.injection.DaggerAppComponent
import com.nomad5.sparkle.mock.injection.TestStorageModule

/****************************************************************************************************************************
 * This is a special test application that delivers
 * a mocked storage module instead of the actual implementation
 * a mocked presenter object
 */
class TestSparkleApplication : SparkleApplication() {

    /****************************************************************************************************************************
     */
    override fun createComponent() {
        graph = DaggerAppComponent.builder()
                .storageModule(TestStorageModule())
                .build()
    }
}