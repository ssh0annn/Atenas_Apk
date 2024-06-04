package com.solidtype.atenas_apk_2.perfil_administrador.presentation;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.solidtype.atenas_apk_2.R;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel;
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.PerfilUIState;

import java.util.List;

import javax.inject.Inject;

import kotlinx.coroutines.flow.MutableStateFlow;

public class PefilAdministrador extends AppCompatActivity {
    @Inject
    AdminViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pefil_administrador);
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        MutableStateFlow<PerfilUIState> perfilUI = viewModel.getUiState();
    }
}