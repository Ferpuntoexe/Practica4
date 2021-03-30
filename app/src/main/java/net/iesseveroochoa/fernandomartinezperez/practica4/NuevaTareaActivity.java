package net.iesseveroochoa.fernandomartinezperez.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iesseveroochoa.fernandomartinezperez.practica4.model.Tarea;

public class NuevaTareaActivity extends AppCompatActivity {
    public final static String EXTRA_TAREA = "Activity.tarea";
    public final static String EXTRA_INDICE = "Indice.tarea";

    private Spinner spCategoria;
    private Spinner spPrioridad;
    private Spinner spEstado;
    private EditText etTecnico;
    private EditText etBreveDes;
    private EditText etmDescripcion;
    private FloatingActionButton fabGuardar;
    private ImageView ivEstadoAb;
    private ImageView ivEstadoEC;
    private ImageView ivEstadoTe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        Intent intent = new Intent();
        int indice = getIntent().getIntExtra(EXTRA_INDICE, 0);

        Tarea tareaAnt = getIntent().getParcelableExtra(EXTRA_TAREA);


        ivEstadoAb = findViewById(R.id.ivEstadoAb);
        ivEstadoEC = findViewById(R.id.ivEstadoEC);
        ivEstadoTe = findViewById(R.id.ivEstadoTe);

        spCategoria = findViewById(R.id.spCategoria);
        spPrioridad = findViewById(R.id.spPrioridad);
        spEstado = findViewById(R.id.spEstado);
        etTecnico = findViewById(R.id.etTecnico);
        etBreveDes = findViewById(R.id.etResumen);
        etmDescripcion = findViewById(R.id.etmDescripcion);
        fabGuardar = findViewById(R.id.fabGuardar);

        if (tareaAnt != null) {
            this.setTitle(getString(R.string.tarean) + tareaAnt.getId());


            spCategoria.setSelection(getIndex(spCategoria, tareaAnt.getCategoria()));
            spPrioridad.setSelection(getIndex(spPrioridad, tareaAnt.getPrioridad()));
            spEstado.setSelection(getIndex(spEstado, tareaAnt.getEstado()));

            etTecnico.setText(tareaAnt.getTecnico());
            etBreveDes.setText(tareaAnt.getResumen());
            etmDescripcion.setText(tareaAnt.getDescripcion());
        } else {
            this.setTitle(getString(R.string.nuevaT));
        }


        spEstado.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String posicionActual = parent.getItemAtPosition(position).toString();
                if (posicionActual.equals(getString(R.string.abierta))) {
                    ivEstadoAb.setVisibility(View.VISIBLE);
                    ivEstadoEC.setVisibility(View.INVISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals(getString(R.string.enCurso))) {
                    ivEstadoAb.setVisibility(View.INVISIBLE);
                    ivEstadoEC.setVisibility(View.VISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals(getString(R.string.terminada))) {
                    ivEstadoAb.setVisibility(View.INVISIBLE);
                    ivEstadoEC.setVisibility(View.INVISIBLE);
                    ivEstadoTe.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textoTemp;

                Tarea tarea;


                textoTemp = (TextView) spCategoria.getSelectedView();
                String categoria = textoTemp.getText().toString();


                textoTemp = (TextView) spPrioridad.getSelectedView();
                String prioridad = textoTemp.getText().toString();


                textoTemp = (TextView) spEstado.getSelectedView();
                String estado = textoTemp.getText().toString();


                tarea = new Tarea(prioridad, categoria, estado, etTecnico.getText().toString(), etBreveDes.getText().toString(), etmDescripcion.getText().toString());
                intent.putExtra(EXTRA_INDICE, indice);
                intent.putExtra(EXTRA_TAREA, tarea);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }

    private int getIndex(Spinner spinner, String valor) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(valor)) {
                return i;
            }
        }
        return 0;
    }

}