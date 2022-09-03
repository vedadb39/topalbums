package com.vama.topalbums.domain.model

sealed interface CachePolicy {
    object CacheThenNetwork : CachePolicy
    object NetworkOnly : CachePolicy
    object CacheOnly : CachePolicy
}
