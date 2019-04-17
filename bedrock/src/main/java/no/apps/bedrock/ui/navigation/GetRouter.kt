package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.Router

interface GetRouter {
    operator fun invoke(currentPageArgs: PageArgs?, nextPageArgs: PageArgs): Router
}
