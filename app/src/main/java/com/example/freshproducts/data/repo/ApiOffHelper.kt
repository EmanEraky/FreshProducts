package com.example.freshproducts.data.repo

import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiOffHelper {
    suspend fun getAllFresh(): Flow<Resource<List<Fresh>>>
    suspend fun insertFresh(fresh: Fresh): Flow<Resource<Long>>
    suspend fun deleteByPlantId(plantId: String): Flow<Resource<Int>>




}