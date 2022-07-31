package com.example.freshproducts.data.repo

import com.example.freshproducts.db.dao.FreshDao
import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiFreshOffRepo @Inject constructor(private val freshDao: FreshDao) : ApiOffHelper {


    override suspend fun getAllFresh(): Flow<Resource<List<Fresh>>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val fresh = freshDao.getAll()
                Resource.success(fresh)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }

    override suspend fun insertFresh(fresh: Fresh): Flow<Resource<Long>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val _fresh = freshDao.insert(fresh)
                Resource.success(_fresh)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }

    override suspend fun deleteByPlantId(plantId: String): Flow<Resource<Int>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val _fresh = freshDao.deleteByPlantId(plantId)
                Resource.success(_fresh)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }

}