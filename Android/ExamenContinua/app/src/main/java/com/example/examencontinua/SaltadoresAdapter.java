package com.example.examencontinua;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examencontinua.Datos.SaltadoresResponse;

import java.util.ArrayList;
import java.util.List;

public class SaltadoresAdapter extends RecyclerView.Adapter<SaltadoresAdapter.SaltadoresViewHolder> {
    private List<SaltadoresResponse> data= new ArrayList<>();

    @NonNull
    @Override
    public SaltadoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_saltadores, parent, false);
        return new SaltadoresViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SaltadoresViewHolder holder, int position) {
            holder.getNombre().setText(data.get(position).getNombre());
            holder.getLocalidad().setText(data.get(position).getLocalidad());
            holder.getFecha().setText(data.get(position).getFecha());
            holder.getCentimetros().setText(data.get(position).getCentimetros());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setResults(List<SaltadoresResponse> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public class SaltadoresViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView localidad;
        private TextView fecha;
        private TextView centimetros;

        public TextView getNombre() {
            return nombre;
        }

        public TextView getLocalidad() {
            return localidad;
        }

        public TextView getFecha() {
            return fecha;
        }

        public TextView getCentimetros() {
            return centimetros;
        }

        public SaltadoresViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.nombre);
            localidad=itemView.findViewById(R.id.localidad);
            fecha=itemView.findViewById(R.id.fecha);
            centimetros=itemView.findViewById(R.id.centimetros);

        }
    }
}
