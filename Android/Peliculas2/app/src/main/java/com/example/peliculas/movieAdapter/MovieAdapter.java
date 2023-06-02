package com.example.peliculas.movieAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.peliculas.R;
import com.example.peliculas.model.Movie;

import java.util.ArrayList;
import java.util.List;

//Incorporar dentro del RV

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //Declaramos dos variables objetos una que nos permitira cargar el listado de peliculas
    private List<Movie> movies= new ArrayList<>();
    //El activity main que muestra el RecyclerView
    private Context context;
    //Constructor para inicializar estos objetos

    public MovieAdapter() {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Aqui especificamos el xml
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Funcion de colocar en nuestros componentes xml lo del json
        holder.tv_titulo.setText(movies.get(position).getTitulo());
        //Este lo usabamos para imagenes
        Glide.with(context).load(movies.get(position).getPortada()).into(holder.iv_portada);
    }

    @Override
    public int getItemCount() {
        //Tamaño de elementos
        return movies.size();
    }
    //Aqui para setear
    public void setResults(List<Movie>movies){
        this.movies=movies;
    }
    //La funcion de la clase ViewHolder es especificar que componentes del item_movie
    //vamos a utilizar
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_portada;
        private TextView tv_titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada=itemView.findViewById(R.id.iv_portada);
            tv_titulo=itemView.findViewById(R.id.tv_titulo);
        }

        public ImageView getIv_portada() {
            return iv_portada;
        }

        public TextView getTv_titulo() {
            return tv_titulo;
        }
    }
}
