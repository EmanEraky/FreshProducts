package com.example.freshproducts.domain.usecases

import com.example.freshproducts.data.repo.ApiHelper
import com.example.freshproducts.data.repo.ApiOffHelper
import com.example.freshproducts.domain.model.Fresh
import javax.inject.Inject

class getMainFreshUseCase @Inject constructor(private val apiHelper: ApiHelper,private val apiOffHelper: ApiOffHelper) {

    suspend fun getFresh() =
        apiHelper.getFresh()


    suspend fun getAllFresh() =
        apiOffHelper.getAllFresh()


    suspend fun insertFresh(fresh: Fresh) =
        apiOffHelper.insertFresh(fresh)


    suspend fun deleteByPlantId(plantId: String) =
        apiOffHelper.deleteByPlantId(plantId)
}