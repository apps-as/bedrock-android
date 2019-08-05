package no.apps.bedrock

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@Suppress("unused")
abstract class AppsApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    abstract fun inject()

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}