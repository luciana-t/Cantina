package com.luciana.cantina;
import java.util.Random;

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

    public float ramdomPrice(){
        Random preco = new Random();
        return preco.nextInt(10);
    }

    public void menos(int position) {
        int aux = listaitem.get(position).getAmount();
        if (aux == 0){
        }else{
            aux--;
            listaitem.get(position).setAmount(aux);
            this.adapter.notifyItemChanged(position);
        }
    }

    public void mais(int position) {
        int aux = listaitem.get(position).getAmount();
            aux++;
            listaitem.get(position).setAmount(aux);
            this.adapter.notifyItemChanged(position);
    }

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
                new comida("burrito", ramdomPrice())
        );
        listaitem.add(
                new comida("cafe", ramdomPrice())
        );
        listaitem.add(
                new comida("bixcoito", ramdomPrice())
        );
        listaitem.add(
                new comida("bulacha", ramdomPrice())
        );
        listaitem.add(
                new comida("leite", ramdomPrice())
        );
        listaitem.add(
                new comida("banana", ramdomPrice())
        );
        listaitem.add(
                new comida("maca", ramdomPrice())
        );
        listaitem.add(
                new comida("saladaMista", ramdomPrice())
        );
        listaitem.add(
                new comida("pera", ramdomPrice())
        );
        listaitem.add(
                new comida("alho", ramdomPrice())
        );
        listaitem.add(
                new comida("n sei", ramdomPrice())
        );
        listaitem.add(
                new comida("criatividade 0", ramdomPrice())
        );

        adapter = new AdapterComida(listaitem, getActivity(), new AdapterComida.DetailsAdapterListener() {
            @Override
            public void menosOnClick(View v, int position) {
                menos(position);
            }

            @Override
            public void maisOnClick(View v, int position) {
                mais(position);
            }
        });
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    public static Checkin_2 newInstance() {
        return new Checkin_2();
    }
}
