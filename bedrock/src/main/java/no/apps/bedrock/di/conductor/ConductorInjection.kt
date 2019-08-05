package no.apps.bedrock.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.android.HasAndroidInjector
import dagger.internal.Preconditions

object ConductorInjection {
    fun inject(controller: Controller) {
        Preconditions.checkNotNull(controller, "controller")
        val hasDispatchingControllerInjector = findHasControllerInjector(controller)
        val controllerInjector = hasDispatchingControllerInjector.androidInjector()
        Preconditions.checkNotNull(
            controllerInjector, "%s.controllerInjector() returned null",
            hasDispatchingControllerInjector.javaClass.canonicalName
        )
        controllerInjector.inject(controller)
    }

    private fun findHasControllerInjector(controller: Controller): HasAndroidInjector {
        return findInjectorParent(controller) ?: controller.activity?.let {
            (it as? HasAndroidInjector) ?: (it.application as? HasAndroidInjector)
        }
        ?: throw IllegalArgumentException("No injector was found for ${controller.javaClass.canonicalName}")
    }

    private tailrec fun findInjectorParent(controller: Controller): HasAndroidInjector? {
        return when (val parentController = controller.parentController ?: return null) {
            is HasAndroidInjector -> parentController
            else -> findInjectorParent(parentController)
        }
    }
}