package com.luciana.cantina;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class CadastroProdutoTab extends TabActivity implements TabHost.OnTabChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa o tabHost
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);

        // Cria Aba 1
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Tab 1");
        tab1.setIndicator("Produto");
        tab1.setContent(new Intent(this, CadastroProduto.class));
        tabHost.addTab(tab1);

        // Cria Aba 2
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Tab 2");
        tab2.setIndicator("Porção");
        tab2.setContent(new Intent(this, CadastroPorcao.class));
        tabHost.addTab(tab2);

        // Cria Aba 3
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Tab 3");
        tab3.setIndicator("Estoque");
        tab3.setContent(new Intent(this, CadastroEstoque.class));
        tabHost.addTab(tab3);

        // Seleciona a primeira "tab"
        tabHost.setCurrentTab(0);
    }
    //Ação que acontece quando muda de Aba
    public void onTabChanged(String tabId) {
        Log.i("Trocas", "Trocou aba: " + tabId);
    }

}
