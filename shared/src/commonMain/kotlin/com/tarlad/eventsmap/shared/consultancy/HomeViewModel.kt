package com.tarlad.eventsmap.shared.consultancy

import com.tarlad.eventsmap.shared.base.BaseViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repo: ConsultancyRepo) : BaseViewModel() {
  private val consultancy = MutableStateFlow(listOf<Consultancy>())

  override fun onStart() {
    viewModelScope.launch {
      consultancy.emit(repo.getAllConsultancy())
    }
  }

  fun observeState(callback: (List<Consultancy>) -> Unit) {
    viewModelScope.launch {
      consultancy.collect(callback)
    }
  }
}