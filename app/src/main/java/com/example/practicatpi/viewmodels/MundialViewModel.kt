package com.example.practicatpi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicatpi.network.RetrofitClient
import com.example.practicatpi.repository.MundialRepository
import com.example.practicatpi.ui.state.PartidoDetalleUiState
import com.example.practicatpi.ui.state.PartidoListaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MundialViewModel : ViewModel() {

    private val repository =
        MundialRepository(RetrofitClient.api)

    private val _listaUiState =
        MutableStateFlow<PartidoListaUiState>(
            PartidoListaUiState.Loading
        )

    val listaUiState: StateFlow<PartidoListaUiState>
        get() = _listaUiState

    private val _detalleUiState =
        MutableStateFlow<PartidoDetalleUiState>(
            PartidoDetalleUiState.Loading
        )

    val detalleUiState: StateFlow<PartidoDetalleUiState>
        get() = _detalleUiState

    fun cargarPartidos() {

        viewModelScope.launch {

            _listaUiState.value =
                PartidoListaUiState.Loading

            try {

                val partidos =
                    repository.fetchPartidosLista()

                _listaUiState.value =
                    PartidoListaUiState.Success(
                        partidos
                    )

            } catch (e: Exception) {

                _listaUiState.value =
                    PartidoListaUiState.Error(
                        e.message ?: "Error al cargar partidos"
                    )
            }
        }
    }

    fun cargarDetalle(
        id: String
    ) {

        viewModelScope.launch {

            _detalleUiState.value =
                PartidoDetalleUiState.Loading

            try {

                val detalle =
                    repository.fetchPartidoDetalleById(id)

                if (detalle != null) {

                    _detalleUiState.value =
                        PartidoDetalleUiState.Success(
                            detalle
                        )

                } else {

                    _detalleUiState.value =
                        PartidoDetalleUiState.Error(
                            "Partido no encontrado"
                        )
                }

            } catch (e: Exception) {

                _detalleUiState.value =
                    PartidoDetalleUiState.Error(
                        e.message ?: "Error al cargar detalle"
                    )
            }
        }
    }
}