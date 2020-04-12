package com.example.enmalleapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enmalleapp.R;
import com.example.enmalleapp.modelos.Creyente;
import java.util.ArrayList;

public class AdaptadorRecyclerCelula extends RecyclerView.Adapter <AdaptadorRecyclerCelula.ViewHolderDatos> {

    ArrayList<Creyente> listaDatos;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public AdaptadorRecyclerCelula(ArrayList<Creyente> listaDatos) {
        this.listaDatos = listaDatos;
    }
    @NonNull
    @Override
    public AdaptadorRecyclerCelula.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.model_celula_recycler,null,false);
        return new ViewHolderDatos(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerCelula.ViewHolderDatos holder, int position) {
        holder.nombreapellido.setText(listaDatos.get(position).getNombreApellido());
        holder.fecha.setText(listaDatos.get(position).getFechaNacimiento());
        holder.idcreyente.setText(listaDatos.get(position).getidCreyente());
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombreapellido , fecha, idcreyente;
        ImageView mDeleteImage ;

        public ViewHolderDatos(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            nombreapellido = itemView.findViewById(R.id.rvRVMNombre);
            fecha = itemView.findViewById(R.id.rvRVMFechaNacimiento);
            idcreyente = itemView.findViewById(R.id.rvRVMId);
            mDeleteImage = itemView.findViewById(R.id.ivMCRImage_delete);

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
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
