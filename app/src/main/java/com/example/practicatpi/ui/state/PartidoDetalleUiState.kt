package com.example.practicatpi.ui.state

import com.example.practicatpi.models.DTOPartidosDetalle

sealed class PartidoDetalleUiState {

    data object Loading : PartidoDetalleUiState()

    data class Success(
        val partido: DTOPartidosDetalle
    ) : PartidoDetalleUiState()

    data class Error(
        val mensaje: String
    ) : PartidoDetalleUiState()
}