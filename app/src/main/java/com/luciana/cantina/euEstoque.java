package com.luciana.cantina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class euEstoque extends AppCompatActivity {
    ArrayList<String[]> listaitem;
    databaseHelper crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eu_estoque);

        //Inicializa o banco
        crud = databaseHelper.getInstance();

        //Adquire o linear layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_euEstoque);

        //Adquire os estoques
        listaitem = crud.getEstoque();

        int i=0;
        for(String[] s: listaitem){
            TextView text = new TextView(this);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            text.setText(s[0]);

            layout.addView(text);
        }
    }
}
