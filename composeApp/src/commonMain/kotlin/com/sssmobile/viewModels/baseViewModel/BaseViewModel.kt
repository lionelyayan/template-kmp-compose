package com.sssmobile.viewModels.baseViewModel

import kotlinx.coroutines.*

open class BaseViewModel {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    open fun onCleared() {
        job.cancel()
    }
}
