package com.luciana.cantina;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroPorcao extends Activity {

    //Views
    EditText nome, codBarra, qtPorcao, valorPorcao;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    String spinnerValue;

    //Variavel do banco de dados
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_porcao);

        //Adquire as instancias dos spinners
        spinner = (Spinner) findViewById(R.id.spinner_cadastro_porcao);
        String [] pesos = {"Kg" , "g", "L", "ml", "unidade(s)", "caixa(s)"};
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,pesos);
        spinner.setAdapter(adapter);

        //Adquire as instancias das views de aquisicao de dados
        nome = (EditText) findViewById(R.id.cadastro_porcao_nome);
        codBarra = (EditText) findViewById(R.id.cadastro_porcao_codBarra);
        qtPorcao = (EditText) findViewById(R.id.cadastro_porcao_qt);
        valorPorcao = (EditText) findViewById(R.id.cadastro_porcao_valor);

        //Inicializa o banco
        Log.i("BANCO_DADOS", "Adquiriu instancia do banco na classe CadastroUsuario");
        crud = databaseHelper.getInstance();
    }

    private boolean isEmpty(@NonNull EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void voltar(View view){
        finish();
    }

    public void cadastrarPorcao(View view){
        //Verifica se os campos estao preenchidos
        if(isEmpty(nome) | isEmpty(codBarra) | isEmpty(qtPorcao) | isEmpty(valorPorcao)) {
            Log.i("Cadastro_Porcao", "Campo(s) não preenchidos");
            Toast.makeText(this,"Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        }else {

            //Cadastra porcao
            String result;
            spinnerValue = spinner.getSelectedItem().toString();
            result = crud.addPorcao(codBarra.getText().toString(), valorPorcao.getText().toString(), spinnerValue, qtPorcao.getText().toString());

            //Avisa o usuario o resultado do cadastro
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
