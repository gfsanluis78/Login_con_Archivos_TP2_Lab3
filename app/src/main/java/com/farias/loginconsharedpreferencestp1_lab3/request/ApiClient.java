package com.farias.loginconsharedpreferencestp1_lab3.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.farias.loginconsharedpreferencestp1_lab3.model.Usuario;

public class ApiClient {
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences conectar(Context  context){
        if (sharedPreferences==null){
            sharedPreferences = context.getSharedPreferences("datos",0);
        }
        return  sharedPreferences;
    }

    public static void guardar(Context context, Usuario usuario){

        SharedPreferences sharedPreferences = conectar(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail", usuario.getMail());
        editor.putString("password", usuario.getPassword());
        editor.commit();

    }

    public Usuario leer (Context context){

        SharedPreferences sharedPreferences = conectar(context);
        Long dni = sharedPreferences.getLong("dni",-1);
        String apellido = sharedPreferences.getString("apellido", "-1");
        String nombre = sharedPreferences.getString("nombre", "-1");
        String email = sharedPreferences.getString("mail", "-1");
        String pass = sharedPreferences.getString("password", "-1");

        Usuario usuario = new Usuario(dni,apellido,nombre,email,pass);
        return usuario;

    }

    public static Usuario login (Context context, String mail, String password){

        Usuario usuario = null;

        SharedPreferences sharedPreferences = conectar(context);
        Long dni = sharedPreferences.getLong("dni",-1);
        String apellido = sharedPreferences.getString("apellido", "-1");
        String nombre = sharedPreferences.getString("nombre", "-1");
        String email = sharedPreferences.getString("mail", "-1");
        String pass = sharedPreferences.getString("password", "-1");

        if(mail.equals(email) && password.equals(pass)){
            usuario = new Usuario(dni, apellido, nombre, email, pass);
        }
        return  usuario;

    }

}
