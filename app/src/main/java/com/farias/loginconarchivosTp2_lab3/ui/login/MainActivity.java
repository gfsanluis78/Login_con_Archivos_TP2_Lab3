package com.farias.loginconarchivosTp2_lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.farias.loginconarchivosTp2_lab3.R;


public class MainActivity extends AppCompatActivity {

    private EditText email,password;
    private TextView mensaje;
    private Button login, registrar;

    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        inicializarVista();

        mViewModel.getMensaje().observe(this, elMensaje -> {
            mensaje.setText(elMensaje);
            mensaje.setVisibility(View.VISIBLE);
            email.setText("");
            password.setText("");

        });
    }

    private void inicializarVista() {

        email = findViewById(R.id.editText_usuario);
        password = findViewById(R.id.editText_password);
        mensaje = findViewById(R.id.textView_mensaje);
        login = findViewById(R.id.button_login);

        registrar = findViewById(R.id.button_registrarse);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mensaje", "onClick: El email " + email);
                mViewModel.autenticar(email.getText().toString(),password.getText().toString());
                email.setText("");
                password.setText("");

                // para cerrar el teclado virtual
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.aRegistrar();
            }
        });

    }

}