package com.solidtype.atenas_apk_2.perfil_administrador.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.solidtype.atenas_apk_2.R;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilEvent;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilUIState;
import java.util.Objects;

public class PefilAdministrador extends FrameLayout {
    private final EditText nombre_admin,correo_admin,clave_admin,nombre_empresa,direccion_empresa,numero_empresa,numero_licencia,estado,fecha_caduca,fecha_compra;
    public PefilAdministrador(Context context, PerfilUIState uiState, AdminViewModel viewModel) {
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
        numero_licencia = findViewById(R.id.txt_perfil_numero_licencia_admin);
        estado = findViewById(R.id.txt_perfil_estado_licencia_admin);
        fecha_caduca = findViewById(R.id.txt_perfil_fecha_caduca_admin);
        fecha_compra = findViewById(R.id.txt_perfil_fecha_compra_admin);
        Button btn_guardar = findViewById(R.id.perfil_config_btnguardar);
        Button btn_cancelar = findViewById(R.id.perfil_config_btncancelar);

        if (!uiState.getPerfilAdmin().isEmpty()) poner_datos(uiState);

        // BOTONES Y SUS ACCIONES
        btn_guardar.setOnClickListener(v -> viewModel.onEvent(PerfilEvent.UpdatePerfil));
        btn_cancelar.setOnClickListener(v -> poner_datos(uiState));
    }

    public void poner_datos(PerfilUIState uiState){
        nombre_admin.setText(uiState.getPerfilAdmin().get(0).getNombre());
        correo_admin.setText(uiState.getPerfilAdmin().get(0).getCorreo());
        clave_admin.setText(uiState.getPerfilAdmin().get(0).getClave());
        nombre_empresa.setText(uiState.getPerfilAdmin().get(0).getNombre_negocio());
        direccion_empresa.setText(uiState.getPerfilAdmin().get(0).getDireccion_negocio());
        numero_empresa.setText(uiState.getPerfilAdmin().get(0).getTelefono());
        numero_licencia.setText(uiState.getPerfilAdmin().get(0).getLicencia());
        if (uiState.getPerfilAdmin().get(0).getEstado()){
            estado.setText("Habilitado");
        }else{
            estado.setText("Desabilitada");
        }
        fecha_caduca.setText(Objects.requireNonNull(uiState.getPerfilAdmin().get(0).getFecha_caduca()).toString());
        fecha_compra.setText(Objects.requireNonNull(uiState.getPerfilAdmin().get(0).getFecha_compra()).toString());
    }
}