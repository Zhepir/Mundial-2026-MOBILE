package com.example.practicatpi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.practicatpi.utils.normalizeFlag
import com.example.practicatpi.viewmodels.MundialViewModel

@Composable
fun DetallePartidoScreen(
    id: String,
    viewModel: MundialViewModel = viewModel()
) {

    val detalle by viewModel.detalle.collectAsState()

    LaunchedEffect(id) {
        viewModel.cargarDetalle(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFF2E7D32))
            .padding(16.dp)
    ) {

        Text(
            text = "Detalle del partido",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (detalle == null) {

            CircularProgressIndicator()

        } else {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
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
                                model = "https://flagcdn.com/w40/${normalizeFlag(detalle!!.flag1)}.png",
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = detalle!!.equipo1
                            )
                        }

                        Text("VS")

                        Row {

                            Text(
                                text = detalle!!.equipo2
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            AsyncImage(
                                model = "https://flagcdn.com/w40/${normalizeFlag(detalle!!.flag2)}.png",
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text("Grupo: ${detalle!!.grupo}")

                    Text("Fecha: ${detalle!!.fecha}")

                    Text("Estadio: ${detalle!!.estadio}")

                    Text("Precio: USD ${detalle!!.precio}")

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text("ID: ${detalle!!.id}")
                }
            }
        }
    }
}