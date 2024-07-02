package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import android.app.Dialog
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilEvent
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral

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

                    //DIALOGO INICIADAS Y CON SUS BOTONES CORRESPONDIENTES DE DIALOGOPERFILADMIN
                    val dialog = Dialog(context)
                    dialog.setContentView(R.layout.dialog_password)
                    dialog.setCancelable(false)
                    val btnpassc = dialog.findViewById<Button>(R.id.dialog_confirmar_cambio)
                    val btnpasscancel = dialog.findViewById<ImageView>(R.id.dialog_volver)
                    val dclave = dialog.findViewById<EditText>(R.id.dialog_password)
                    val dnewclave = dialog.findViewById<EditText>(R.id.dialog_new_password)
                    val dconfirnewclave = dialog.findViewById<EditText>(R.id.dialog_confir_password)

                    //VARIABLE INICIADAS DE PERFILADMIN
                    val btng = view.findViewById<Button>(R.id.perfil_config_btnguardar)
                    val btnc = view.findViewById<Button>(R.id.perfil_config_btncambiarpass)
                    val nombreadmin : EditText = view.findViewById(R.id.txt_perfil_nombre_admin)
                    val correoadmin : EditText = view.findViewById(R.id.txt_perfil_correo_admin)
                    val claveadmin : EditText = view.findViewById(R.id.txt_perfil_clave_admin)
                    val nombreempresa : EditText = view.findViewById(R.id.txt_perfil_nombre_empresa_admin)
                    val direccionempresa : EditText = view.findViewById(R.id.txt_perfil_direccion_empresa_admin)
                    val numeroempresa : EditText = view.findViewById(R.id.txt_perfil_numero_telefono_admin)
                    val numerolicencia : EditText = view.findViewById(R.id.txt_perfil_numero_licencia_admin)
                    val estado : EditText = view.findViewById(R.id.txt_perfil_estado_licencia_admin)
                    val fechacaduca  : EditText = view.findViewById(R.id.txt_perfil_fecha_caduca_admin)
                    val fechacompra : EditText = view.findViewById(R.id.txt_perfil_fecha_compra_admin)

                    //VERIFICA SI LOS DATOS ESTAN PARA RELLENARLO
                    if (uiState.perfilAdmin.first() != null) {
                        nombreadmin.text = uiState.perfilAdmin[0]?.nombre?.toEditable() ?: "".toEditable()
                        correoadmin.text = uiState.perfilAdmin[0]?.correo?.toEditable() ?: "".toEditable()
                        direccionempresa.text = uiState.perfilAdmin[0]?.direccion_negocio?.toEditable() ?: "".toEditable()
                        numeroempresa.text = uiState.perfilAdmin[0]?.telefono?.toEditable() ?: "".toEditable()
                        claveadmin.text = uiState.perfilAdmin[0]?.clave?.toEditable() ?: "".toEditable()
                        numerolicencia.text = uiState.perfilAdmin[0]?.licencia?.toEditable() ?: "".toEditable()
                        nombreempresa.text = uiState.perfilAdmin[0]?.nombre_negocio?.toEditable() ?: "".toEditable()
                        if (uiState.perfilAdmin[0]?.estado == true) estado.text = "Habilitado".toEditable()
                        else estado.text = "Desabilitado".toEditable()
                        fechacaduca.text = uiState.perfilAdmin[0]?.fecha_caduca?.toString()?.toEditable() ?: "".toEditable()
                        fechacompra.text = uiState.perfilAdmin[0]?.fecha_compra?.toString()?.toEditable() ?: "".toEditable()
                    }

                    //ACCION DE LOS BOTONES TANTO DIALOGO COMO
                    btng.setOnClickListener {
                        uiState.perfilAdmin.first()?.id_administrador?.let {
                            viewModel.onEvent(
                                PerfilEvent.UpdatePerfil(
                                    administrador(
                                        id_administrador = it,
                                        nombre = nombreadmin.text.toString(),
                                        apellido = uiState.perfilAdmin[0]?.apellido.toString(),
                                        correo = correoadmin.text.toString(),
                                        telefono = numeroempresa.text.toString(),
                                        direccion_negocio = direccionempresa.text.toString(),
                                        nombre_negocio = nombreempresa.text.toString(),
                                        clave = claveadmin.text.toString(),
                                        licencia = uiState.perfilAdmin.first()!!.licencia,
                                        fecha_compra = uiState.perfilAdmin.first()!!.fecha_compra,
                                        fecha_caduca = uiState.perfilAdmin.first()!!.fecha_caduca,
                                        estado = uiState.perfilAdmin.first()!!.estado
                                    )
                                )
                            )
                        }
                        Toast.makeText(context,"Datos guardados",Toast.LENGTH_SHORT).show()
                    }
                    btnc.setOnClickListener {
                        dialog.show()
                    }
                    btnpasscancel.setOnClickListener {
                        dialog.dismiss()
                    }
                    btnpassc.setOnClickListener {
                        if (dclave.text.toString() != "" && dclave.text.toString() == claveadmin.text.toString()) {
                            if (dnewclave.text.toString() != "" && dconfirnewclave.text.toString() != "") {
                                if (dnewclave.text.toString() == dconfirnewclave.text.toString() && dnewclave.text.toString() != dclave.text.toString()) {
                                    //CAMBIAR CONTRASEÑA DE USUARIO
                                    Toast.makeText(context, "Contraseña guardada", Toast.LENGTH_SHORT).show()
                                    viewModel.onEvent(
                                        PerfilEvent.ChangePassword(
                                            correoadmin.text.toString(),
                                            dclave.text.toString(),
                                            dnewclave.text.toString()
                                        )
                                    )
                                    dialog.dismiss()
                                }else{
                                    Toast.makeText(context,"Contraseña nueva no son iguales",Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(context,"Contraseña nueva vacia",Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(context,"Contraseña anterior incorrecta",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            MenuLateral(navController)
        }

    }
}