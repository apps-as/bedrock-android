package no.apps.bedrock.ui.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import no.apps.bedrock.di.conductor.ConductorInjection
import no.apps.bedrock.domain.viewmodel.AppsViewModel
import no.apps.bedrock.ui.navigation.Navigator
import no.apps.bedrock.ui.navigation.PageArgs
import no.apps.bedrock.ui.navigation.toArgs
import no.apps.bedrock.utils.DispatcherProvider
import no.apps.bedrock.utils.Event
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class AppsController<A : PageArgs, VMA : Any, VM : AppsViewModel<VMA>> @JvmOverloads constructor(
    bundle: Bundle? = null
) : LifecycleController(bundle), LayoutContainer, CoroutineScope {
    companion object {
        const val MAX_NAVIGATION_BLOCK = 1000L
    }

    private val viewModelStore = ViewModelStore()
    private val job = SupervisorJob()
    private var detachContinuation: Continuation<Unit>? = null
    private var pendingNavigation = false
    override var containerView: View? = null

    override val coroutineContext: CoroutineContext = DispatcherProvider.mainImmediate + job

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator
    protected lateinit var viewModel: VM
        private set
    abstract val layoutId: Int
    abstract val viewModelClass: Class<VM>
    protected val pageArgs: A by lazy {
        args.toArgs<A>()
    }

    override fun onContextAvailable(context: Context) {
        ConductorInjection.inject(this)
        viewModel = buildViewModel(viewModelFactory, viewModelClass).apply {
            trySetArgs(pageArgs.toViewModelArgs())
        }
    }

    abstract fun A.toViewModelArgs(): VMA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View = inflater.inflate(layoutId, container, false).also {
        containerView = it
        initView(it.context)
        viewModel.onCreateView()
    }

    override fun onAttach(view: View) {
        viewModel.onAttach()
    }

    override fun onDetach(view: View) {
        viewModel.onDetach()
        detachContinuation?.resume(Unit)
    }

    override fun onDestroyView(view: View) {
        clearFindViewByIdCache()
        containerView = null
        viewModel.onDestroyView()
    }

    override fun onDestroy() {
        viewModelStore.clear()
        job.cancel()
    }

    protected inline fun <T : Any?> withContext(block: (Context) -> T): T? {
        val context = activity
        return if (context != null) {
            block(context)
        } else {
            null
        }
    }

    @CallSuper
    protected open fun navigate(args: PageArgs) {
        if (pendingNavigation || !isAttached) {
            Timber.w("Skipping navigation: pending: $pendingNavigation, attached: $isAttached")
            return
        }
        pendingNavigation = true
        launch {
            withTimeoutOrNull(MAX_NAVIGATION_BLOCK) {
                suspendCoroutine<Unit> {
                    detachContinuation = it
                }
            }
            detachContinuation = null
            pendingNavigation = false
        }
        navigator.navigate(pageArgs, args, this)
    }

    @CallSuper
    protected open fun goBack() {
        activity?.onBackPressed()
    }

    protected open fun initView(context: Context) {}

    private fun buildViewModel(factory: ViewModelProvider.Factory, clazz: Class<VM>): VM {
        return ViewModelProvider(viewModelStore, factory)[clazz]
    }

    protected inline fun <T : Any> LiveData<Event<T>>.handle(crossinline block: (T) -> Unit) {
        observe(this@AppsController, Event.EventObserver { block(it) })
    }

    protected inline fun <T> LiveData<T>.observe(crossinline block: (T) -> Unit) {
        observe(this@AppsController, Observer { block(it) })
    }
}