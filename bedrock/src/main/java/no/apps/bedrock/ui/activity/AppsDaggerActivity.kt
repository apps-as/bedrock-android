package no.apps.bedrock.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class AppsDaggerActivity : AppCompatActivity() {
    abstract val layoutId: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val id = layoutId
        if (id != null) {
            setContentView(id)
        }
    }
}