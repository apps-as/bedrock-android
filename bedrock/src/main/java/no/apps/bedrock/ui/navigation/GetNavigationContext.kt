package no.apps.bedrock.ui.navigation

interface GetNavigationContext {
    operator fun invoke(currentPageArgs: PageArgs?, nextPageArgs: PageArgs): NavigationContext
}