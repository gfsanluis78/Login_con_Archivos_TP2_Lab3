package com.farias.loginconsharedpreferencestp1_lab3.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.farias.loginconsharedpreferencestp1_lab3.R;
import com.farias.loginconsharedpreferencestp1_lab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText dni,apellido,nombre,email,password;
    private TextView mensaje;
    private Button guardar;
    private RegistroActivityViewModel mViewModel;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Usuario usuarioActual = (Usuario) getIntent().getSerializableExtra("usuario");

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        inicializarVista();

        mViewModel.getUsuario().observe(this, usuario -> {

            dni.setText(usuario.getDni() + "");
            apellido.setText(usuario.getApellido());
            nombre.setText(usuario.getNombre());
            email.setText(usuario.getMail());
            password.setText(usuario.getPassword());

        });

        mViewModel.getMensaje().observe(this, elMensaje -> {
            mensaje.setText(elMensaje);
            mensaje.setVisibility(View.VISIBLE);
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mViewModel.registrar(Long.parseLong(dni.getText().toString()),
                        apellido.getText().toString(),
                        nombre.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        usuarioActual);

                // para cerrar el teclado virtual
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);

                guardar.setVisibility(View.GONE);
            }

        });

        mViewModel.mostrar(usuarioActual);

    }

    private void inicializarVista() {

        dni= findViewById(R.id.editText_dni);
        apellido=findViewById(R.id.editText_apellido);
        nombre=findViewById(R.id.editText_nombre);
        email=findViewById(R.id.editText_mail);
        password=findViewById(R.id.editText_password_registro);
        guardar=findViewById(R.id.button_guardar);
        mensaje = findViewById(R.id.textView_mensaje);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.exit(0);
    }
}