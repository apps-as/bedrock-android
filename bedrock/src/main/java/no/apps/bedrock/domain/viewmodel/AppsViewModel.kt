package no.apps.bedrock.domain.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import no.apps.bedrock.utils.DispatcherProvider
import no.apps.bedrock.utils.StandardDispatcherProvider
import kotlin.coroutines.experimental.CoroutineContext

abstract class AppsViewModel<A>(
    private val dispatcherProvider: DispatcherProvider = StandardDispatcherProvider
) : ViewModel(), CoroutineScope {
    protected val main
        get() = dispatcherProvider.main
    protected val io
        get() = dispatcherProvider.io
    protected val unconfined
        get() = dispatcherProvider.unconfined
    protected val bg
        get() = dispatcherProvider.computation
    protected val immediate
        get() = dispatcherProvider.mainImmediate

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = main + job

    open fun setArgs(args: A) {
    }

    open fun onCreateView() {
    }

    open fun onDestroyView() {
    }

    open fun onAttach() {
    }

    fun onDetach() {
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}