package no.apps.bedrock.ui.controller

import com.bluelinelabs.conductor.Controller

interface ProvidesTarget {
    val target: Controller?
}