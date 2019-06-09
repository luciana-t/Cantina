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
import android.widget.Toast;

public class CadastroEstoque extends Activity {
    EditText nome, dtVal, qtdTotal, qtdPorcao, precoTotal, precoPorcao;
    TextView tx;
    Button add;
    int vez;
    ArrayAdapter <String> adapter;
    Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estoque);
        vez = 1;
        tx = (TextView) findViewById(R.id.tv_vez);
        tx.setText(""+vez);

        spinner1 = (Spinner) findViewById(R.id.sp1);
        spinner2 = (Spinner) findViewById(R.id.sp2);
        String [] pesos = {"   Kg" , "    g", "    L", "   ml", "unidade(s)", "caixa(s)"};
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,pesos);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        nome = (EditText) findViewById(R.id.edt_nome);
        qtdTotal = (EditText) findViewById(R.id.edt_qtd);
        qtdPorcao = (EditText) findViewById(R.id.edt_porcao);
        dtVal = (EditText) findViewById(R.id.edt_dt_validade);
        precoTotal = (EditText) findViewById(R.id.edt_total);
        precoPorcao = (EditText) findViewById(R.id.edt_valorporcao);

    }
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public void onClickVez(View view){
        //colocar todos no bd.

        if(isEmpty(nome)) Toast.makeText(this,"Produto não inserido no estoque.", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, ""+ nome.getText().toString()+ " foi adicionado no estoque.", Toast.LENGTH_SHORT).show();
            vez++;
            tx.setText(""+vez);
        }
        //zera campos
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        nome.setText("");
        qtdTotal.setText("");
        qtdPorcao.setText("");
        dtVal.setText("");
        precoTotal.setText("");
        precoPorcao.setText("");
    }
    public void onClickVoltar(View view){
        int quant = vez -1;
        Toast.makeText(this,""+ quant + " produtos foram adicionados no estoque.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int quant = vez -1;
        Toast.makeText(this,""+ quant + " produtos foram adicionados no estoque.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
