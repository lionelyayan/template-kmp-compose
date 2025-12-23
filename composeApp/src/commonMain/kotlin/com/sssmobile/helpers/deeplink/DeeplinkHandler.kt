package com.sssmobile.helpers.deeplink

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object DeeplinkHandler {

    private val _events =
        MutableSharedFlow<DeeplinkEvent>(extraBufferCapacity = 1)

    val events = _events.asSharedFlow()

    fun emit(event: DeeplinkEvent) {
        _events.tryEmit(event)
    }
}
