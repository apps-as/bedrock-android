package no.apps.bedrock.ui.controller

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.bluelinelabs.conductor.Controller
import timber.log.Timber

internal class ControllerLifecycleOwner(
    controller: Controller
) : Controller.LifecycleListener(), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        controller.addLifecycleListener(object :
            Controller.LifecycleListener() {

            override fun preCreateView(controller: Controller) {
                trySetLifeCycleEvent(Lifecycle.Event.ON_CREATE)
            }

            override fun preAttach(controller: Controller, view: View) {
                trySetLifeCycleEvent(Lifecycle.Event.ON_START)
                trySetLifeCycleEvent(Lifecycle.Event.ON_RESUME)
            }

            override fun postDetach(controller: Controller, view: View) {
                trySetLifeCycleEvent(Lifecycle.Event.ON_PAUSE)
                trySetLifeCycleEvent(Lifecycle.Event.ON_STOP)
            }

            override fun postDestroyView(controller: Controller) {
                trySetLifeCycleEvent(Lifecycle.Event.ON_DESTROY)
            }
        })
    }

    private fun trySetLifeCycleEvent(event: Lifecycle.Event) {
        try {
            lifecycleRegistry.handleLifecycleEvent(event)
        } catch (t: Throwable) {
            Timber.w(t, "Couldn't set controller lifecycle --> Possible leak")
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}