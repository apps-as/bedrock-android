package no.apps.bedrock.ui.navigation

data class NavigationContext(
    val clearStack: Boolean,
    val singleTop: Boolean,
    val replaceTop: Boolean
)