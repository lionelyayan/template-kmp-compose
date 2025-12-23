package com.sssmobile.viewModels.baseViewModel

import com.sssmobile.api.BaseResponse
import com.sssmobile.data.repository.Test
import com.sssmobile.models.ResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel(
    private val testRepo: Test
) : BaseViewModel() {

    private val _test = MutableStateFlow<BaseResponse<ResponseModel>?>(null)
    val test: StateFlow<BaseResponse<ResponseModel>?> = _test

    private val _indexTest = MutableStateFlow(0)
    val indexTest = _indexTest.asStateFlow()

    fun nextUserDetailTest() {
        _indexTest.value++
        loadUserDetailTest(_indexTest.value)
    }

    fun loadUserDetailTest(id: Int) {
        scope.launch {
            val result = testRepo.getUserDetailTest(id)
            _test.value = result
        }
    }
}