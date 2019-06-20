package com.luciana.cantina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        //Inicializa o banco
        Log.i("BANCO_DADOS", "Adquiriu instancia do banco na classe Login");
        crud = databaseHelper.getInstance();
    }

    public void login(View view) {
        EditText user = (EditText) findViewById(R.id.loginUser);
        EditText psswd = (EditText) findViewById(R.id.loginPassword);

        String result;
        result = crud.validarUsuario(user.getText().toString(), psswd.getText().toString());
        if(result.equals("Usuário inválido") | result.equals("Senha inválida")){
            Toast.makeText(this,"Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
        }else if(result.equals("Usuário comum")){
            Log.i("Validacao", "Logado como usuário comum");
            //Loga no usuario comum
            Intent intent = new Intent();
            intent.setClass(Login.this, Inicio.class);
            startActivity(intent);
            finish();
        }else if(result.equals("Usuário especial")){
            Log.i("Validacao", "Logado como usuário especial");
            //Loga no usuario especial
            Intent intent = new Intent();
            intent.setClass(Login.this, EspecialUser.class);
            startActivity(intent);
        }
    }

    public void cadastro(View view) {
        Intent it = new Intent(getBaseContext(), CadastroUsuario.class);
        startActivity(it);
    }
}
