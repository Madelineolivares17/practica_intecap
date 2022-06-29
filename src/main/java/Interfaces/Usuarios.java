/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author madel
 */
public class Usuarios {
    private int usuario_id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String direccion;
    private String correo;
    private String fecha_nacimiento;
    private int rol_id;
    private int activo;
    private String contraseña;

  
   
   

   
    
    
    
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public int getRol_id() {
        return rol_id;
    }

    public int getActivo() {
        return activo;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    
    
    
    
    
}
