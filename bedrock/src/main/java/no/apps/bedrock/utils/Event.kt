package no.apps.bedrock.utils

import androidx.lifecycle.Observer

class Event<out T : Any>(private val content: T) {

    private var hasBeenHandled = false

    private fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
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


