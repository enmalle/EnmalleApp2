package com.example.enmalleapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.enmalleapp.R;
import com.example.enmalleapp.modelos.CreyentePos;
import java.util.ArrayList;

public class AdaptadorRecyclerPos extends RecyclerView.Adapter <AdaptadorRecyclerPos.ViewHolderDatos> {

    ArrayList<CreyentePos> listaDatosPos;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdaptadorRecyclerPos(ArrayList<CreyentePos> listaDatosPos) {
        this.listaDatosPos = listaDatosPos;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerPos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.model_pos_encuentro_recycler,null,false);
        return new ViewHolderDatos(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerPos.ViewHolderDatos holder, int position) {
        holder.nombrespos.setText(listaDatosPos.get(position).getNombresPos());
        holder.apellidospos.setText(listaDatosPos.get(position).getApellidosPos());
        holder.edadpos.setText(listaDatosPos.get(position).getEdadPos());
    }

    @Override
    public int getItemCount() {
        return listaDatosPos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView idcreyentepos, nombrespos, apellidospos, edadpos;
        ImageView mimageDeletePos;

        public ViewHolderDatos(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            nombrespos = itemView.findViewById(R.id.tvMPosNombres);
            apellidospos = itemView.findViewById(R.id.tvMPosApellidos);
            edadpos = itemView.findViewById(R.id.tvMPosEdad);
            idcreyentepos = itemView.findViewById(R.id.tvMPosId);
            mimageDeletePos = itemView.findViewById(R.id.ivMPosImage_delete);

            mimageDeletePos.setOnClickListener(new View.OnClickListener() {
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
