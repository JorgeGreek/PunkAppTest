package com.example.punkapitestapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.BeersItem
import com.example.punkapitestapp.ui.common.ScopedViewModel
import com.example.usecase.GetBeersById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("characterId") private val beerId: Int,
    private val getBeersById: GetBeersById
    ) : ScopedViewModel() {

    private var _modelLoading = MutableLiveData<Boolean>()
    val modelLoading: LiveData<Boolean> get() = _modelLoading

    private var _model = MutableLiveData<List<BeersItem>>()
    val model: LiveData<List<BeersItem>> get() = _model

    init {
        getDetails()
    }


    private fun getDetails() {
        launch {
            _modelLoading.value = true
            _model.value = getBeersById.invoke(beerId)
            _modelLoading.value = false
        }
    }


}