package com.example.freshproducts.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.domain.usecases.getMainFreshUseCase
import com.example.freshproducts.utils.NetworkHelper
import com.example.freshproducts.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor
    (
    private val mainRepositoryUseCase: getMainFreshUseCase,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {
    private val _fresh = MutableLiveData<Resource<List<Fresh>>>()
    val freshProducts: MutableLiveData<Resource<List<Fresh>>>
        get() = _fresh

    private val _freshOff = MutableLiveData<Resource<List<Fresh>>>()
    val freshProductsOff: MutableLiveData<Resource<List<Fresh>>>
        get() = _freshOff

    private val _freshInsert = MutableLiveData<Resource<Long>>()
    val freshInsert: MutableLiveData<Resource<Long>>
        get() = _freshInsert

    fun getFreshResponse() {
        viewModelScope.launch {
            _fresh.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepositoryUseCase.getFresh().collect {
                    _fresh.postValue(it)
                }
            } else _fresh.postValue(Resource.error("No internet connection", null))
        }
    }


    fun insertFresh(fresh: Fresh) {
        viewModelScope.launch {
            _freshInsert.postValue(Resource.loading(null))
            mainRepositoryUseCase.insertFresh(fresh).collect {
                _freshInsert.postValue(it)
            }

        }
    }


    fun getAllFresh() {
        viewModelScope.launch {
            _freshOff.postValue(Resource.loading(null))

            mainRepositoryUseCase.getAllFresh().collect {
                _freshOff.postValue(it)
            }

        }
    }


    fun deleteByPlantId(plantId: String) {
        viewModelScope.launch {
            mainRepositoryUseCase.deleteByPlantId(plantId).collect{

            }

        }
    }
}