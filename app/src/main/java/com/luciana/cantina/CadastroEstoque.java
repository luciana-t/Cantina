package com.luciana.cantina;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEstoque extends Activity {

    //Views
    EditText codBarra, quantidade, valorCompra;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    String spinnerValue;

    //Variavel do banco de dados
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estoque);

        //Adquire as instancias dos spinners
        spinner = (Spinner) findViewById(R.id.spinner_cadastro_estoque);
        String [] pesos = {"Kg" , "g", "L", "ml", "unidade(s)", "caixa(s)"};
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pesos);
        spinner.setAdapter(adapter);

        //Adquire as instancias das views de aquisicao de dados
        codBarra = (EditText) findViewById(R.id.cadastro_estoque_codBarra);
        quantidade = (EditText) findViewById(R.id.cadastro_estoque_qt);
        valorCompra = (EditText) findViewById(R.id.cadastro_estoque_valor_compra);

        //Inicializa o banco
        Log.i("BANCO_DADOS", "Adquiriu instancia do banco na classe CadastroUsuario");
        crud = databaseHelper.getInstance();

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void voltar(View view){
        finish();
    }

    public void cadastrarEstoque(View view){
        //Verifica se os campos estao preenchidos
        if(isEmpty(codBarra) | isEmpty(quantidade) | isEmpty(valorCompra)) {
            Log.i("Cadastro_Estoque", "Campo(s) não preenchidos");
            Toast.makeText(this,"Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        }else {

            //Cadastra estoque
            String result;
            spinnerValue = spinner.getSelectedItem().toString();
            result = crud.addEstoque(codBarra.getText().toString(), quantidade.getText().toString(), valorCompra.getText().toString());

            //Avisa o usuario o resultado do cadastro
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
