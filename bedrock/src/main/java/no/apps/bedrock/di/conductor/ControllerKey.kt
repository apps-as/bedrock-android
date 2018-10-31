package no.apps.bedrock.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.MapKey
import kotlin.reflect.KClass


@Suppress("unused")
@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class ControllerKey(val value: KClass<out Controller>)