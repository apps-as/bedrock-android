@file:Suppress("unused")

package no.apps.bedrock.domain.usecase

interface BlockingUseCase0<out T> {
    operator fun invoke(): T
}

interface BlockingUseCase1<in P1, out T> {
    operator fun invoke(p1: P1): T
}

interface BlockingUseCase2<in P1, in P2, out T> {
    operator fun invoke(p1: P1, p2: P2): T
}