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

    private List<item> listItems;
    private Context context;

    public MyAdapter(List<item> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_comentario, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        item listItem = listItems.get(position);
        if(position%2!=0){
            holder.card.setBackgroundResource(R.color.amareloClaro);
        }

        holder.infoComentCard.setText(listItem.getNomeDisciplina());
        holder.txtcomentCard.setText(listItem.getComentario());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView infoComentCard;
        public TextView txtcomentCard;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardComent);
            infoComentCard = (TextView) itemView.findViewById(R.id.infoComentCard);
            txtcomentCard =(TextView)itemView.findViewById(R.id.txtcomentCard);
        }
    }
}