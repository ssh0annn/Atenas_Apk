package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilEvent
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

@Composable
fun PerfilAdminScreen(navController: NavController, viewModel: AdminViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (uiState.perfilAdmin.isNotEmpty()) {
            println(uiState.perfilAdmin.first())
            AndroidView(
                factory = { PefilAdministrador(context) },
                modifier = Modifier.fillMaxSize(),
                update = { view ->
                    val btn_g = view.findViewById<Button>(R.id.perfil_config_btnguardar)
                    val btn_c = view.findViewById<Button>(R.id.perfil_config_btncancelar)
                    val nombre_admin: EditText = view.findViewById(R.id.txt_perfil_nombre_admin)
                    val correo_admin: EditText = view.findViewById(R.id.txt_perfil_correo_admin)
                    val clave_admin: EditText = view.findViewById(R.id.txt_perfil_clave_admin)
                    val nombre_empresa: EditText =
                        view.findViewById(R.id.txt_perfil_nombre_empresa_admin)
                    val direccion_empresa: EditText =
                        view.findViewById(R.id.txt_perfil_direccion_empresa_admin)
                    val numero_empresa: EditText =
                        view.findViewById(R.id.txt_perfil_numero_telefono_admin)
                    val numero_licencia: EditText =
                        view.findViewById(R.id.txt_perfil_numero_licencia_admin)
                    val estado: EditText = view.findViewById(R.id.txt_perfil_estado_licencia_admin)
                    val fecha_caduca: EditText =
                        view.findViewById(R.id.txt_perfil_fecha_caduca_admin)
                    val fecha_compra: EditText =
                        view.findViewById(R.id.txt_perfil_fecha_compra_admin)

                    if (uiState.perfilAdmin.first() != null) {
                        nombre_admin.text =
                            uiState.perfilAdmin[0]?.nombre?.toEditable() ?: "".toEditable()
                        correo_admin.text =
                            uiState.perfilAdmin[0]?.correo?.toEditable() ?: "".toEditable()
                        nombre_empresa.text =
                            uiState.perfilAdmin[0]?.nombre_negocio?.toEditable() ?: "".toEditable()
                        direccion_empresa.text =
                            uiState.perfilAdmin[0]?.direccion_negocio?.toEditable()
                                ?: "".toEditable()
                        numero_empresa.text =
                            uiState.perfilAdmin[0]?.telefono?.toEditable() ?: "".toEditable()
                        clave_admin.text =
                            uiState.perfilAdmin[0]?.clave?.toEditable() ?: "".toEditable()
                        numero_licencia.text =
                            uiState.perfilAdmin[0]?.licencia?.toEditable() ?: "".toEditable()
                        estado.text = uiState.perfilAdmin[0]?.estado?.toString()?.toEditable()
                            ?: "".toEditable()
                        fecha_caduca.text =
                            uiState.perfilAdmin[0]?.fecha_caduca?.toString()?.toEditable()
                                ?: "".toEditable()
                        fecha_compra.text =
                            uiState.perfilAdmin[0]?.fecha_compra?.toString()?.toEditable()
                                ?: "".toEditable()


                    btn_g.setOnClickListener {
                        uiState.perfilAdmin.first()?.id_administrador?.let {
                            viewModel.onEvent(
                                PerfilEvent.UpdatePerfil(
                                    administrador(
                                        id_administrador = it,
                                        nombre = nombre_admin.text.toString(),
                                        apellido = nombre_admin.text.toString(),
                                        correo = correo_admin.text.toString(),
                                        telefono = numero_empresa.text.toString(),
                                        direccion_negocio = direccion_empresa.text.toString(),
                                        nombre_negocio = nombre_empresa.text.toString(),
                                        clave = clave_admin.text.toString(),
                                        licencia = uiState.perfilAdmin.first()!!.licencia,
                                        fecha_compra = uiState.perfilAdmin.first()!!.fecha_compra,
                                        fecha_caduca = uiState.perfilAdmin.first()!!.fecha_caduca,
                                        estado = uiState.perfilAdmin.first()!!.estado
                                    )
                                )
                            )
                        }

                    }
                    }


                }
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Titulo(text = "No hay datos de Administrador")
        }
        MenuLateral(navController)
    }
}