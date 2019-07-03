package com.luciana.cantina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EspecialUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especial_user);
    }

    public void onClickBtn(View view){
        Intent it = new Intent(getBaseContext(), CadastroProdutoTab.class);
        startActivity(it);
    }

    public void onClickHistorico(View view){
        Intent it = new Intent(getBaseContext(), euHistorico.class);
        startActivity(it);
    }

    public void onClickVencidos(View view){
        Intent it = new Intent(getBaseContext(), euVencidos.class);
        startActivity(it);
    }

    public void onClickEstoque(View view){
        Intent it = new Intent(getBaseContext(), euEstoque.class);
        startActivity(it);
    }

    public void onClickMensagens(View view){
        Intent it = new Intent(getBaseContext(), euMensagens.class);
        startActivity(it);
    }
    public void onClickSair(View view){
        finish();
    }
}
