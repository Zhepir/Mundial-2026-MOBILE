package com.example.practicatpi.repository

import com.example.practicatpi.models.DTOPartidosDetalle
import com.example.practicatpi.models.DTOPartidosLista
import com.example.practicatpi.network.MundialApiService

class MundialRepository(private val api: MundialApiService) {

    suspend fun fetchPartidosLista(): List<DTOPartidosLista> {
        return api.getPartidosLista()
    }

    suspend fun fetchPartidosDetalle(): List<DTOPartidosDetalle> {
        return api.getPartidosDetalle()
    }

    suspend fun fetchPartidoDetalleById(
        id: String
    ): DTOPartidosDetalle? {

        return api.getPartidosDetalle()
            .find { it.id == id }
    }
}