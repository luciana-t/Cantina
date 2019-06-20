package com.luciana.cantina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        //Inicializa o banco
        Log.i("BANCO_DADOS", "Adquiriu instancia do banco na classe CadastroUsuario");
        crud = databaseHelper.getInstance();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void cadastro(View view) {
        EditText user = (EditText) findViewById(R.id.CadastroUser);
        EditText email = (EditText) findViewById(R.id.CadastroEmail);
        EditText psswd = (EditText) findViewById(R.id.CadastroPassword);

        //Verifica se algum campo esta vazio
        if(isEmpty(user) | isEmpty(email) | isEmpty(psswd)) {
            Log.i("Cadastro", "Campo inválido");
            if(isEmpty(psswd))
                Toast.makeText(this,"Campo SENHA vazio.", Toast.LENGTH_SHORT).show();
            if(isEmpty(email))
                Toast.makeText(this,"Campo EMAIL vazio.", Toast.LENGTH_SHORT).show();
            if(isEmpty(user))
                Toast.makeText(this,"Campo NOME DE USUÁRIO vazio.", Toast.LENGTH_SHORT).show();
        }else{
            String result;
            result = crud.cadastrarUsuario(user.getText().toString(), email.getText().toString(), psswd.getText().toString(), 0);
            if(result != "Usuário cadastrado")
                Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(this,result+" com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
