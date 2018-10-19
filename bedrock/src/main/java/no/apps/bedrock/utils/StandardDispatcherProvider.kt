package no.apps.bedrock.utils

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Dispatchers

object StandardDispatcherProvider : DispatcherProvider {
    override val mainImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}