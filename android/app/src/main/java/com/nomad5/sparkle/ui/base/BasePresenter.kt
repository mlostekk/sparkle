package com.nomad5.sparkle.ui.base

import java.lang.ref.WeakReference

/****************************************************************************************************************************
 * The base interface used by all presenters
 *
 * This specifies the view attach and detach function
 *
 * Those should be called in onResume & onPause (or onCreate & on Destroy respectively, depending on the requirements)
 * to avoid memory leaks
 */
interface BasePresenter<V : BaseView> {

    var weakReference: WeakReference<V>?

    /****************************************************************************************************************************
     */
    val view: V?
        get() = weakReference?.get()

    /****************************************************************************************************************************
     */
    fun attachView(view: V) {
        this.weakReference = WeakReference(view)
    }

    /****************************************************************************************************************************
     */
    fun detachView() {
        this.weakReference = null
    }
}