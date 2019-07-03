package com.luciana.cantina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class euVencidos extends AppCompatActivity {
    List<String[]> listaitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eu_vencidos);

        //Inicializa lista de resultados
        listaitem = new ArrayList<>();

        //Adquire resultados

    }
}
