package net.iesseveroochoa.fernandomartinezperez.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NuevaTareaActivity extends AppCompatActivity {
    private Spinner spCategoria;
    private Spinner spPrioridad;
    private Spinner spEstado;
    private EditText etTecnico;
    private EditText etBreveDes;
    private EditText etmDescripcion;
    private FloatingActionButton fabGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        Intent intent = new Intent();

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spPrioridad = (Spinner) findViewById(R.id.spPrioridad);
        spEstado = (Spinner) findViewById(R.id.spEstado);
        etTecnico = (EditText) findViewById(R.id.etTecnico);
        etBreveDes = (EditText) findViewById(R.id.etBreveDes);
        etmDescripcion = (EditText) findViewById(R.id.etmDescripcion);
        fabGuardar = (FloatingActionButton) findViewById(R.id.fabGuardar);

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("categoria", spCategoria.getItemIdAtPosition(spCategoria.getSelectedItemPosition()));
                intent.putExtra("prioridad", spPrioridad.getItemIdAtPosition(spCategoria.getSelectedItemPosition()));
                intent.putExtra("estado", spEstado.getItemIdAtPosition(spCategoria.getSelectedItemPosition()));
                intent.putExtra("tecnico", etTecnico.getText().toString());
                intent.putExtra("brevaDes", etBreveDes.getText().toString());
                intent.putExtra("Descripcion", etmDescripcion.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });



    }


}