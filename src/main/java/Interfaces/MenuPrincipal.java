/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author madel
 */
public class MenuPrincipal extends JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion acceso = new Conexion();
    Usuarios pro = new Usuarios();

    JTabbedPane menu = new JTabbedPane();
    ClienteDao dao = new ClienteDao();

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JLabel t1 = new JLabel();
    JLabel t2 = new JLabel();

    JPanel panel1 = new JPanel();

    Login login = new Login();

    JTable tabla = new JTable();
    JScrollPane sp = new JScrollPane(tabla);
    Conexion conec = new Conexion();
    Usuarios p = new Usuarios();

    Object[] usuarios = new Object[6];
    DefaultTableModel modelo = new DefaultTableModel();

    private String nombre;
    int estado = 1, rol;

    public void Frame(String nombre) throws ClassNotFoundException, SQLException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setMaximumSize(new Dimension(1000, 1000));
        setVisible(true);

        setTitle("Hoteles GT- Usuarios");

        menu.addTab("Tabla informacion", panel1);

        JButton cerrar = new JButton("salir");
        cerrar.setBackground(Color.red);
        cerrar.setBounds(761, 0, 115, 20);
        this.getContentPane().add(menu);

        this.getContentPane().add(menu);

        panel1.setLayout(null);

        ActionListener cerrar_accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                login.Frame();
            }
        };

        cerrar.addActionListener(cerrar_accion);

        t1.setText("Usuario:");
        t1.setBounds(600, 10, 100, 20);

        t1.setText(nombre);
        t1.setBounds(670, 10, 200, 20);

        panel1.add(t1);
        panel1.add(t2);

        boton();
        tabla();

    }

    public void ejecutar() throws ClassNotFoundException, SQLException {
        boton();
        Frame(nombre);
        try {
            tabla();
        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void boton() {
        b1.setText("Modificar ");
        b1.setBounds(150, 500, 100, 40);
        panel1.add(b1);

        ActionListener modificarus = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                modificarUsuario();

            }
        };

        // Acción del evento
        b1.addActionListener(modificarus);

        
        
        b2.setText("Eliminar ");
        b2.setBounds(350, 500, 100, 40);
        panel1.add(b2);
        ActionListener Eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Eliminar();

			}
		};

		// Acción del evento
		b2.addActionListener(Eliminar);
	
        
        

        b3.setText("Agregar");
        b3.setBounds(550, 500, 100, 40);
        panel1.add(b3);

        ActionListener agregar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                crear();

            }
        };
        b3.addActionListener(agregar);

    }

    public void agregar(String nombre, String apellido, int telefono, String direccion, String correo,
            String fecha_nacimiento, int rol_id, int estado, String contrasenia) {
        String sql = "insert into usuarios(nombre, apellido,telefono,direccion,correo_electronico,fecha_nacimiento, rol_id, activo,contraseña)values(?,?,?,?,?,?,?,?,?)";

        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, correo);
            ps.setString(6, fecha_nacimiento);
            ps.setInt(7, rol_id);
            ps.setInt(8, estado);
            ps.setString(9, contrasenia);
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void crear() {
        String nombre1 = nombre;
        int Rol1 = rol;
        login.setVisible(false);
        JFrame crearUsuarios = new JFrame();
        JPanel p1 = new JPanel();
        crearUsuarios.setLocationRelativeTo(null);
        crearUsuarios.setTitle("Hoteles GT-Administrador");
        crearUsuarios.setBackground(Color.WHITE);

        crearUsuarios.setLayout(null);
    
        crearUsuarios.setBounds(750, 150, 500, 650);
        crearUsuarios.setVisible(true);
        crearUsuarios.setResizable(false);
        crearUsuarios.add(p1);
        p1.setSize(500, 850);
        p1.setLayout(null);
        p1.setVisible(true);
        p1.setBackground(Color.white);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton activo = new JRadioButton("Activo");
        JRadioButton inactivo = new JRadioButton("Inactivo");
        bg.add(activo);
        bg.add(inactivo);

        activo.setFont(new Font("serig", Font.PLAIN, 22));
        activo.setBounds(120, 470, 100, 25);
        p1.add(activo);

        inactivo.setFont(new Font("serig", Font.PLAIN, 22));
        inactivo.setBounds(260, 470, 150, 25);
        p1.add(inactivo);

         JLabel l1 = new JLabel("Modificar Usuario");
            JLabel l2 = new JLabel("Nombre");
            JLabel l3 = new JLabel("Apellido");
            JLabel l4 = new JLabel("Telefono");
            JLabel l5 = new JLabel("Direccion");
            JLabel l6 = new JLabel("Correo");
            JLabel l7 = new JLabel("Fecha Nacimiento");
            JLabel formatoFecha = new JLabel("Año/Mes/Dia");
            JLabel l8 = new JLabel("Contraseña");

            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
            JTextField t3 = new JTextField();
            JTextField t4 = new JTextField();
            JTextField t5 = new JTextField();
            JTextField t6 = new JTextField();
            JPasswordField t7 = new JPasswordField();
            JButton B1 = new JButton("Guardar Cambios");
            JButton B2 = new JButton("Cancelar");

            l1.setFont(new Font("serig", Font.PLAIN, 15));
            l1.setBounds(175, 10, 250, 25);
            p1.add(l1);

            l2.setFont(new Font("serig", Font.PLAIN, 15));
            l2.setBounds(50, 70, 100, 25);
            p1.add(l2);

            l3.setFont(new Font("serig", Font.PLAIN, 15));
            l3.setBounds(50, 120, 100, 25);
            p1.add(l3);

            l4.setFont(new Font("serig", Font.PLAIN, 15));
            l4.setBounds(50, 170, 125, 25);
            p1.add(l4);

            l5.setFont(new Font("serig", Font.PLAIN, 15));
            l5.setBounds(50, 220, 100, 25);
            p1.add(l5);

            l6.setFont(new Font("serig", Font.PLAIN, 15));
            l6.setBounds(75, 270, 100, 25);
            p1.add(l6);

            formatoFecha.setFont(new Font("serig", Font.PLAIN, 10));
            formatoFecha.setBounds(200, 300, 200, 25);
            p1.add(formatoFecha);

            l7.setFont(new Font("serig", Font.PLAIN, 15));
            l7.setBounds(20, 320, 200, 25);
            p1.add(l7);

            l8.setFont(new Font("serig", Font.PLAIN, 15));
            l8.setBounds(50, 370, 200, 25);
            p1.add(l8);

            t1.setFont(new Font("serig", Font.PLAIN, 15));
            t1.setBounds(200, 70, 200, 25);
            p1.add(t1);

            t2.setFont(new Font("serig", Font.PLAIN, 15));
            t2.setBounds(200, 120, 200, 25);
            p1.add(t2);

            t3.setFont(new Font("serig", Font.PLAIN, 15));
            t3.setBounds(200, 170, 200, 25);
            p1.add(t3);

            t4.setFont(new Font("serig", Font.PLAIN, 15));
            t4.setBounds(200, 220, 200, 25);
            p1.add(t4);

            t5.setFont(new Font("serig", Font.PLAIN, 15));
            t5.setBounds(200, 270, 200, 25);
            p1.add(t5);

            t6.setFont(new Font("serig", Font.PLAIN, 15));
            t6.setBounds(200, 320, 200, 25);
            p1.add(t6);

            t7.setFont(new Font("serig", Font.PLAIN, 15));
            t7.setBounds(200, 370, 200, 25);
            p1.add(t7);

            B1.setFont(new Font("serig", Font.PLAIN, 15));
            B1.setForeground(Color.white);
            B1.setBackground(Color.BLUE);
            B1.setBounds(40, 520, 250, 50);
            p1.add(B1);

            B2.setFont(new Font("serig", Font.PLAIN, 15));
            B2.setForeground(Color.white);
            B2.setBackground(Color.BLUE);
            B2.setBounds(300, 520, 150, 50);
            p1.add(B2);

        ActionListener agregarbd = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (activo.isSelected()) {
                    estado = 1;
                } else if (inactivo.isSelected()) {
                    estado = 0;
                }

                if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty()
                        || t5.getText().isEmpty() || t6.getText().isEmpty() || t7.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "DATOS INCORRECTOS");
                } else {
                    agregar(t1.getText(), t2.getText(), Integer.parseInt(t3.getText()), t4.getText(), t5.getText(),
                            t6.getText(), rol, estado, t7.getText());
                    JOptionPane.showMessageDialog(null, "SE HA AGREGADO CORRECTAMENTE");
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mu = new MenuPrincipal();
                    mu.setNombre(nombre1);
                    mu.setRol(Rol1);
                    try {
                        mu.ejecutar();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        };

        // Acción del evento
        B1.addActionListener(agregarbd);

        ActionListener cancelar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuarios.setVisible(false);
                MenuPrincipal mu = new MenuPrincipal();
                mu.setNombre(nombre1);
                mu.setRol(Rol1);
                try {
                    mu.ejecutar();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        // Acción del evento
        B2.addActionListener(cancelar);
    }

    /*public ArrayList <Usuarios> listaprod(){
        List<Usuarios> listaprod = new ArrayList<>();
          String sql = "select* from usuarios";
        try{
             con = acceso.conectar();
            Statement st = con.createStatement();
            rs = ps.executeQuery(sql);
            Usuarios usuarios;
            while (rs.next()){
                usuarios= new Usuarios( rs.getInt("usuario_id"),rs.getString("nombre"),rs.getString("apellido"),rs.getInt("rol_id"),rs.getString("correo"),rs.getInt("telefono"));
            
                listaprod.add(usuarios);
            }
            
        }
         catch (Exception e) {
            System.out.println(e);
        }
        return  (ArrayList<Usuarios>) listaprod;
        
    }
    
   //String sql = "select u.usuario_id, u.nombre, u.apellido, r.nombre, u.correo_electronico , u.telefono  from usuarios u, roles r WHERE u.rol_id = r.rol_id";
    private void tabla() {
List<Usuarios> list = listaprod();
DefaultTableModel model= new DefaultTableModel();
Object [] row = new Object [6];
        for (int i = 0; i < list.size(); i++) {
            row [0]=list.get(i).getUsuario_id();
            row [1]=list.get(i).getNombre();
            row [2]=list.get(i).getApellido();
            row [3]=list.get(i).getRol_id();
            row [4]=list.get(i).getCorreo();
            row [5]=list.get(i).getTelefono();
            
            model.addRow (row);
        }
        
    
    }*/
 /*
    private void tabla(){
    DefaultTableModel modelo= new DefaultTableModel();
    modelo.addColumn("No");
     modelo.addColumn("Nombre");
      modelo.addColumn("apellido");
       modelo.addColumn("rol");
        modelo.addColumn("correo");
         modelo.addColumn("telefono");
         
        JTable tabla= new JTable (modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        tabla.setBounds (12,22,200,200);
        setSize (750,700);
        scroll.setBounds (12,22,200,200);
        
        add(scroll);
        
        setLayout(null);
        setVisible (true);
      
        panel1.add(scroll);
                
    
    
}*/
    private void tabla() throws ClassNotFoundException, SQLException {
        modelo.addColumn("No.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Rol");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");
        tabla.setModel(modelo);
        sp.setBounds(30, 50, 700, 400);
        panel1.add(sp);

        List<Usuarios> Listusa = listaUsuario();

        for (int i = 0; i < Listusa.size(); i++) {
            usuarios[0] = Listusa.get(i).getUsuario_id();
            usuarios[1] = Listusa.get(i).getNombre();
            usuarios[2] = Listusa.get(i).getApellido();
            usuarios[3] = Listusa.get(i).getNombre_rol();
            usuarios[4] = Listusa.get(i).getCorreo();
            usuarios[5] = Listusa.get(i).getTelefono();
            modelo.addRow(usuarios);
        }

    }

    public List listaUsuario() {
        String sql = "select u.usuario_id, u.nombre, u.apellido, r.nombre, u.correo_electronico , u.telefono  from usuarios u, roles r WHERE u.rol_id = r.rol_id";
        List<Usuarios> listaus = new ArrayList<>();

        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuarios p = new Usuarios();
                p.setUsuario_id(rs.getInt("u.usuario_id"));
                p.setNombre(rs.getString("u.nombre"));
                p.setApellido(rs.getString("u.apellido"));
                p.setTelefono(rs.getInt("u.telefono"));
                p.setCorreo(rs.getString("u.correo_electronico"));
                p.setNombre_rol(rs.getString("r.nombre"));
                listaus.add(p);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        }
        return listaus;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void modificar(String nombre, String apellido, int telefono, String direccion, String correo,
            String fecha_nacimiento, String contrasenia, int id) {
        String sql = "update usuarios set nombre=?, apellido=?, telefono=?, direccion=?, correo_electronico=?, fecha_nacimiento=?, contraseña=? where usuario_id=?";

        try {
            con = conec.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, correo);
            ps.setString(6, fecha_nacimiento);
            ps.setString(7, contrasenia);
            ps.setInt(8, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    private void modificarUsuario() {
        int seleccionar = tabla.getSelectedRow();
        if (seleccionar != -1) {
            String nombre1 = nombre;
            int Rol1 = rol;
            login.setVisible(false);
            JFrame crearUsuarios = new JFrame();
            JPanel p1 = new JPanel();
            crearUsuarios.setLocationRelativeTo(null);
            crearUsuarios.setTitle("Hoteles GT-Administrador");
            crearUsuarios.setBackground(Color.WHITE);

            crearUsuarios.setLayout(null);
            crearUsuarios.setBounds(750, 150, 500, 650);
            crearUsuarios.setVisible(true);
            crearUsuarios.setResizable(false);
            crearUsuarios.add(p1);
            p1.setSize(500, 850);
            p1.setLayout(null);
            p1.setVisible(true);
            p1.setBackground(Color.white);

            JLabel l1 = new JLabel("Modificar Usuario");
            JLabel l2 = new JLabel("Nombre");
            JLabel l3 = new JLabel("Apellido");
            JLabel l4 = new JLabel("Telefono");
            JLabel l5 = new JLabel("Direccion");
            JLabel l6 = new JLabel("Correo");
            JLabel l7 = new JLabel("Fecha Nacimiento");
            JLabel formatoFecha = new JLabel("Año/Mes/Dia");
            JLabel l8 = new JLabel("Contraseña");

            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
            JTextField t3 = new JTextField();
            JTextField t4 = new JTextField();
            JTextField t5 = new JTextField();
            JTextField t6 = new JTextField();
            JPasswordField t7 = new JPasswordField();
            JButton B1 = new JButton("Guardar Cambios");
            JButton B2 = new JButton("Cancelar");

            l1.setFont(new Font("serig", Font.PLAIN, 15));
            l1.setBounds(175, 10, 250, 25);
            p1.add(l1);

            l2.setFont(new Font("serig", Font.PLAIN, 15));
            l2.setBounds(50, 70, 100, 25);
            p1.add(l2);

            l3.setFont(new Font("serig", Font.PLAIN, 15));
            l3.setBounds(50, 120, 100, 25);
            p1.add(l3);

            l4.setFont(new Font("serig", Font.PLAIN, 15));
            l4.setBounds(50, 170, 125, 25);
            p1.add(l4);

            l5.setFont(new Font("serig", Font.PLAIN, 15));
            l5.setBounds(50, 220, 100, 25);
            p1.add(l5);

            l6.setFont(new Font("serig", Font.PLAIN, 15));
            l6.setBounds(75, 270, 100, 25);
            p1.add(l6);

            formatoFecha.setFont(new Font("serig", Font.PLAIN, 10));
            formatoFecha.setBounds(200, 300, 200, 25);
            p1.add(formatoFecha);

            l7.setFont(new Font("serig", Font.PLAIN, 15));
            l7.setBounds(20, 320, 200, 25);
            p1.add(l7);

            l8.setFont(new Font("serig", Font.PLAIN, 15));
            l8.setBounds(50, 370, 200, 25);
            p1.add(l8);

            t1.setFont(new Font("serig", Font.PLAIN, 15));
            t1.setBounds(200, 70, 200, 25);
            p1.add(t1);

            t2.setFont(new Font("serig", Font.PLAIN, 15));
            t2.setBounds(200, 120, 200, 25);
            p1.add(t2);

            t3.setFont(new Font("serig", Font.PLAIN, 15));
            t3.setBounds(200, 170, 200, 25);
            p1.add(t3);

            t4.setFont(new Font("serig", Font.PLAIN, 15));
            t4.setBounds(200, 220, 200, 25);
            p1.add(t4);

            t5.setFont(new Font("serig", Font.PLAIN, 15));
            t5.setBounds(200, 270, 200, 25);
            p1.add(t5);

            t6.setFont(new Font("serig", Font.PLAIN, 15));
            t6.setBounds(200, 320, 200, 25);
            p1.add(t6);

            t7.setFont(new Font("serig", Font.PLAIN, 15));
            t7.setBounds(200, 370, 200, 25);
            p1.add(t7);

            B1.setFont(new Font("serig", Font.PLAIN, 15));
            B1.setForeground(Color.white);
            B1.setBackground(Color.BLUE);
            B1.setBounds(40, 470, 250, 50);
            p1.add(B1);

            B2.setFont(new Font("serig", Font.PLAIN, 15));
            B2.setForeground(Color.white);
            B2.setBackground(Color.BLUE);
            B2.setBounds(300, 470, 150, 50);
            p1.add(B2);

            con = conec.conectar();
            String sql = "SELECT nombre,apellido,telefono,direccion,correo_electronico,fecha_nacimiento,contraseña FROM usuarios WHERE usuario_id=? ";

            int id = Integer.parseInt(this.tabla.getValueAt(seleccionar, 0).toString());

            try {

                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    t1.setText(rs.getString(1));
                    t2.setText(rs.getString(2));
                    t3.setText(rs.getInt(3) + "");
                    t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5));
                    t6.setText(rs.getString(6));
                    t7.setText(rs.getString(7));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            ActionListener cancelar = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    crearUsuarios.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.setNombre(nombre1);
                    mp.setRol(Rol1);
                    try {
                        mp.ejecutar();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            // Acción del evento
            B2.addActionListener(cancelar);

            ActionListener GuardarCambios = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        modificar(t1.getText(), t2.getText(), Integer.parseInt(t3.getText()), t4.getText(), t5.getText(), t6.getText(), t7.getText(), id);
                        crearUsuarios.setVisible(false);
                        MenuPrincipal mp = new MenuPrincipal();
                        mp.setNombre(nombre1);
                        mp.setRol(Rol1);
                        mp.ejecutar();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };

            // Acción del evento
            B1.addActionListener(GuardarCambios);
        } else {
            JOptionPane.showMessageDialog(null, "seleccione  un usuario");
        }

    }

    public void eliminar(int id) {
		String sql = "delete from usuarios where usuario_id =?";

		try {
			con = conec.conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

    
    private void Eliminar() {
		int seleccionar = tabla.getSelectedRow();
		
		if (seleccionar != -1) {
			String nombre1 = nombre;
			int Rol1= rol;
			login.setVisible(false);
			JFrame crearUsuarios = new JFrame();
			JPanel p1 = new JPanel();
			crearUsuarios.setLocationRelativeTo(null);
			crearUsuarios.setTitle("Hoteles GT-Administrador");
			crearUsuarios.setBackground(Color.WHITE);

			crearUsuarios.setLayout(null);
			// x y ancho y alto
			crearUsuarios.setBounds(750, 150, 500, 850);
			crearUsuarios.setVisible(true);
			crearUsuarios.setResizable(false);
			crearUsuarios.add(p1);
			p1.setSize(500, 850);
			p1.setLayout(null);
			p1.setVisible(true);
			p1.setBackground(Color.white);

			JLabel l1 = new JLabel("Modificar Usuario");
            JLabel l2 = new JLabel("Nombre");
            JLabel l3 = new JLabel("Apellido");
            JLabel l4 = new JLabel("Telefono");
            JLabel l5 = new JLabel("Direccion");
            JLabel l6 = new JLabel("Correo");
            JLabel l7 = new JLabel("Fecha Nacimiento");
            JLabel formatoFecha = new JLabel("Año/Mes/Dia");
            JLabel l8 = new JLabel("Contraseña");

            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
            JTextField t3 = new JTextField();
            JTextField t4 = new JTextField();
            JTextField t5 = new JTextField();
            JTextField t6 = new JTextField();
            JPasswordField t7 = new JPasswordField();
            JButton B1 = new JButton("Guardar Cambios");
            JButton B2 = new JButton("Cancelar");

            l1.setFont(new Font("serig", Font.PLAIN, 15));
            l1.setBounds(175, 10, 250, 25);
            p1.add(l1);

            l2.setFont(new Font("serig", Font.PLAIN, 15));
            l2.setBounds(50, 70, 100, 25);
            p1.add(l2);

            l3.setFont(new Font("serig", Font.PLAIN, 15));
            l3.setBounds(50, 120, 100, 25);
            p1.add(l3);

            l4.setFont(new Font("serig", Font.PLAIN, 15));
            l4.setBounds(50, 170, 125, 25);
            p1.add(l4);

            l5.setFont(new Font("serig", Font.PLAIN, 15));
            l5.setBounds(50, 220, 100, 25);
            p1.add(l5);

            l6.setFont(new Font("serig", Font.PLAIN, 15));
            l6.setBounds(75, 270, 100, 25);
            p1.add(l6);

            formatoFecha.setFont(new Font("serig", Font.PLAIN, 10));
            formatoFecha.setBounds(200, 300, 200, 25);
            p1.add(formatoFecha);

            l7.setFont(new Font("serig", Font.PLAIN, 15));
            l7.setBounds(20, 320, 200, 25);
            p1.add(l7);

            l8.setFont(new Font("serig", Font.PLAIN, 15));
            l8.setBounds(50, 370, 200, 25);
            p1.add(l8);

            t1.setFont(new Font("serig", Font.PLAIN, 15));
            t1.setBounds(200, 70, 200, 25);
            p1.add(t1);

            t2.setFont(new Font("serig", Font.PLAIN, 15));
            t2.setBounds(200, 120, 200, 25);
            p1.add(t2);

            t3.setFont(new Font("serig", Font.PLAIN, 15));
            t3.setBounds(200, 170, 200, 25);
            p1.add(t3);

            t4.setFont(new Font("serig", Font.PLAIN, 15));
            t4.setBounds(200, 220, 200, 25);
            p1.add(t4);

            t5.setFont(new Font("serig", Font.PLAIN, 15));
            t5.setBounds(200, 270, 200, 25);
            p1.add(t5);

            t6.setFont(new Font("serig", Font.PLAIN, 15));
            t6.setBounds(200, 320, 200, 25);
            p1.add(t6);

            t7.setFont(new Font("serig", Font.PLAIN, 15));
            t7.setBounds(200, 370, 200, 25);
            p1.add(t7);

            B1.setFont(new Font("serig", Font.PLAIN, 15));
            B1.setForeground(Color.white);
            B1.setBackground(Color.BLUE);
            B1.setBounds(40, 470, 250, 50);
            p1.add(B1);

            B2.setFont(new Font("serig", Font.PLAIN, 15));
            B2.setForeground(Color.white);
            B2.setBackground(Color.BLUE);
            B2.setBounds(300, 470, 150, 50);
            p1.add(B2);

			

			con = conec.conectar();
			String sql = "SELECT nombre,apellido,telefono,direccion,correo_electronico,fecha_nacimiento,contraseña FROM usuarios WHERE usuario_id=? ";
			
			int id = Integer.parseInt(this.tabla.getValueAt(seleccionar, 0).toString());
			
			try { 

				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					t1.setText(rs.getString(1));
					t2.setText(rs.getString(2));
					t3.setText(rs.getInt(3) +"");
					t4.setText(rs.getString(4));
					t5.setText(rs.getString(5));
					t6.setText(rs.getString(6));
					t7.setText(rs.getString(7));
					t1.setEditable(false);
					t2.setEditable(false);
					t3.setEditable(false);
					t4.setEditable(false);
					t5.setEditable(false);
					t6.setEditable(false);
					t7.setEditable(false);
				}
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			
			ActionListener cancelar = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
                                    try {
                                        crearUsuarios.setVisible(false);
                                        MenuPrincipal mu = new MenuPrincipal();
                                        mu.setNombre(nombre1);
                                        mu.setRol(Rol1);
                                        mu.ejecutar();
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}
			};

			// Acción del evento
			B2.addActionListener(cancelar);
			
			
			ActionListener borrar = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
                                    try {
                                        eliminar(id);
                                        crearUsuarios.setVisible(false);
                                        MenuPrincipal mu = new MenuPrincipal();
                                        mu.setNombre(nombre1);
                                        mu.setRol(Rol1);
                                        mu.ejecutar();
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}
			};

			// Acción del evento
			B1.addActionListener(borrar);
		}else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar a un usuario");
		}
		
	}
}

