package no.apps.bedrock.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds

@Suppress("unused")
@Module
abstract class ConductorInjectionModule private constructor() {
    @Multibinds
    internal abstract fun controllerInjectorFactories(): Map<Class<out Controller>, AndroidInjector.Factory<*>>

    @Multibinds
    internal abstract fun controllerInjectorFactoriesWithStringKeys(): Map<String, AndroidInjector.Factory<*>>
}