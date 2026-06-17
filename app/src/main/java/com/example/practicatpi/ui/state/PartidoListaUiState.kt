package com.example.practicatpi.ui.state

import com.example.practicatpi.models.DTOPartidosLista

sealed class PartidoListaUiState {

    data object Loading : PartidoListaUiState()

    data class Success(
        val partidos: List<DTOPartidosLista>
    ) : PartidoListaUiState()

    data class Error(
        val mensaje: String
    ) : PartidoListaUiState()
}