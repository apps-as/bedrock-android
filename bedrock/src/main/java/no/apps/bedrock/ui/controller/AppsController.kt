package no.apps.bedrock.ui.controller

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.viewbinding.ViewBinding
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
import no.apps.bedrock.utils.extensions.hideKeyboard
import no.apps.bedrock.utils.extensions.isKeyboardOpen
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class AppsController<B : ViewBinding, A : PageArgs, VMA : Any, VM : AppsViewModel<VMA>> @JvmOverloads constructor(
    bundle: Bundle? = null
) : LifecycleController(bundle), CoroutineScope {
    companion object {
        const val MAX_NAVIGATION_BLOCK = 1000L
    }

    private val viewModelStore = ViewModelStore()
    private val job = SupervisorJob()
    private var detachContinuation: Continuation<Unit>? = null
    private var pendingNavigation = false

    override val coroutineContext: CoroutineContext = DispatcherProvider.mainImmediate + job

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator
    protected lateinit var viewModel: VM
        private set
    protected val binding: B
        get() = mBinding ?: throw IllegalStateException("Binding has already detached")
    private var mBinding: B? = null
    abstract val viewModelClass: Class<VM>
    protected val pageArgs: A by lazy {
        args.toArgs()
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater): B

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
    ): View = inflateBinding(inflater).run {
        mBinding = this
        initView(root.context)
        viewModel.onCreateView()
        root
    }

    override fun onAttach(view: View) {
        viewModel.onAttach()
    }

    override fun onDetach(view: View) {
        viewModel.onDetach()
        detachContinuation?.resume(Unit)
    }

    override fun onDestroyView(view: View) {
        mBinding = null
        viewModel.onDestroyView()
    }

    override fun onDestroy() {
        viewModelStore.clear()
        job.cancel()
    }

    protected inline fun <T : Any?> withContext(block: (Context) -> T): T? = activity?.let {
        block(it)
    }

    protected fun <T : Any?> withBinding(block: B.() -> T): T? = mBinding?.let {
        block(it)
    }

    @CallSuper
    protected open fun navigate(args: PageArgs) =
        closeKeyboardThen {
            if (pendingNavigation || !isAttached) {
                Timber.w("Skipping navigation: pending: $pendingNavigation, attached: $isAttached")
                return@closeKeyboardThen
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
    protected open fun goBack(closeKeyboard: Boolean = true) =
        if (closeKeyboard)
            closeKeyboardThen {
                activity?.onBackPressed()
            }
        else
            activity?.onBackPressed()

    @CallSuper
    protected open fun initView(context: Context) {
    }

    private fun buildViewModel(factory: ViewModelProvider.Factory, clazz: Class<VM>): VM {
        return ViewModelProvider(viewModelStore, factory)[clazz]
    }

    protected inline fun <T : Any> LiveData<Event<T>>.handle(crossinline block: (T) -> Unit) {
        observe(this@AppsController, Event.EventObserver { block(it) })
    }

    protected inline fun <T> LiveData<T>.observe(crossinline block: (T) -> Unit) {
        observe(this@AppsController, { block(it) })
    }

    protected fun closeKeyboardThen(block: () -> Unit) {
        activity?.currentFocus
            ?.takeIf { activity?.isKeyboardOpen() == true }
            ?.hideKeyboard(block)
            ?: block()
    }
}