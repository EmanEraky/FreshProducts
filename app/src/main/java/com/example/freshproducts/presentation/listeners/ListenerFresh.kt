package com.example.freshproducts.presentation.listeners

import com.example.freshproducts.domain.model.Fresh

interface ListenerFresh {
    fun onClickHeart(fresh: Fresh)
}