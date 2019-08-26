package no.apps.bedrock.domain.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import no.apps.bedrock.utils.DispatcherProvider
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class AppsViewModel<A : Any> : ViewModel(), CoroutineScope {
    protected val main = DispatcherProvider.main
    protected val io = DispatcherProvider.io
    protected val unconfined = DispatcherProvider.unconfined
    protected val bg = DispatcherProvider.computation
    protected val immediate = DispatcherProvider.mainImmediate

    private val job = SupervisorJob()
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
