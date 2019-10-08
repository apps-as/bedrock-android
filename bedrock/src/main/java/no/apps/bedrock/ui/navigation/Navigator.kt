package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.Controller

interface Navigator {
    fun navigate(currentPage: PageArgs?, nextPage: PageArgs, targetController: Controller? = null)
}
