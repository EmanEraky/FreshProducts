package com.example.freshproducts.data.repo

import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getFresh(): Flow<Resource<List<Fresh>>>




}