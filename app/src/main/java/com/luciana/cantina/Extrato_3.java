package com.luciana.cantina;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Extrato_3 extends Fragment {
    RecyclerView recyclerView;
    AdapterCompra adapter;
    List<compra> listaitem;
    public float ramdomPrice(){
        Random preco = new Random();
        return preco.nextInt(10);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_extrato_3, container, false);

        listaitem = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaitem.add(
                new compra("05/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("05/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("05/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("04/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("04/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("03/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("03/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("03/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("02/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("02/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("01/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("01/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("01/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("01/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("30/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("29/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("02/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("02/07/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("11/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("10/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("09/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("08/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("07/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("06/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("05/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("04/06/2019", ramdomPrice()+"")
        );
        listaitem.add(
                new compra("03/06/2019", ramdomPrice()+"")
        );
        adapter = new AdapterCompra(listaitem, getActivity());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    public static Extrato_3 newInstance() {
        return new Extrato_3();
    }
}
