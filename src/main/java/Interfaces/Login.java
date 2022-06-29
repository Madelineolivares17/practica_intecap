/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author madel
 */
public class Login extends JFrame{
    JLabel nombre= new JLabel();
     JLabel contraseña= new JLabel();
     JLabel l4= new JLabel();
     JTextField nom_ingresar =new JTextField ();
     JPasswordField con_ingresar =new JPasswordField();
     JPanel p1 =new JPanel ();
     JButton b1=new JButton();
     int oportunidades=3;
     
     
     ClienteDao dao=new ClienteDao();
   
    public void Frame (){
        //frame
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setMaximumSize(new Dimension(1000,1000));
        Paneles();
        
         /*imagen.setBounds(10,10,50,50);
         imagen.setIcon(new ImageIcon(getClass().getResource("img/sexy.png")));
             p1.add(imagen);*/
         
       
         
         
    }
     
    public void Paneles (){
        p1.setLayout(null);
        p1.setSize(300,300);
        
        p1.setBackground(Color.getHSBColor(0.7f, 0.5f, 0.9f));
        this.getContentPane().add(p1);
        etiquetas();
        botones();
        cajas_texto();
    }
     
     private void etiquetas(){
         nombre.setText("Nombre:");
         nombre.setBounds(20, 50, 80, 20);
         
         contraseña.setText("Contraseña:");
         contraseña.setBounds(20,150,100,20);
         
         
         
         
        p1.add(nombre);
        p1.add(contraseña);
        
       
     
         
          
     }
     
     private void cajas_texto(){
         nom_ingresar.setBounds(118,50,150,20);
         con_ingresar.setBounds(115,150,150,20);
         p1.add(nom_ingresar);
         p1.add(con_ingresar);
        
         
         
       /* l4.setBackground(Color.red);
	ImageIcon imagen= new ImageIcon ("src/img/logo.png");
	Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(l4.getWidth(), l4.getHeight(), Image.SCALE_DEFAULT));
	l4.setIcon (icono);
        l4.setBounds(60, 10, 500, 500);
         l4.setVisible(true);
	p1.add(l4);*/
         
        
        
        	
        
     }
     
     private void botones (){
         b1.setText("ingresar");
         b1.setBounds(100,200,100,40);
         p1.add(b1);
         
         
         
         ActionListener accion =new ActionListener () {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     login();
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         };
         
         b1.addActionListener(accion);
         
       
     }
     
     private void login() throws ClassNotFoundException, SQLException{
         Boolean prueba =false;
         int y=0;
         String nombre= "";
         List<Usuarios> lista =dao.listarusuario ();
         for (int i = 0; i <lista.size(); i++) {
             y=y+1;
             System.out.println(lista);
             
         }
         Object [][] object =new Object [y][3]; 
         
         for (int i=0;i< lista.size();i++){
             object [i][0]=lista.get(i).getCorreo();
             object [i][1]=lista.get (i).getContraseña();
             object [i][2]=lista.get(i).getNombre();
             
         }
         for (int i = 0; i < object.length; i++) {
             if(nom_ingresar.getText().equals(object[i][0])&& con_ingresar.getText().equals(object[i][1])){
                 prueba =true;
                 nombre = (String)object [i][2].toString();
                 break;
         }else if (oportunidades==0){
                 JOptionPane.showMessageDialog(null, "usuario o contraseña incorrectos, intente de nuevo");
         }else{
             
         }
             
         }
         
         if (prueba.equals(true)){
             MenuPrincipal menu= new MenuPrincipal ();
            menu.Frame(nombre);
             setVisible (false);
             
         }else{
             JOptionPane.showMessageDialog(null, "usuario o contraseña incorrecto");
             oportunidades= oportunidades-1;
         }
      
         
         
         
     }
     
    public static void main(String[] args) {
        Login p = new Login ();
        p.Frame ();
    }
         
     
     
    
}
