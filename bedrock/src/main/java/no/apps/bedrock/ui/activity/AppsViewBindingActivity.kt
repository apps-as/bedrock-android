package no.apps.bedrock.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class AppsViewBindingActivity<B: ViewBinding> : AppsDaggerActivity() {

    @Suppress("MemberVisibilityCanBePrivate")
    protected lateinit var binding: B
        private set

    abstract fun inflateBinding(layoutInflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
    }
}