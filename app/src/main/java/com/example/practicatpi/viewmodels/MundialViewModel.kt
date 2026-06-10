package com.example.practicatpi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicatpi.models.DTOPartidosDetalle
import com.example.practicatpi.models.DTOPartidosLista
import com.example.practicatpi.network.RetrofitClient
import com.example.practicatpi.repository.MundialRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MundialViewModel : ViewModel() {

    private val repository =
        MundialRepository(RetrofitClient.api)

    private val _partidos =
        MutableStateFlow<List<DTOPartidosLista>>(emptyList())

    val partidos: StateFlow<List<DTOPartidosLista>>
        get() = _partidos

    private val _detalle =
        MutableStateFlow<DTOPartidosDetalle?>(null)

    val detalle: StateFlow<DTOPartidosDetalle?>
        get() = _detalle

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    fun cargarPartidos() {

        viewModelScope.launch {

            _isLoading.value = true

            try {
                _partidos.value =
                    repository.fetchPartidosLista()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun cargarDetalle(
        id: String
    ) {

        viewModelScope.launch {

            _detalle.value =
                repository.fetchPartidoDetalleById(id)
        }
    }
}