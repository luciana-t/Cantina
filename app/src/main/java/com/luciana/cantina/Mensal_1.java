package com.luciana.cantina;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Mensal_1 extends Fragment {
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<compra> listaitem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mensal_1, container, false);

        listaitem = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaitem.add(
                new compra("11/06/2019", "R$ 3,75")
        );
        listaitem.add(
                new compra("10/06/2019", "R$ 2,50")
        );
        listaitem.add(
                new compra("09/06/2019", "R$ 1,50")
        );
        listaitem.add(
                new compra("08/06/2019", "R$ 4,50")
        );
        listaitem.add(
                new compra("07/06/2019", "R$ 22,00")
        );
        listaitem.add(
                new compra("06/06/2019", "R$ 11,00")
        );
        listaitem.add(
                new compra("05/06/2019", "R$ 5,00")
        );
        listaitem.add(
                new compra("04/06/2019", "R$ 7,00")
        );
        listaitem.add(
                new compra("03/06/2019", "R$ 8,50")
        );
        adapter = new MyAdapter(listaitem, getActivity());
        recyclerView.setAdapter(adapter);

        //return inflater.inflate(R.layout.fragment_mensal_1, container, false);
        return rootView;
    }
    public static Mensal_1 newInstance() {
        return new Mensal_1();
    }
}
