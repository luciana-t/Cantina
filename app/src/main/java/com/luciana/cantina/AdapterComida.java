package com.luciana.cantina;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class AdapterComida extends RecyclerView.Adapter<AdapterComida.ViewHolder> {

    private List<comida> listItems;
    private Context context;
    static Boolean flag_par = false;
    static Boolean flag_impar = true;
    public DetailsAdapterListener onClickListener;

    public AdapterComida(List<comida> listItems, Context context, DetailsAdapterListener listener) {
        this.listItems = listItems;
        this.context = context;
        this.onClickListener = listener;
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
            if(flag_impar)
            {
                holder.card.setBackgroundResource(R.color.amareloClaro);
                flag_impar=false;
            }else{
                flag_impar=true;
            }
        }else if(flag_par)
        {
            Log.d("capture1", "onBindViewHolder: "+position+" "+flag_par);
            holder.card.setBackgroundResource(R.color.amareloClaro);
            flag_par=false;
        }else{
            flag_par=true;
        }
        holder.quantidade.setText("" +listItem.getAmount());
        holder.NomeComida.setText(listItem.getNome());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView NomeComida;
        public CardView card;
        public Button btmenos;
        public EditText quantidade;
        public Button btmais;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardComent);
            NomeComida = (TextView) itemView.findViewById(R.id.nomeComida);
            btmenos = (Button) itemView.findViewById(R.id.bt_sub_card);
            quantidade = (EditText) itemView.findViewById(R.id.qtd);
            btmais = (Button) itemView.findViewById(R.id.bt_add_card);

            btmenos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.menosOnClick(v, getAdapterPosition());
                }
            });
            btmais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.maisOnClick(v, getAdapterPosition());
                }
            });

        }
    }

    public interface DetailsAdapterListener {

        void menosOnClick(View v, int position);

        void maisOnClick(View v, int position);
    }
}
