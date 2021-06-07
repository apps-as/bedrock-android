package no.apps.bedrock.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.ResultReceiver
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard(block: (() -> Unit)? = null) {
    val closed =
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(
                windowToken,
                0,
                object : ResultReceiver(handler) {
                    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                        block?.invoke()
                    }
                }
            ).also {
                clearFocus()
            } ?: false

    if (!closed) {
        // Somehow closing keyboard wasn't a success but we still need to execute after-operation
        // ResultReceiver is not executed in this case
        block?.invoke()
    }
}

fun View.showKeyboard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
        requestFocus()
        showSoftInput(
            this@showKeyboard,
            InputMethodManager.SHOW_IMPLICIT
        )
    }


fun Activity.isKeyboardOpen(): Boolean =
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.isActive ?: false


fun Context.isLightTheme(): Boolean =
    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO