package com.farias.loginconarchivosTp2_lab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.farias.loginconarchivosTp2_lab3.model.Usuario;
import com.farias.loginconarchivosTp2_lab3.request.ApiClient;
import com.farias.loginconarchivosTp2_lab3.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> email;
    private MutableLiveData<String> pass;
    private MutableLiveData<String> mensaje;
    private ApiClient apiClient;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.apiClient = new ApiClient();
    }

    public LiveData<String> getMensaje(){
        if (mensaje == null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public void autenticar(String mail, String pass){
        Usuario usuario = apiClient.login(context, mail, pass);
        if (usuario != null){
            mensaje.setValue("");
            Intent intent=new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario",usuario);
            context.startActivity(intent);
        }
        else {
            mensaje.setValue("Email o Password incorrecto ");
        }

    }

    public void aRegistrar(){
        Intent intent=new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
