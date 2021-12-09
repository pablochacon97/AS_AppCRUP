package com.example.appcrud;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcrud.models.Contactos;
import com.example.appcrud.models.ListContactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarAcivity extends AppCompatActivity {
    EditText txtNombre,txtTelefono, txtCorreo;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    ListContactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actitvity);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar= findViewById(R.id.fabEliminar);

        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState==null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        Contactos db = new Contactos(EditarAcivity.this);
        contacto = db.verContacto(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
                    correcto = db.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());
                    if (correcto) {
                        Toast.makeText(EditarAcivity.this, "Registro modificado", Toast.LENGTH_LONG);
                        lista();
                    } else {
                        Toast.makeText(EditarAcivity.this, "Registro NO modificado", Toast.LENGTH_LONG);
                    }
                } else {
                    Toast.makeText(EditarAcivity.this, "Llenar todos los campos", Toast.LENGTH_LONG);
                }
            }
        });
    }
    private void lista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*private void verRegistro() {
        Intent intent = new Intent(this, VerActitvity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }*/
}