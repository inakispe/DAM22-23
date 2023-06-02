package com.example.pasarentreactividades;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> {

    private ArrayList<Producto> datos;

    public interface OnItemClickListener {
        void onItemClick(int position, int precio);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }



    public AdaptadorProductos(ArrayList<Producto> datos) {
        this.datos = datos;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView precio;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.nombreProducto);
            precio=itemView.findViewById(R.id.precioProducto);
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getPrecio() {
            return precio;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.examen_vh,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String nombreP=datos.get(position).getNombre();
        int precioP=datos.get(position).getPrecio();
        holder.getNombre().setText(nombreP);
        //Los int sino dan problemas, lo que no sea text
        holder.getPrecio().setText(String.valueOf(precioP));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.onItemClick(position,precioP);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datos.size();
    }

}
