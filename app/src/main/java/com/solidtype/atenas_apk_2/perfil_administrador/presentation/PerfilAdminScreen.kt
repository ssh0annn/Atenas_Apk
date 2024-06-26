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
                    val btn_g = view.findViewById<Button>(R.id.perfil_config_btnguardar)
                    val btn_c = view.findViewById<Button>(R.id.perfil_config_btncancelar)
                    val nombre_admin : EditText = view.findViewById(R.id.txt_perfil_nombre_admin)
                    val correo_admin : EditText = view.findViewById(R.id.txt_perfil_correo_admin)
                    val clave_admin : EditText = view.findViewById(R.id.txt_perfil_clave_admin)
                    val nombre_empresa : EditText = view.findViewById(R.id.txt_perfil_nombre_empresa_admin)
                    var direccion_empresa : EditText = view.findViewById(R.id.txt_perfil_direccion_empresa_admin)
                    var numero_empresa : EditText = view.findViewById(R.id.txt_perfil_numero_telefono_admin)
                    var numero_licencia : EditText = view.findViewById(R.id.txt_perfil_numero_licencia_admin)
                    var estado : EditText = view.findViewById(R.id.txt_perfil_estado_licencia_admin)
                    var fecha_caduca  : EditText = view.findViewById(R.id.txt_perfil_fecha_caduca_admin)
                    var fecha_compra : EditText = view.findViewById(R.id.txt_perfil_fecha_compra_admin)

                    if (uiState.perfilAdmin.first() != null){
                        nombre_admin.text = uiState.perfilAdmin.get(0)?.nombre?.toEditable() ?: "".toEditable()
                        correo_admin.text = uiState.perfilAdmin.get(0)?.correo?.toEditable() ?: "".toEditable()
                        nombre_empresa.text = uiState.perfilAdmin.get(0)?.nombre_negocio?.toEditable() ?: "".toEditable()
                        direccion_empresa.text = uiState.perfilAdmin.get(0)?.direccion_negocio?.toEditable() ?: "".toEditable()
                        numero_empresa.text = uiState.perfilAdmin.get(0)?.telefono?.toEditable() ?: "".toEditable()
                    }

                    btn_g.setOnClickListener {
                        viewModel.onEvent(PerfilEvent.UpdatePerfil(PerfilAdmin(
                            nombre_admin.text.toString(),
                            nombre_admin.text.toString(),
                            correo_admin.text.toString(),
                            numero_empresa.text.toString(),
                            direccion_empresa.text.toString(),
                            nombre_empresa.text.toString()
                        )))
                    }
                    btn_c.setOnClickListener {
                        viewModel.onEvent(PerfilEvent.VerPerfil)
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