package com.example.enmalleapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enmalleapp.R;
import com.example.enmalleapp.modelos.CreyenteEncuentro;
import java.util.ArrayList;

public class AdaptadorRecyclerEncuentro extends RecyclerView.Adapter<AdaptadorRecyclerEncuentro.ViewHolderDatos>{

    ArrayList<CreyenteEncuentro> listaDatosEncuentro;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdaptadorRecyclerEncuentro(ArrayList<CreyenteEncuentro> listaDatosEncuentro) {
        this.listaDatosEncuentro = listaDatosEncuentro;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerEncuentro.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.model_encuentro_recycler,null,false);
        return new ViewHolderDatos(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerEncuentro.ViewHolderDatos holder, int position) {
        holder.nombresencuentro.setText(listaDatosEncuentro.get(position).getNombresEncuentro());
        holder.apellidosecuentro.setText(listaDatosEncuentro.get(position).getApellidosEncuentro());
        holder.edadencuentro.setText(listaDatosEncuentro.get(position).getEdadEncuentro());
    }

    @Override
    public int getItemCount() {
        return listaDatosEncuentro.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView idcreyenteencuentro, nombresencuentro, apellidosecuentro, edadencuentro;
        ImageView mimageDeleteEncuentro;

        public ViewHolderDatos(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            nombresencuentro = itemView.findViewById(R.id.tvMENombres);
            apellidosecuentro = itemView.findViewById(R.id.tvMEApellidos);
            edadencuentro = itemView.findViewById(R.id.tvMEEdad);
            idcreyenteencuentro = itemView.findViewById(R.id.tvMEId);
            mimageDeleteEncuentro = itemView.findViewById(R.id.ivMEImage_delete);

            mimageDeleteEncuentro.setOnClickListener(new View.OnClickListener() {
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
