package no.apps.bedrock.ui.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.bluelinelabs.conductor.archlifecycle.LifecycleController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*
import no.apps.bedrock.di.conductor.ConductorInjection
import no.apps.bedrock.domain.viewmodel.AppsViewModel
import no.apps.bedrock.ui.navigation.Navigator
import no.apps.bedrock.ui.navigation.PageArgs
import no.apps.bedrock.ui.navigation.toArgs
import no.apps.bedrock.utils.Event
import javax.inject.Inject

abstract class AppsController<A : PageArgs, VMA, VM : AppsViewModel<VMA>> @JvmOverloads constructor(
    bundle: Bundle? = null
) : LifecycleController(bundle), LayoutContainer {
    private val viewModelStore = ViewModelStore()
    override var containerView: View? = null

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
            setArgs(pageArgs.toViewModelArgs())
        }
    }

    abstract fun A.toViewModelArgs(): VMA

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(layoutId, container, false).also {
            containerView = it
            initView(it.context)
            viewModel.onCreateView()
        }
    }

    override fun onAttach(view: View) {
        viewModel.onAttach()
    }

    override fun onDetach(view: View) {
        viewModel.onDetach()
    }

    override fun onDestroyView(view: View) {
        clearFindViewByIdCache()
        containerView = null
        viewModel.onDestroyView()
    }

    override fun onDestroy() {
        viewModelStore.clear()
    }

    protected inline fun <T : Any?> withContext(block: (Context) -> T): T? {
        val context = activity
        return if (context != null) {
            block(context)
        } else {
            null
        }
    }

    protected fun navigate(args: PageArgs) {
        navigator.navigate(pageArgs, args)
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