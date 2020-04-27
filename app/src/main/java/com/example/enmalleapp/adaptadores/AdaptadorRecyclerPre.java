package com.example.enmalleapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enmalleapp.R;
import com.example.enmalleapp.modelos.CreyentePre;
import java.util.ArrayList;

public class AdaptadorRecyclerPre extends RecyclerView.Adapter <AdaptadorRecyclerPre.ViewHolderDatos>{

    ArrayList<CreyentePre> listaDatosPre;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public AdaptadorRecyclerPre(ArrayList<CreyentePre> listaDatosPre) {
            this.listaDatosPre = listaDatosPre;
        }

    @NonNull
    @Override
    public AdaptadorRecyclerPre.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.model_pre_encuentro_recycler,null,false);
        return new ViewHolderDatos(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerPre.ViewHolderDatos holder, int position) {
        holder.idcreyentepre.setText(listaDatosPre.get(position).getIdCreyentePre());
        holder.nombrespre.setText(listaDatosPre.get(position).getNombresPre());
        holder.apellidospre.setText(listaDatosPre.get(position).getApellidosPre());
        holder.edadpre.setText(listaDatosPre.get(position).getEdadPre());
    }

    @Override
    public int getItemCount() {
        return listaDatosPre.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView idcreyentepre, nombrespre, apellidospre, edadpre;
        ImageView mimageDeletePre;

        public ViewHolderDatos(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            idcreyentepre = itemView.findViewById(R.id.tvMPEId);
            nombrespre = itemView.findViewById(R.id.tvMPENombres);
            apellidospre = itemView.findViewById(R.id.tvMPEApellidos);
            edadpre = itemView.findViewById(R.id.tvMPEEdad);
            mimageDeletePre = itemView.findViewById(R.id.ivMPEImage_delete);

            mimageDeletePre.setOnClickListener(new View.OnClickListener() {
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

