package no.apps.bedrock.ui.controller

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.bluelinelabs.conductor.Controller

abstract class LifecycleController @JvmOverloads constructor(args: Bundle? = null) :
    Controller(args), LifecycleOwner {

    private val lifecycleOwner by lazy(mode = LazyThreadSafetyMode.NONE) {
        ControllerLifecycleOwner(this)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleOwner.lifecycle
    }
}