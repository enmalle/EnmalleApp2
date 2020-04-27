package com.example.enmalleapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enmalleapp.R;
import com.example.enmalleapp.modelos.CreyenteEscuela;
import java.util.ArrayList;

public class AdaptadorRecyclerEscuela extends RecyclerView.Adapter<AdaptadorRecyclerEscuela.ViewHolderDatos> {


    ArrayList<CreyenteEscuela> listaDatosEscuela;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdaptadorRecyclerEscuela(ArrayList<CreyenteEscuela> listaDatosEscuela) {
        this.listaDatosEscuela = listaDatosEscuela;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerEscuela.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.model_escuela_recycler,null,false);
        return new ViewHolderDatos(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerEscuela.ViewHolderDatos holder, int position) {
        holder.nombresescuela.setText(listaDatosEscuela.get(position).getNombresEscuela());
        holder.apellidosescuela.setText(listaDatosEscuela.get(position).getApellidosEscuela());
        holder.edadescuela.setText(listaDatosEscuela.get(position).getEdadEscuela());
    }

    @Override
    public int getItemCount() {
        return listaDatosEscuela.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView idcreyenteescuela, nombresescuela, apellidosescuela, edadescuela;
        ImageView mimageDeleteEscuela;

        public ViewHolderDatos(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            nombresescuela = itemView.findViewById(R.id.tvMEsNombres);
            apellidosescuela = itemView.findViewById(R.id.tvMEsApellidos);
            edadescuela = itemView.findViewById(R.id.tvMEsEdad);
            idcreyenteescuela = itemView.findViewById(R.id.tvMEsId);
            mimageDeleteEscuela = itemView.findViewById(R.id.ivMEsImage_delete);

            mimageDeleteEscuela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }


}


