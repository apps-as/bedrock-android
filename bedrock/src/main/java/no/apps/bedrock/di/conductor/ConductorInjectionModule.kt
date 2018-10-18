package no.apps.bedrock.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds

@Module
abstract class ConductorInjectionModule private constructor() {
    @Multibinds
    internal abstract fun controllerInjectorFactories(): Map<Class<out Controller>, AndroidInjector.Factory<out Controller>>
}