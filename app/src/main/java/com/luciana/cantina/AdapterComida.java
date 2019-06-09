package com.luciana.cantina;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterComida extends RecyclerView.Adapter<AdapterComida.ViewHolder> {

    private List<comida> listItems;
    private Context context;

    public AdapterComida(List<comida> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_food, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        comida listItem = listItems.get(position);
        if(position%2!=0){
            holder.card.setBackgroundResource(R.color.amareloClaro);
        }

        holder.NomeComida.setText(listItem.getNome());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView NomeComida;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardComent);
            NomeComida = (TextView) itemView.findViewById(R.id.nomeComida);
        }
    }
}
