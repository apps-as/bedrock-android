package no.apps.bedrock.domain.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

abstract class AppsViewModel<A> : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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