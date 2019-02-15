package no.apps.bedrock.ui.controller

import com.bluelinelabs.conductor.Controller

interface NeedsTarget {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any, R : Any?> Controller.withTarget(block: T.() -> R): R? {
        val target = try {
            targetController as? T
        } catch (ignored: ClassCastException) {
            null
        }
        return target?.block()
    }
}