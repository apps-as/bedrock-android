package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.ControllerChangeHandler

interface GetChangeHandler {
    operator fun invoke(currentPageArgs: PageArgs?, nextPageArgs: PageArgs): ControllerChangeHandler
}