package no.apps.bedrock.utils

import kotlinx.coroutines.experimental.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val computation: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
}
