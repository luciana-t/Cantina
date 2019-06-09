package com.luciana.cantina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroEstoque extends AppCompatActivity {
    TextView tx;
    int vez = 1;
    EditText nome, dtVal, qtdTotal, qtdPorcao, precoTotal, precoPorcao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vez = 1;
        tx = (TextView) findViewById(R.id.tv_vez);
        //tx.setText(""+vez);

        nome = (EditText) findViewById(R.id.edt_nome);
        qtdTotal = (EditText) findViewById(R.id.edt_qtd);
        qtdPorcao = (EditText) findViewById(R.id.edt_porcao);
        dtVal = (EditText) findViewById(R.id.edt_dt_validade);
        precoTotal = (EditText) findViewById(R.id.edt_total);
        precoPorcao = (EditText) findViewById(R.id.edt_valorporcao);

        setContentView(R.layout.activity_cadastro_estoque);
    }
    public void onClickVez(View view){
        Intent it = new Intent(getBaseContext(), CadastroEstoque.class);
//        vez++;
//        it.putExtra("vez", vez);
//        nome.setText(" ");
//        qtdPorcao.setText(" ");
//        qtdTotal.setText("");
//        dtVal.setText("");
//        precoPorcao.setText("");
//        precoTotal.setText("");
        startActivity(it);
    }
    public void onClickVoltar(View view){
        finish();
    }
}
