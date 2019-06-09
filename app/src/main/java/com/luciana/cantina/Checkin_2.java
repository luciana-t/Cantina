package com.luciana.cantina;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Checkin_2 extends Fragment {

    RecyclerView recyclerView;
    AdapterComida adapter;
    List<comida> listaitem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_checkin_2, container, false);

        listaitem = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        listaitem.add(
                new comida("burrito")
        );
        listaitem.add(
                new comida("cafe")
        );
        listaitem.add(
                new comida("bixcoito")
        );
        listaitem.add(
                new comida("bulacha")
        );
        listaitem.add(
                new comida("leite")
        );
        listaitem.add(
                new comida("banana")
        );
        listaitem.add(
                new comida("maca")
        );
        listaitem.add(
                new comida("saladaMista")
        );
        listaitem.add(
                new comida("pera")
        );
        listaitem.add(
                new comida("alho")
        );
        listaitem.add(
                new comida("n sei")
        );
        listaitem.add(
                new comida("criatividade 0")
        );
        adapter = new AdapterComida(listaitem, getActivity());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    public static Checkin_2 newInstance() {
        return new Checkin_2();
    }
}
