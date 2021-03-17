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

public class NuevaTareaActivity extends AppCompatActivity {
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
        this.setTitle("Nueva Tarea");

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
                if (posicionActual.equals("Abierta")) {
                    ivEstadoAb.setVisibility(View.VISIBLE);
                    ivEstadoEC.setVisibility(View.INVISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals("En curso")) {
                    ivEstadoAb.setVisibility(View.INVISIBLE);
                    ivEstadoEC.setVisibility(View.VISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals("Terminada")) {
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
                String temp;


                textoTemp = (TextView) spCategoria.getSelectedView();
                temp = textoTemp.getText().toString();
                intent.putExtra("categoria", temp);

                textoTemp = (TextView) spPrioridad.getSelectedView();
                temp = textoTemp.getText().toString();
                intent.putExtra("prioridad", temp);

                textoTemp = (TextView) spEstado.getSelectedView();
                temp = textoTemp.getText().toString();
                intent.putExtra("estado", temp);

                intent.putExtra("tecnico", etTecnico.getText().toString());
                intent.putExtra("brevaDes", etBreveDes.getText().toString());
                intent.putExtra("Descripcion", etmDescripcion.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }


}