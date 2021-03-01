package no.apps.bedrock.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class AppsViewBindingActivity<B: ViewBinding> : AppsDaggerActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    @Suppress("MemberVisibilityCanBePrivate")
    protected lateinit var binding: B
        private set

    abstract fun bindToView(@LayoutRes layoutId: Int): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindToView(layoutId)
        setContentView(binding.root)
    }
}