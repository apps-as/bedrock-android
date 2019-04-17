package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.Controller

interface GetController {
    operator fun invoke(pageArgs: PageArgs): Controller
}