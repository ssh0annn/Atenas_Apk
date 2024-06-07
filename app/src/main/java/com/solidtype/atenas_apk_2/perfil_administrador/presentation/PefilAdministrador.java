package com.solidtype.atenas_apk_2.perfil_administrador.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelProvider;
import com.solidtype.atenas_apk_2.R;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilEvent;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilUIState;

import java.util.List;

import javax.inject.Inject;

import kotlinx.coroutines.flow.MutableStateFlow;

public class PefilAdministrador extends AppCompatActivity {
    private EditText nombre_admin,correo_admin,clave_admin,nombre_empresa,direccion_empresa,numero_empresa;
    private Button btn_guardar, btn_cancelar;
    @Inject
    AdminViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pefil_administrador);

        // OBJETO COMPOSEBLE INICADO Y ACTUANDO
        ComposeView hello = findViewById(R.id.jetpack);

        // INICIO DE VIEWMODEL Y UISTATE
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        MutableStateFlow<PerfilUIState> perfilUI = viewModel.getUiState();

        // INICIACION DE CAMPOS EN VIEW
        nombre_admin.findViewById(R.id.txt_perfil_nombre_admin);
        correo_admin.findViewById(R.id.txt_perfil_correo_admin);
        clave_admin.findViewById(R.id.txt_perfil_clave_admin);
        nombre_empresa.findViewById(R.id.txt_perfil_nombre_empresa_admin);
        direccion_empresa.findViewById(R.id.txt_perfil_direccion_empresa_admin);
        numero_empresa.findViewById(R.id.txt_perfil_numero_telefono_admin);
        btn_guardar.findViewById(R.id.perfil_config_btnguardar);
        btn_cancelar.findViewById(R.id.perfil_config_btncancelar);

        // RELLENADO DE CAMPOS
        nombre_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getNombre());
        correo_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getCorreo());
        clave_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getClave());
        nombre_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getNombre_negocio());
        direccion_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getDireccion_negocio());
        numero_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getTelefono());

        viewModel.onEvent(PerfilEvent.VerPerfil);
        
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onEvent(PerfilEvent.UpdatePerfil);
            }
        });
    }
}