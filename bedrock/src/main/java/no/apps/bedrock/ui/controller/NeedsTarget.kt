package no.apps.bedrock.ui.controller

import com.bluelinelabs.conductor.Controller

interface NeedsTarget

@Suppress("unused")
inline fun <reified T : Any, R : Any?> NeedsTarget.withTarget(block: T.() -> R): R? =
    ((this as? Controller)?.targetController as? T)?.block()