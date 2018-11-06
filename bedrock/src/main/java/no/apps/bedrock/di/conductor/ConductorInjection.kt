package no.apps.bedrock.di.conductor

import android.util.Log
import com.bluelinelabs.conductor.Controller
import dagger.internal.Preconditions

object ConductorInjection {
    fun inject(controller: Controller) {
        Preconditions.checkNotNull(controller, "controller")
        val hasDispatchingControllerInjector = findHasControllerInjector(controller)
        Log.d(
            "dagger.android",
            "An injector for ${controller.javaClass.canonicalName} was found in ${hasDispatchingControllerInjector.javaClass.canonicalName}"
        )
        val controllerInjector = hasDispatchingControllerInjector.controllerInjector
        Preconditions.checkNotNull(
            controllerInjector, "%s.controllerInjector() returned null",
            hasDispatchingControllerInjector.javaClass.canonicalName
        )
        controllerInjector.inject(controller)
    }

    private fun findHasControllerInjector(controller: Controller): HasControllerInjector {
        return findInjectorParent(controller) ?: controller.activity?.let {
            (it as? HasControllerInjector) ?: (it.application as? HasControllerInjector)
        }
        ?: throw IllegalArgumentException("No injector was found for ${controller.javaClass.canonicalName}")
    }

    private tailrec fun findInjectorParent(controller: Controller): HasControllerInjector? {
        return when (val parentController = controller.parentController ?: return null) {
            is HasControllerInjector -> parentController
            else -> findInjectorParent(parentController)
        }
    }
}