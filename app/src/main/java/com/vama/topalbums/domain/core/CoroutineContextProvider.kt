package com.vama.topalbums.domain.core

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
}
