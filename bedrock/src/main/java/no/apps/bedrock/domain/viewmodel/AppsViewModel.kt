package no.apps.bedrock.domain.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import no.apps.bedrock.utils.DispatcherProvider
import no.apps.bedrock.utils.StandardDispatcherProvider
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class AppsViewModel<A : Any>(
    dispatcherProvider: DispatcherProvider = StandardDispatcherProvider
) : ViewModel(), CoroutineScope {
    protected val main = dispatcherProvider.main
    protected val io = dispatcherProvider.io
    protected val unconfined = dispatcherProvider.unconfined
    protected val bg = dispatcherProvider.computation
    protected val immediate = dispatcherProvider.mainImmediate

    private val job = Job()
    override val coroutineContext: CoroutineContext = immediate + job

    lateinit var args: A

    open fun initialized() {}

    open fun onCreateView() {
    }

    open fun onDestroyView() {
    }

    open fun onAttach() {
    }

    open fun onDetach() {
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    internal fun trySetArgs(args: A) {
        if (!this::args.isInitialized) {
            this.args = args
            initialized()
        }
    }
}
