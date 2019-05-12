package com.luciana.cantina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText user = (EditText) findViewById(R.id.loginUser);
        EditText psswd = (EditText) findViewById(R.id.loginPassword);

        if(user.getText().toString().equals("Usuário") && psswd.getText().toString().equals("Senha")){
            Intent intent = new Intent();
            intent.setClass(Login.this, Inicio.class);
            startActivity(intent);
        }
    }
}
