package no.apps.bedrock.utils

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object DispatcherProvider {
    @JvmStatic
    val main: CoroutineContext
        get() = dispatcherOverride(Dispatchers.Main)
    @JvmStatic
    val io: CoroutineContext
        get() = dispatcherOverride(Dispatchers.IO)
    @JvmStatic
    val computation: CoroutineContext
        get() = dispatcherOverride(Dispatchers.Default)
    @JvmStatic
    val unconfined: CoroutineContext
        get() = dispatcherOverride(Dispatchers.Unconfined)
    @JvmStatic
    val mainImmediate: CoroutineContext
        get() = dispatcherOverride(Dispatchers.Main.immediate)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @JvmStatic
    var dispatcherOverride: (CoroutineContext) -> CoroutineContext = { it }
}
