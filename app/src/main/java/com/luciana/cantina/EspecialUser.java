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
        Intent it = new Intent(getBaseContext(), CadastroEstoque.class);
        startActivity(it);
    }
    public void onClickSair(View view){
        finish();
    }
}
