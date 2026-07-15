/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import view.Register;
import javax.swing.JOptionPane;
import models.gestor;

/**
 *
 * @author Romel
 */
public class registerController {
    
    // El controlador guarda una referencia de la vista para poder manipularla
    private Register vista;

    // Constructor: recibe la vista ya creada desde el Main
    public registerController(Register vista) {
        this.vista = vista;
        inicializarEventos();
    }

    // Aquí conectamos los botones con sus acciones
    private void inicializarEventos() {
        this.vista.getBtnRegistrarse().addActionListener(e -> validarYRegistrar());
    }

    // La lógica de validación
    private void validarYRegistrar() {
        // 1. Le ordenamos a la vista que limpie los colores
        vista.restaurarColores();
        
        boolean[] errores = new boolean[14];
        
        /**
        *
        * 0 - Ya existe la cedula.
        * 1 - Campo de cedula en blanco.
        * 2 - La cedula incluye letras.
        * 3 - Campo de nombre en blanco. 
        * 4 - El nombre incluye numeros.
        * 5 - El nombre es muy largo.
        * 6 - Campo de apellido en blanco..
        * 7 - El apellido tiene numeros.
        * 8 - El apellido es muy largo. 
        * 9 - Campo de password en blanco..
        * 10 - Password muy corto.  
        * 11 - Password muy largo.
        * 12 - Password2 no coincide con password.
        * 13 - Cedula invalida.
        * 
        */
        
        String[] erroresMensaje = new String[14];
        erroresMensaje[0] = "Ya existe la cedula";
        erroresMensaje[1] = "El campo de cedula no puede estar en blanco";
        erroresMensaje[2] = "La cedula no puede incluir letras";
        erroresMensaje[3] = "El campo de nombre no puede estar en blanco";
        erroresMensaje[4] = "El nombre no puede incluir numeros";
        erroresMensaje[5] = "El nombre no puede tener mas de 45 letras";
        erroresMensaje[6] = "El campo de apellido no puede estar en blanco";
        erroresMensaje[7] = "El apellido no peude tener numeros";
        erroresMensaje[8] = "El apellido no puede tener mas de 45 letras";
        erroresMensaje[9] = "El campo de contraseña no puede estar en blanco";
        erroresMensaje[10] = "La contraseña no puede tener menos de 5 caracteres";
        erroresMensaje[11] = "La contraseña no puede tener mas de 45 caracteres";
        erroresMensaje[12] = "Las contraseñas no coinciden";
        erroresMensaje[13] = "La cédula no puede ser 1";
        
        String cedulaString = vista.getCedula().trim();
        int cedula = Integer.parseInt(cedulaString);
        if(cedulaString.isEmpty()){
            vista.marcarErrorCedula();
            errores[1] = true;
        } else if(cedulaString == "1"){
            vista.marcarErrorCedula();
            errores[13] = true;
        } else if(cedulaString.matches(".*\\p{L}.*")){
            vista.marcarErrorCedula();
            errores[2] = true;
        } else if(!(gestor.buscarCedula(cedula))){
            vista.marcarErrorCedula();
            errores[0] = true;
        } else {
            errores[0] = false;
            errores[1] = false;
            errores[2] = false;
            errores[13] = false;
        }
        
        String nombre = vista.getNombre().trim();
        if(nombre.isEmpty()){
            vista.marcarErrorNombre();
            errores[3] = true;
        } else if(nombre.matches(".*\\d.*")){
            vista.marcarErrorNombre();
            errores[4] = true;
        } else if(nombre.length() > 45){
            vista.marcarErrorNombre();
            errores[5] = true;
        } else {
            errores[3] = false;
            errores[4] = false;
            errores[5] = false;
        }
        
        String apellido = vista.getApellido().trim();
        if(apellido.isEmpty()){
            vista.marcarErrorApellido();
            errores[6] = true;
        } else if(apellido.matches(".*\\d.*")){
            vista.marcarErrorApellido();
            errores[7] = true;
        } else if(apellido.length() > 45){
            vista.marcarErrorApellido();
            errores[8] = true;
        } else {
            errores[6] = false;
            errores[7] = false;
            errores[8] = false;
        }
        
        String password = vista.getContrasena();
        if(password.isEmpty()){
            vista.marcarErrorContrasena();
            errores[9] = true;
        } else if(password.length() < 5){
            vista.marcarErrorContrasena();
            errores[10] = true;
        } else if(password.length() > 45){
            vista.marcarErrorContrasena();
            errores[11] = true;
        } else {
            errores[9] = false;
            errores[10] = false;
            errores[11] = false;
        }
        
        String password2 = vista.getConfirmarContrasena();
        if(password2 != password){
            vista.marcarErrorContrasena();
            errores[12] = true;
        } else {
            errores[12] = false;
        }
        
        String mensajeError = "";
        boolean hayError = false;
        
        for (boolean valor : errores){
            if(valor){
                hayError = true;
                break;
            } else {
                hayError = false;
            }
        }
        
        if(hayError){
            for(int i = 0; i < errores.length; i++){
                if(errores[i]){
                    mensajeError += erroresMensaje[i] + "\n";
                }
            }
            vista.mostrarMensaje(
                mensajeError, // El texto del error
                "Error de Validación", // El título de la ventanita
                JOptionPane.ERROR_MESSAGE // El ícono de error (X roja)
            );
        }
        
        
        
    }
    
}
