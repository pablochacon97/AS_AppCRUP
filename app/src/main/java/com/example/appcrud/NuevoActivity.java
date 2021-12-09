package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcrud.models.Contactos;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contactos contactos = new Contactos(NuevoActivity.this);
                long id = contactos.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(),txtCorreo.getText().toString());
                if (id>0) {
                    Toast.makeText(NuevoActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    lista();
                } else {
                    Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void lista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}