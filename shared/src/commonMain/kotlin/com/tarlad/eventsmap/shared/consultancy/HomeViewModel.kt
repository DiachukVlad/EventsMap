package com.tarlad.eventsmap.shared.consultancy

import com.tarlad.eventsmap.shared.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: ConsultancyRepo) : BaseViewModel() {
  private val _state = MutableStateFlow(ConsultanciesState())
  val state = _state.asStateFlow()

  override fun onStart() {
    viewModelScope.launch {
      val consultancies = repo.getAllConsultancy()
      _state.update { copy(consultancies = consultancies) }
    }
  }
}