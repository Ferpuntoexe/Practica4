package net.iesseveroochoa.fernandomartinezperez.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
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
        this.setTitle(getString(R.string.nuevaT));

        Intent intent = new Intent();

        ivEstadoAb = (ImageView) findViewById(R.id.ivEstadoAb);
        ivEstadoEC = (ImageView) findViewById(R.id.ivEstadoEC);
        ivEstadoTe = (ImageView) findViewById(R.id.ivEstadoTe);
        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spPrioridad = (Spinner) findViewById(R.id.spPrioridad);
        spEstado = (Spinner) findViewById(R.id.spEstado);
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
        etTecnico = (EditText) findViewById(R.id.etTecnico);
        etBreveDes = (EditText) findViewById(R.id.etBreveDes);
        etmDescripcion = (EditText) findViewById(R.id.etmDescripcion);
        fabGuardar = (FloatingActionButton) findViewById(R.id.fabGuardar);

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textoTemp;

                Tarea tarea;


                textoTemp = (TextView) spCategoria.getSelectedView();
                String temp = textoTemp.getText().toString();


                textoTemp = (TextView) spPrioridad.getSelectedView();
                String temp2 = textoTemp.getText().toString();


                textoTemp = (TextView) spEstado.getSelectedView();
                String temp3 = textoTemp.getText().toString();


                tarea = new Tarea(temp2, temp, temp3, etTecnico.getText().toString(), etmDescripcion.getText().toString(), etBreveDes.getText().toString());
                intent.putExtra(EXTRA_TAREA, tarea);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }


}