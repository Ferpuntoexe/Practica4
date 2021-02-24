package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TareaViewModel extends AndroidViewModel {


private MutableLiveData<List<Tarea>> listaTareasLiveData;

    private List<Tarea> listaTareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);

        listaTareasLiveData = new MutableLiveData<List<Tarea>>();
        crearDatos();
        listaTareasLiveData.setValue(listaTareas);

    }



    public LiveData<List<Tarea>> getListaTareas() {
        return listaTareasLiveData;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public void addTarea(Tarea tarea) {
        int i = listaTareas.indexOf(tarea);
        if (i < 0)
            listaTareas.add(tarea);
        else {
            listaTareas.remove(i);
            listaTareas.add(i, tarea);
        }
    }

    public void delTarea(Tarea tarea) {
        if (listaTareas.size() > 0) {
            listaTareas.remove(tarea);
        }
    }

    public int getItemCount(){
        if (listaTareas!= null){
            return listaTareas.size();
        } else {
            return 0;
        }
    }

    private void crearDatos() {
        listaTareas=new ArrayList<Tarea>();
        Tarea tarea=new Tarea("Alta","Mantenimiento","Abierta","Juan " +
                "Perez","Actualización de antivirus","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
                listaTareas.add(tarea);
        tarea=new Tarea("Media","Mantenimiento","Terminada","Maria " +
                "Perez","Actualización de S.O.Linux","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
                listaTareas.add(tarea);
        tarea=new Tarea("Baja","Reparación","En curso","Maria " +
                "Lopez","Sustitución de ratones","Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium " +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed " +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra. " +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu " +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat " +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
                listaTareas.add(tarea);
        tarea=new Tarea("Media","Comercial","Abierta","Fele " +
                "Martinez","Presentar presupuesto Web","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "9 " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
                listaTareas.add(tarea);
        tarea=new Tarea("Media","Comercial","Abierta","Fele " +
                "Martinez","Presentar presupuesto Web","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
                listaTareas.add(tarea);
    }

    public void setDatos(Tarea tarea, String prioridad, String categoria, String estado, String tecnico, String descripcion, String resumen) {
        int i = listaTareas.indexOf(tarea);

        if (i > 0) {
            listaTareas.get(i).setPrioridad(prioridad);
            listaTareas.get(i).setEstado(estado);
            listaTareas.get(i).setTecnico(tecnico);
            listaTareas.get(i).setDescripcion(descripcion);
            listaTareas.get(i).setResumen(resumen);
        }
    }
}
