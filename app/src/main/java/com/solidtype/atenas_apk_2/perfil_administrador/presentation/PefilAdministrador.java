package com.solidtype.atenas_apk_2.perfil_administrador.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.solidtype.atenas_apk_2.R;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilEvent;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilUIState;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.flow.StateFlow;

@AndroidEntryPoint
public class PefilAdministrador extends FrameLayout {

    private EditText nombre_admin,correo_admin,clave_admin,nombre_empresa,direccion_empresa,numero_empresa;
    private Button btn_guardar, btn_cancelar;
    private StateFlow<PerfilUIState> perfilUI;

    public PefilAdministrador(Context context, AdminViewModel viewModel) {
        super(context);

        //Inflar la vista
        LayoutInflater.from(context).inflate(R.layout.activity_pefil_administrador, this, true);

        // INICIACION DE CAMPOS EN VIEW
        nombre_admin = findViewById(R.id.txt_perfil_nombre_admin);
        correo_admin = findViewById(R.id.txt_perfil_correo_admin);
        clave_admin = findViewById(R.id.txt_perfil_clave_admin);
        nombre_empresa = findViewById(R.id.txt_perfil_nombre_empresa_admin);
        direccion_empresa = findViewById(R.id.txt_perfil_direccion_empresa_admin);
        numero_empresa = findViewById(R.id.txt_perfil_numero_telefono_admin);
        btn_guardar = findViewById(R.id.perfil_config_btnguardar);
        btn_cancelar = findViewById(R.id.perfil_config_btncancelar);

        if (!perfilUI.getValue().getPerfilAdmin().isEmpty()) {

            // RELLENADO DE CAMPOS
            nombre_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getNombre());
            correo_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getCorreo());
            clave_admin.setText(perfilUI.getValue().getPerfilAdmin().get(0).getClave());
            nombre_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getNombre_negocio());
            direccion_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getDireccion_negocio());
            numero_empresa.setText(perfilUI.getValue().getPerfilAdmin().get(0).getTelefono());
        }
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onEvent(PerfilEvent.UpdatePerfil);
            }
        });
    }
}