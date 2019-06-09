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


public class Mensal_1 extends Fragment {
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<item> listaitem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mensal_1, container, false);

        listaitem = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaitem.add(
                new item("gabriel", "eh um cara legal")
        );
        listaitem.add(
                new item("lu", "eh uma muie legal")
        );
        listaitem.add(
                new item("socrates", "eh um cara legal")
        );
        listaitem.add(
                new item("gabriel", "eh um cara legal")
        );
        listaitem.add(
                new item("lu", "eh uma muie legal")
        );
        listaitem.add(
                new item("socrates", "eh um cara legal")
        );
        listaitem.add(
                new item("gabriel", "eh um cara legal")
        );
        listaitem.add(
                new item("lu", "eh uma muie legal")
        );
        listaitem.add(
                new item("socrates", "eh um cara legal")
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
