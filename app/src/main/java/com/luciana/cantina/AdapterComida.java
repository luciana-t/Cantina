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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AdapterComida extends RecyclerView.Adapter<AdapterComida.ViewHolder> {

    private List<comida> listItems;
    private Context context;
    static Boolean flag_par = false;
    static Boolean flag_impar = true;
    public DetailsAdapterListener onClickListener;
    static Boolean [] tomado;
    private int tamanhoLista;

    public AdapterComida(List<comida> listItems, Context context, DetailsAdapterListener listener) {
        this.listItems = listItems;
        this.context = context;
        this.onClickListener = listener;
        this.tamanhoLista=listItems.size();
        tomado= new Boolean[tamanhoLista];
        for(int i=0;i<tamanhoLista;i++){
            tomado[i]=false;
        }
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
       /* if(position%2!=0 && tomado[position]==false){
            if(flag_impar)
            {
                holder.card.setBackgroundResource(R.color.amareloClaro);
                flag_impar=false;
                tomado[position]=true;
            }else{
                flag_impar=true;
                tomado[position]=true;
            }
        }else if(flag_par && tomado[position]==false)
        {
            holder.card.setBackgroundResource(R.color.amareloClaro);
            flag_par=false;
            tomado[position]=true;
        }else{
            if(tomado[position]==false) {
                flag_par = true;
                tomado[position]=true;
            }
        }*/
        holder.fotocomida.setImageResource(listItem.getDrawableID());
        holder.quantidade.setText("" +listItem.getAmount());
        holder.NomeComida.setText(listItem.getNome()+" R$ "+ listItem.getPreco());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView fotocomida;
        public TextView NomeComida;
        public CardView card;
        public Button btmenos;
        public EditText quantidade;
        public Button btmais;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardComent);
            fotocomida = (ImageView) itemView.findViewById(R.id.imagemComida);
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
