package no.apps.bedrock.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.android.DispatchingAndroidInjector

interface HasControllerInjector {
    val controllerInjector: DispatchingAndroidInjector<Controller>
}