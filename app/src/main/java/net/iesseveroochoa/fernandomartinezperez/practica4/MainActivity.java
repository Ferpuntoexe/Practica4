package net.iesseveroochoa.fernandomartinezperez.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import net.iesseveroochoa.fernandomartinezperez.practica4.adapters.TareasAdapter;
import net.iesseveroochoa.fernandomartinezperez.practica4.model.Tarea;
import net.iesseveroochoa.fernandomartinezperez.practica4.model.TareaViewModel;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_TAREA = "Activity.tarea";
    public final static String EXTRA_INDICE = "Indice.tarea";
    private TareasAdapter tareasAdapter;
    private RecyclerView rvTareas;
    private FloatingActionButton fabAdd;
    private TareaViewModel tareasViewModel;
    int cuentaNotas = 1;
    public static final int OPTION_REQUEST_CREAR = 1;
    public static final int OPTION_REQUEST_EDITAR = 2;

    /**Este es el metodo que se llama cuando se inicia la actividad*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTareas = findViewById(R.id.rvTareas);
        fabAdd = findViewById(R.id.fabAñadir);
        tareasAdapter = new TareasAdapter();
        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        rvTareas.setAdapter(tareasAdapter);

        /**
         * Aqui se llama al View Model y actualiza el Recicler View
         */
        tareasViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        tareasViewModel.getListaTareas().observe(this, new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tarea) {
                tareasAdapter.setListaTareas(tarea);
            }
        });

        /**
         * Aqui se obserba si se ha pulsado sobre el icono Borrar
         */
        tareasAdapter.setOnItemClickBorrarListener(new TareasAdapter.OnItemClickBorrarListener() {
            @Override
            public void onItemClickBorrar(Tarea tarea) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);

                builder.setMessage(R.string.mensageBorrar + tarea.getId() + "?").setTitle(R.string.borrar)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                tareasViewModel.delTarea(tarea);
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();

            }
        });
        /**
         * Aqui se obserba si se ha pulsado sobre el icono Editar
         */
        tareasAdapter.setOnItemClickEditarListener(new TareasAdapter.OnItemClickEditarListener() {
            @Override
            public void onItemClickEditar(Tarea tarea) {
                Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
                intent.putExtra(EXTRA_TAREA, tarea);
                intent.putExtra(EXTRA_INDICE, tareasViewModel.tareaIndexOf(tarea));
                int codigoNuevaTarea = OPTION_REQUEST_EDITAR;
                startActivityForResult(intent, codigoNuevaTarea);
            }
        });

        /**
         * Aqui se obserba si se ha pulsado sobre el boton flotante de añadir
         */

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
                int codigoNuevaTarea = OPTION_REQUEST_CREAR;
                startActivityForResult(intent, codigoNuevaTarea);

            }
        });
        {


        }
    }

    /**
     * Esto genera el menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Aqui se obserba si se ha pulsado sobre el alguna de las obciones del menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        /**Aqui al pulsa acerca de llama a un cuadro de dialogo*/
        if (id == R.id.accion_AcercaDe) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            AcercaDe dialogo = new AcercaDe();
            dialogo.show(fragmentManager, "tagAlerta");

            return true;
            /**Aqui al pulsar Ordenar ordena las tareas*/
        } else if (id == R.id.accion_ordenar) {


            return false;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Aqui se recogen la tarea que se ha manipulado
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            /**En caso de que la tarea sea nuava aqui se recoge y se añade al RV*/
            if (requestCode == OPTION_REQUEST_CREAR) {


                Tarea tarea = data.getParcelableExtra(NuevaTareaActivity.EXTRA_TAREA);

                tareasViewModel.addTarea(tarea);

                /**en caso de que la tarea fuese una editada se sobreesciben los datos*/
            } else if (requestCode == OPTION_REQUEST_EDITAR) {

                Tarea tarea = data.getParcelableExtra(NuevaTareaActivity.EXTRA_TAREA);
                int indice = data.getIntExtra(EXTRA_INDICE, 0);
                tareasViewModel.setDatos(indice,
                        tarea.getPrioridad(),
                        tarea.getCategoria(),
                        tarea.getEstado(),
                        tarea.getTecnico(),
                        tarea.getResumen(),
                        tarea.getDescripcion());
            }
        }
    }

}