/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author madel
 */
public class ClienteDao {
int r;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion acceso = new Conexion();
    Usuarios pro = new Usuarios();

    public List listarusuario()  {
        String sql = "select* from usuarios";
        List<Usuarios> listaprod = new ArrayList<>();

        try {
            con = acceso.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuarios p = new Usuarios();
                p.setUsuario_id(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setTelefono(rs.getInt(4));
                p.setDireccion(rs.getString(5));
                p.setCorreo(rs.getString(6));
                p.setFecha_nacimiento(rs.getString(7));
                p.setRol_id(rs.getInt(8));
                p.setActivo(rs.getInt(9));
                p.setContraseña(rs.getString(10));
                listaprod.add(p);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return listaprod;

    }

    public void modificar(int usuario_id, String nombre, String apellido, int telefono, String direccion, String correo, String fecha_nacimiento, int rol_id, int activo, String contraseña)throws SQLException {
        String sql = "update usuarios set  nombre=?, apellido=?, telefono=?, direccion=?, correo=?,fecha_nacimiento=?, rol_id=?, activo=?, contraseña=? where usuario_id=?";
        try {
            con = acceso.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, correo);
            ps.setString(6, fecha_nacimiento);
            ps.setInt(7, rol_id);
            ps.setInt(8, activo);
            ps.setString(9, contraseña);
            ps.setInt(10, usuario_id);
            ps.executeUpdate();
            
         } catch (SQLException e) {
            System.out.println(e);

        }
 
        

    }

    public void agregar(int usuario_id, String nombre, String apellido, int telefono, String direccion, String correo, String fecha_nacimiento, int rol_id, int activo, String contraseña) {
        String sql = "insert into usuarios (usuario_id,nombre,apellido,telefono,direccion,correo,fecha_nacimiento,rol_id,activo,contraseña)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            con = acceso.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, usuario_id);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setInt(4, telefono);
            ps.setString(5, direccion);
            ps.setString(6, correo);
            ps.setString(7, fecha_nacimiento);
            ps.setInt(8, rol_id);
            ps.setInt(9, activo);
            ps.setString(10, contraseña);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

        }
  
       

    }
    
    public void delete (int id){
        String sql= "delete from usuarios where usuario_id=?";
        try{
            con= acceso.conectar();
            ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
        
        
    }
    
    
    

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ClienteDao rp = new ClienteDao();
      
      //rp.agregar(2, "franciso", "o", 454512, "dasasd", "ddsds", "2002-06-23", 1, 2, "adsasdsd");
     
     
        //para ver los cambios en la base de datos
        List<Usuarios> lista = rp.listarusuario();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getNombre());

        }
    }

}
