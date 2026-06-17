package com.example.practicatpi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practicatpi.ui.navigation.Routes
import com.example.practicatpi.ui.state.PartidoListaUiState
import com.example.practicatpi.utils.normalizeFlag
import com.example.practicatpi.viewmodels.MundialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPartidosScreen(
    navController: NavController,
    viewModel: MundialViewModel = viewModel()
) {

    val uiState by viewModel.listaUiState.collectAsState()

    var grupoSeleccionado by remember {
        mutableStateOf("Todos")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.cargarPartidos()
    }

    val partidos = when (uiState) {
        is PartidoListaUiState.Success ->
            (uiState as PartidoListaUiState.Success).partidos

        else -> emptyList()
    }

    val grupos = listOf("Todos") +
            partidos.map { it.grupo }
                .distinct()
                .sorted()

    val partidosFiltrados =
        if (grupoSeleccionado == "Todos") {
            partidos
        } else {
            partidos.filter {
                it.grupo == grupoSeleccionado
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFF2E7D32))
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de partidos",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = grupoSeleccionado,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Filtrar por grupo",
                        color = Color.Black
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTrailingIconColor = Color.Black,
                    unfocusedTrailingIconColor = Color.Black
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier.background(Color.White)
            ) {

                grupos.forEach { grupo ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = grupo,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            grupoSeleccionado = grupo
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        when (uiState) {

            is PartidoListaUiState.Loading -> {

                CircularProgressIndicator()
            }

            is PartidoListaUiState.Error -> {

                Text(
                    text = (uiState as PartidoListaUiState.Error).mensaje,
                    color = Color.Red
                )
            }

            is PartidoListaUiState.Success -> {

                LazyColumn {

                    items(partidosFiltrados) { partido ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Row {

                                        AsyncImage(
                                            model = "https://flagcdn.com/w40/${normalizeFlag(partido.flag1)}.png",
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp)
                                        )

                                        Spacer(
                                            modifier = Modifier.width(8.dp)
                                        )

                                        Text(
                                            text = partido.equipo1
                                        )
                                    }

                                    Text("VS")

                                    Row {

                                        Text(
                                            text = partido.equipo2
                                        )

                                        Spacer(
                                            modifier = Modifier.width(8.dp)
                                        )

                                        AsyncImage(
                                            model = "https://flagcdn.com/w40/${normalizeFlag(partido.flag2)}.png",
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = "Grupo: ${partido.grupo}"
                                )

                                Text(
                                    text = "Fecha: ${partido.fecha}"
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                Button(
                                    onClick = {
                                        navController.navigate(
                                            "${Routes.DETALLE_PARTIDO}/${partido.id}"
                                        )
                                    }
                                ) {
                                    Text("Ver detalle")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}