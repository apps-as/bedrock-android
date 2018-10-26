package no.apps.bedrock.utils

import androidx.lifecycle.Observer

class Event<out T : Any>(content: T) {
    private var nullableContent: T? = content

    private fun getContentIfNotHandled(): T? {
        val currentContent = nullableContent
        if (currentContent != null) {
            nullableContent = null
        }
        return currentContent
    }

    class EventObserver<T : Any>(private val onEventUnhandledContent: (T) -> Unit) :
        Observer<Event<T>> {
        override fun onChanged(event: Event<T>?) {
            event?.getContentIfNotHandled()?.let { value ->
                onEventUnhandledContent(value)
            }
        }
    }
}


