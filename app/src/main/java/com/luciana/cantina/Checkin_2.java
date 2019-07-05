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
                new comida("Taco", ramdomPrice(), R.drawable.taco)
        );
        listaitem.add(
                new comida("Cafe", ramdomPrice(), R.drawable.coffee)
        );
        listaitem.add(
                new comida("Biscoito", ramdomPrice(),R.drawable.cookies)
        );
        listaitem.add(
                new comida("Pizza", ramdomPrice(), R.drawable.pizza)
        );
        listaitem.add(
                new comida("Leite", ramdomPrice(),R.drawable.milk)
        );
        listaitem.add(
                new comida("Banana", ramdomPrice(),R.drawable.banana)
        );
        listaitem.add(
                new comida("Maca", ramdomPrice(),R.drawable.apple)
        );
        listaitem.add(
                new comida("saladaMista", ramdomPrice(),R.drawable.salad)
        );
        listaitem.add(
                new comida("bolo", ramdomPrice(),R.drawable.cake)
        );
        listaitem.add(
                new comida("Cebola", ramdomPrice(),R.drawable.onion)
        );
        listaitem.add(
                new comida("cenoura", ramdomPrice(),R.drawable.carrot)
        );
        listaitem.add(
                new comida("Abacate", ramdomPrice(), R.drawable.avocado)
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
