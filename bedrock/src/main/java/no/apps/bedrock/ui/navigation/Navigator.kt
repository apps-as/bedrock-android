package no.apps.bedrock.ui.navigation

interface Navigator {
    fun navigate(currentPage: PageArgs?, nextPage: PageArgs)
}
