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
        var parentController: Controller?
        do {
            parentController = controller.parentController
            if (parentController == null) {
                val parentActivity = controller.activity
                if (parentActivity is HasControllerInjector) {
                    return parentActivity
                }

                val parentApplication = parentActivity?.application
                if (parentApplication is HasControllerInjector) {
                    return parentApplication
                }

                throw IllegalArgumentException(
                    "No injector was found for ${controller.javaClass.canonicalName}"
                )
            }
        } while (parentController !is HasControllerInjector)
        return parentController
    }
}