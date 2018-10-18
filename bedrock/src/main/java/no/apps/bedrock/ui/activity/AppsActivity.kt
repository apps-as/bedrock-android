package no.apps.bedrock.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import no.apps.bedrock.di.conductor.HasControllerInjector
import no.apps.bedrock.ui.navigation.Navigator
import no.apps.bedrock.ui.navigation.PageArgs
import javax.inject.Inject

abstract class AppsActivity : AppCompatActivity(), HasControllerInjector, RouterOwner {
    @Inject
    override lateinit var controllerInjector: DispatchingAndroidInjector<Controller>

    @Inject
    lateinit var navigator: Navigator

    override lateinit var router: Router

    abstract val layoutId: Int
    abstract val conductorContainer: ViewGroup
    protected open val defaultPageArgs: PageArgs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        router = Conductor.attachRouter(this, conductorContainer, savedInstanceState)
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
}