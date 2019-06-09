package com.luciana.cantina;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastroEstoque extends Activity {
    EditText nome, dtVal, qtdTotal, qtdPorcao, precoTotal, precoPorcao;
    TextView tx;
    Button add;
    int vez;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estoque);
        vez = 1;
        tx = (TextView) findViewById(R.id.tv_vez);
        tx.setText(""+vez);

        spinner = (Spinner) findViewById(R.id.sp1);
        String [] pesos = {"   Kg" , "    g", "    L", "   ml", "unidade", "caixas"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,pesos);
        spinner.setAdapter(adapter);

        nome = (EditText) findViewById(R.id.edt_nome);
        qtdTotal = (EditText) findViewById(R.id.edt_qtd);
        qtdPorcao = (EditText) findViewById(R.id.edt_porcao);
        dtVal = (EditText) findViewById(R.id.edt_dt_validade);
        precoTotal = (EditText) findViewById(R.id.edt_total);
        precoPorcao = (EditText) findViewById(R.id.edt_valorporcao);

    }
    public void onClickVez(View view){
        //colocar todos no bd.
        nome.setText("");
        qtdTotal.setText("");
        qtdPorcao.setText("");
        dtVal.setText("");
        precoTotal.setText("");
        precoPorcao.setText("");
        vez++;
        tx.setText(""+vez);
    }
    public void onClickVoltar(View view){
        finish();
    }


}
