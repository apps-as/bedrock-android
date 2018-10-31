package no.apps.bedrock.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import dagger.android.DispatchingAndroidInjector
import no.apps.bedrock.di.conductor.HasControllerInjector
import no.apps.bedrock.ui.navigation.Navigator
import no.apps.bedrock.ui.navigation.PageArgs
import no.apps.bedrock.ui.navigation.RouterOwner
import javax.inject.Inject

abstract class AppsConductorActivity : AppsDaggerActivity(), HasControllerInjector, RouterOwner {
    @Inject
    override lateinit var controllerInjector: DispatchingAndroidInjector<Controller>

    @Inject
    lateinit var navigator: Navigator

    override lateinit var router: Router

    abstract val conductorContainer: ViewGroup
    protected open val defaultPageArgs: PageArgs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Conductor.attachRouter(
            this,
            conductorContainer,
            savedInstanceState
        )
        val defaultPageArgs = defaultPageArgs
        if (!router.hasRootController() && defaultPageArgs != null) {
            navigator.navigate(null, defaultPageArgs)
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        router.onActivityResult(requestCode, resultCode, data)
    }
}