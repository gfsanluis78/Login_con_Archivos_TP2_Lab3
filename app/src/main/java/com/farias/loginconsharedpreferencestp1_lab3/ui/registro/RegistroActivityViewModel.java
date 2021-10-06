package com.farias.loginconsharedpreferencestp1_lab3.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.farias.loginconsharedpreferencestp1_lab3.model.Usuario;
import com.farias.loginconsharedpreferencestp1_lab3.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String> mensaje;
    private Context context;
    private ApiClient apiClient;


    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario=new MutableLiveData<>();
        }
        return usuario;
    }
    public LiveData<String> getMensaje(){
        if(mensaje==null){
            mensaje=new MutableLiveData<>();
        }
        return mensaje;
    }

    public void registrar(Long dni, String apellido, String nombre, String email, String pass, Usuario usuarioActual){
        Usuario u=new Usuario(dni,apellido,nombre,email,pass);
        apiClient.guardar(context,u);
        mensaje.setValue( usuarioActual == null? "El usuario se genero con exito" : "El usuario se actualizo con exito" );
      }

    public void mostrar(Usuario u){
        if(u != null){
            u = apiClient.leer(context);
            usuario.setValue(u);
        }
    }


}
