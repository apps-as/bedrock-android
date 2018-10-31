package no.apps.bedrock.ui.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf

interface PageArgs : Parcelable {
    companion object {
        const val ARGS_KEY = "arguments"
    }

    val tag: String
}

@Suppress("unused")
fun PageArgs.toBundle() = bundleOf(PageArgs.ARGS_KEY to this)
fun <T : PageArgs> Bundle.toArgs() =
    getParcelable<T>(PageArgs.ARGS_KEY)
        ?: throw IllegalStateException("Can't find arguments in bundle")