package com.solidtype.atenas_apk_2.perfil_administrador.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.solidtype.atenas_apk_2.R;

public class PefilAdministrador extends FrameLayout {
    public PefilAdministrador(Context context) {
        super(context);

        //Inflar la vista
        LayoutInflater.from(context).inflate(R.layout.activity_pefil_administrador, this, true);
    }
}