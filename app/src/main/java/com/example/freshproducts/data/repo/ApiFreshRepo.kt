package com.example.freshproducts.data.repo

import com.example.freshproducts.data.api.ApiService
import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiFreshRepo @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getFresh(): Flow<Resource<List<Fresh>>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val fresh = apiService.getFresh()
                Resource.success(fresh)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }




}