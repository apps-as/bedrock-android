package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler
import no.apps.bedrock.di.scope.ActivityScope
import no.apps.bedrock.ui.controller.NeedsTarget
import no.apps.bedrock.ui.controller.ProvidesTarget
import javax.inject.Inject

@Suppress("unused")
@ActivityScope
class ConductorNavigator @Inject constructor(
    private val getRouter: GetRouter,
    private val getNavigationContext: GetNavigationContext,
    private val getChangeHandler: GetChangeHandler,
    private val getController: GetController
) : Navigator {
    override fun navigate(currentPage: PageArgs?, nextPage: PageArgs) {
        val router = getRouter(currentPage, nextPage)
        val tag = nextPage.tag
        val nextController = getController(nextPage)
        val swapTransaction = RouterTransaction.with(nextController).tag(tag)
        if (!router.hasRootController() || currentPage == null) {
            router.setRoot(swapTransaction)
            return
        }
        val navigationContext = getNavigationContext(currentPage, nextPage)
        if (navigationContext.clearStack) {
            router.setRoot(swapTransaction)
            return
        }
        if (navigationContext.singleTop) {
            val currentStack = router.backstack
            val newStack = currentStack
                .filter { it.tag() != tag }
                .takeIf { it.isNotEmpty() }
            if (newStack == null) {
                router.setRoot(swapTransaction)
                return
            }
            if (newStack != currentStack) {
                router.setBackstack(newStack, SimpleSwapChangeHandler())
            }
        }
        if (nextController is NeedsTarget) {
            val currentController = router.backstack.lastOrNull()?.controller()
            nextController.targetController =
                (currentController as? ProvidesTarget)?.target ?: currentController
        }
        val changeHandler = getChangeHandler(currentPage, nextPage)
        val changeTransaction = swapTransaction
            .pushChangeHandler(changeHandler)
            .popChangeHandler(changeHandler)
        if (navigationContext.replaceTop) {
            router.replaceTopController(changeTransaction)
        } else {
            router.pushController(changeTransaction)
        }
    }
}