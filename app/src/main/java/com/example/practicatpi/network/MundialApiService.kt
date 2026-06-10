package com.example.practicatpi.network

import com.example.practicatpi.models.DTOPartidosDetalle
import com.example.practicatpi.models.DTOPartidosLista
import retrofit2.http.GET


interface MundialApiService {

    @GET("PartidoLista")
    suspend fun getPartidosLista(): List<DTOPartidosLista>

    @GET("PartidoDetalle")
    suspend fun getPartidosDetalle(): List<DTOPartidosDetalle>

}