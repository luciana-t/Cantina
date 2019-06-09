package com.luciana.cantina;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<compra> listItems;
    private Context context;

    public MyAdapter(List<compra> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardCompra, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        compra listItem = listItems.get(position);
        if(position%2!=0){
            holder.card.setBackgroundResource(R.color.amareloClaro);
        }

        holder.DataCompra.setText(listItem.getDataCompra());
        holder.price.setText(listItem.getPreco());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView DataCompra;
        public TextView price;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardComent);
            DataCompra = (TextView) itemView.findViewById(R.id.dataCompra);
            price =(TextView)itemView.findViewById(R.id.precoCompra);
        }
    }
}