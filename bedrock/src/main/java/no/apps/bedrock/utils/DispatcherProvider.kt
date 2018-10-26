package no.apps.bedrock.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val computation: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
}
