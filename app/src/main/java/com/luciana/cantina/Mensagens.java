package com.luciana.cantina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Mensagens extends AppCompatActivity {

    EditText et;
    RadioGroup radioGroup;
    int radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);
        et = (EditText) findViewById(R.id.et_mensagem);
        radio = 1;
        radioGroup = (RadioGroup) findViewById(R.id.radiogrup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_meunome) {
                    radio = 1; // tem nome
                    //colocar anonimo no bd
                }
                else{
                    radio = 0;
                    //colocar meu nome no bd
                }
            }
        });
    }

    public void onClickVoltarMensagem(View view){
        finish();
    }

    public void onCLickEnviar(View view){
        //salvar mensagem no bd
        if (radio == 1){
            Toast.makeText(this,"Sua sugestão foi enviada!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this,"Sua sugestão foi enviada anonimamente!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
