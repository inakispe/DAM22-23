package com.example.newpokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newpokedex.Datos.DetallePokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import retrofit2.http.PUT;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> {
    private List<DetallePokemon> detallePokemonList = new ArrayList<>();
    private final String URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, String s);
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_pokemon, parent, false);
        return new PokemonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        //En el OnBindSeAsigna
        String nombrePoke = detallePokemonList.get(position).getName();
        int numDex = position + 1;
        holder.nombrePokemon.setText(numDex + " " + nombrePoke);
        asignarImagen(holder, numDex);
    }

    private void asignarImagen(PokemonHolder holder, int numDex) {
        Glide.with(holder.itemView)
                .load((URL + (numDex) + ".png"))
                .into(holder.imagenPokemon);
    }

    @Override
    public int getItemCount() {
        return detallePokemonList.size();
    }

    public void setListaResults(List<DetallePokemon> listaResults) {
        this.detallePokemonList = listaResults;
        notifyDataSetChanged();
    }


    public class PokemonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombrePokemon;
        private TextView tipoPokemon;
        private ImageView imagenPokemon;

        public PokemonHolder(@NonNull View itemView) {
            super(itemView);
            nombrePokemon = itemView.findViewById(R.id.nombrePokemon);
            imagenPokemon = itemView.findViewById(R.id.sprite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v, detallePokemonList.get(getAdapterPosition()).getName());
            }
        }
    }
}
