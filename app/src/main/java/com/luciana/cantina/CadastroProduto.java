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

public class CadastroProduto extends Activity {

    //Views
    TextView produtoTxt;
    int cadastrados;
    EditText nome, codBarra, qtPorcao, valorPorcao;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    String spinnerValue;

    //Variavel do banco de dados
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        //Adquire as instancias dos spinners
        spinner = (Spinner) findViewById(R.id.spinner_cadastro_porcao);
        String [] pesos = {"Kg" , "g", "L", "ml", "unidade(s)", "caixa(s)"};
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,pesos);
        spinner.setAdapter(adapter);

        //Adquire as instancias das views de aquisicao de dados
        nome = (EditText) findViewById(R.id.cadastro_produto_nome);
        codBarra = (EditText) findViewById(R.id.cadastro_produto_codBarra);
        qtPorcao = (EditText) findViewById(R.id.cadastro_produto_porcao);
        valorPorcao = (EditText) findViewById(R.id.cadastro_produto_valor_porcao);

        //Seta o produto como o primeiro cadastrado
        cadastrados = 1;
        produtoTxt = (TextView) findViewById(R.id.produtoTxt);
        produtoTxt.setText(produtoTxt.getText().toString() + cadastrados);

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

    public void cadastrarProduto(View view){

        //Verifica se algum campo esta vazio
        if(isEmpty(nome) | isEmpty(codBarra)) {
            Log.i("Cadastro_Produto", "Campo produto não preenchido");
            Toast.makeText(this,"Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        }else if(isEmpty(qtPorcao) | isEmpty(valorPorcao)){
            Log.i("Cadastro_Produto", "Campo porção não preenchido");
            Toast.makeText(this,"Deve-se cadastrar ao menos um tipo porção relativa ao produto", Toast.LENGTH_SHORT).show();
        }else{

            //Cadastra o produto
            String result_produto, result_porcao;
            result_produto = crud.cadastrarProduto(codBarra.getText().toString(), nome.getText().toString());
            if(result_produto != "Produto cadastrado")
                Toast.makeText(this, result_produto, Toast.LENGTH_SHORT).show();
            else{

                //Cadastra a porcao
                spinnerValue = spinner.getSelectedItem().toString();
                result_porcao = crud.addPorcao(codBarra.getText().toString(), valorPorcao.getText().toString(), spinnerValue, qtPorcao.getText().toString());

                //Avisa o usuario o resultado do cadastro
                Toast.makeText(this, result_produto, Toast.LENGTH_SHORT).show();
                if(result_porcao != "Porção cadastrada com sucesso"){
                    //Se deu errado, entao deleta o produto cadastrado
                    crud.deleteProduto(codBarra.getText().toString());
                }
            }
        }
        //Adiciona mais um ao contador de produtos cadastrados juntos
        cadastrados++;
        produtoTxt = (TextView) findViewById(R.id.produtoTxt);
        produtoTxt.setText(produtoTxt.getText().toString() + cadastrados);
    }
}
