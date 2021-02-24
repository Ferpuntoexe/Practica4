package net.iesseveroochoa.fernandomartinezperez.practica4.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.iesseveroochoa.fernandomartinezperez.practica4.R;

import java.util.List;

import net.iesseveroochoa.fernandomartinezperez.practica4.model.Tarea;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<Tarea> listamisTareas;


    public void setListaTareas(List<Tarea> tareas) {
        listamisTareas = tareas;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        if (listamisTareas != null) {
            final Tarea tarea = listamisTareas.get(position);

            holder.tvResumen.setText(tarea.getId() + "-" + tarea.getResumen());
            holder.tvTecnico.setText(tarea.getId() + "-" + tarea.getTecnico());
            switch (tarea.getEstado()) {
                case "Abierta":
                    holder.ivEstado.setImageResource(R.mipmap.ic_abierta);
                    break;
                case "En curso":
                    holder.ivEstado.setImageResource(R.mipmap.ic_en_curso);
                    break;
                case "Terminada":
                    holder.ivEstado.setImageResource(R.mipmap.ic_terminada);
                    break;
            }
            if (tarea.getPrioridad().equals("alta")) {
                holder.clItem.setBackgroundResource(R.color.prusia);
            } else {
                holder.clItem.setBackgroundColor(Color.TRANSPARENT);

            }
        }
    }

    @Override
    public int getItemCount() {
        return listamisTareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvResumen;
        private TextView tvTecnico;
        private ImageView ivEstado;
        private ImageView ivEditar;
        private ImageView ivBorrar;
        private ConstraintLayout clItem;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvTecnico = itemView.findViewById(R.id.tvTecnico);
            ivEstado = itemView.findViewById(R.id.ivEstado);
            ivEditar = itemView.findViewById(R.id.ivEditar);
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            clItem = itemView.findViewById(R.id.clItem);

        }
    }
}
