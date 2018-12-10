@file:Suppress("unused")

package no.apps.bedrock.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AsyncUseCase0<out T> {
    suspend fun execute(): T
    suspend operator fun invoke(): T =
        withContext(Dispatchers.Default) {
            execute()
        }
}

interface AsyncUseCase1<in P1, out T> {
    suspend fun execute(p1: P1): T
    suspend operator fun invoke(p1: P1): T =
        withContext(Dispatchers.Default) {
            execute(p1)
        }
}

interface AsyncUseCase2<in P1, in P2, out T> {
    suspend fun execute(p1: P1, p2: P2): T
    suspend operator fun invoke(p1: P1, p2: P2): T =
        withContext(Dispatchers.Default) {
            execute(p1, p2)
        }
}

interface AsyncUseCase3<in P1, in P2, in P3, out T> {
    suspend fun execute(p1: P1, p2: P2, p3: P3): T
    suspend operator fun invoke(p1: P1, p2: P2, p3: P3): T =
        withContext(Dispatchers.Default) {
            execute(p1, p2, p3)
        }
}

interface AsyncUseCase4<in P1, in P2, in P3, in P4, out T> {
    suspend fun execute(p1: P1, p2: P2, p3: P3, p4: P4): T
    suspend operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4): T =
        withContext(Dispatchers.Default) {
            execute(p1, p2, p3, p4)
        }
}