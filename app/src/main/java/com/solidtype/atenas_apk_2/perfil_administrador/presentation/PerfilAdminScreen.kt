package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import android.text.Editable
import android.widget.Button
import android.widget.EditText
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
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin
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
            print(uiState.perfilAdmin.first())
            AndroidView(
                factory = { PefilAdministrador(context) },
                modifier = Modifier.fillMaxSize(),
                update = { view ->

                    //VARIABLE INICIADAS
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
                    if (uiState.perfilAdmin.first() != null){
                        nombreadmin.text
                            .append(uiState.perfilAdmin[0]?.nombre?.toEditable())
                            .append(" ".toEditable())
                            .append(uiState.perfilAdmin[0]?.apellido?.toEditable())
                            ?: "".toEditable()
                        correoadmin.text.append(uiState.perfilAdmin[0]?.correo?.toEditable())
                        nombreempresa.text = uiState.perfilAdmin[0]?.nombre_negocio?.toEditable() ?: "".toEditable()
                        direccionempresa.text = uiState.perfilAdmin[0]?.direccion_negocio?.toEditable() ?: "".toEditable()
                        numeroempresa.text = uiState.perfilAdmin[0]?.telefono?.toEditable() ?: "".toEditable()
                    }

                    //ACCION DE EL BOTON GUARDAR
                    btng.setOnClickListener {
                        viewModel.onEvent(
                            PerfilEvent.UpdatePerfil(
                                PerfilAdmin(
                                    nombreadmin.text.toString(),
                                    nombreadmin.text.toString(),
                                    correoadmin.text.toString(),
                                    numeroempresa.text.toString(),
                                    direccionempresa.text.toString(),
                                    nombreempresa.text.toString()
                                )
                            )
                        )
                        Toast.makeText(context,"Datos guardados",Toast.LENGTH_SHORT).show()
                    }
                }
            )
        } else {
            AndroidView(
                factory = { PefilAdministrador(context) },
                modifier = Modifier.fillMaxSize()
            )
            MenuLateral(navController)
        }
    }
}