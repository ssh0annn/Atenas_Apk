package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

@Composable
fun Ejemplo(
    viewModel: ClientesViewModel = hiltViewModel()
) {

    Button(
        onClick = {
            viewModel.onUserEvent(
                ClienteEvent.AgregarClientes(
                    Personastodas.ClienteUI(
                        0,
                        "johan",
                        "fewfwe",
                        "dasdas",
                        "sdasdas"
                    )
                )
            )
        }
    ) {
        Button(
            onClick = {
                viewModel.onUserEvent(
                    ClienteEvent.EditarClientes(
                        Personastodas.ClienteUI(
                            0,
                            "johan",
                            "fewfwe",
                            "dasdas",
                            "sdasdas"
                        )
                    )
                )
            }
        ) {

            Button(
                onClick = {
                    viewModel.onUserEvent(
                        ClienteEvent.BorrarClientes(
                            Personastodas.ClienteUI(
                                0,
                                "johan",
                                "fewfwe",
                                "dasdas",
                                "sdasdas"
                            )
                        )
                    )
                }
            ) {
                Button(
                    onClick = { viewModel.onUserEvent(ClienteEvent.BuscarClientes("eferf")) }
                ) {


                }

                Button(
                    onClick = { viewModel.onUserEvent(ClienteEvent.MostrarClientesEvent) }
                ) {


                }

            }


        }
    }
}