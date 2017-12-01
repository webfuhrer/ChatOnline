/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
class AccesoBD {
    static Connection c=null;
    static Statement stmt=null;
    private static void abrirConexion()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
            stmt=c.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void insertarUsuario(Usuario u)
    {
        abrirConexion();
        String sql="INSERT INTO t_usuarios(nombre_usuario, password, avatar) VALUES ('"+u.getNombre()+"', '"+u.getPassword()+"' , '"+u.getAvatar()+"');";
       try {
        stmt.executeUpdate(sql);
       } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerrarConexion();
    }
    private static void cerrarConexion()
    {
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public static void insertarComentario(String usuario,String comentario)
    {
        abrirConexion();
        String sql="INSERT INTO `t_comentarios` (id_usuario,comentario) VALUES ((SELECT `id_usuario` FROM `t_usuarios` WHERE `nombre_usuario`='"+usuario+"'), '"+comentario+"')";
        
        try
        {
            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            
        }
        cerrarConexion();
    }

   public static boolean comprobarUsuario(String usuario, String password) {
       
       boolean usuario_ok=false;
        try {
            abrirConexion();
            String sql="SELECT * FROM t_usuarios WHERE nombre_usuario='"+usuario+"' AND password='"+password+"';";
            ResultSet resultados=stmt.executeQuery(sql);
            if(resultados.next())
            {
                usuario_ok=true;
            }
            resultados.close();
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return usuario_ok;
   }

    static List<Comentario> recuperarComentarios() {
        List<Comentario> lista=new ArrayList<>();
        try {
            abrirConexion();
            String sql="SELECT t_usuarios.id_usuario, nombre_usuario,avatar,t_comentarios.comentario\n" +
                    "FROM t_usuarios\n" +
                    "INNER JOIN t_comentarios ON t_usuarios.id_usuario = t_comentarios.id_usuario ORDER BY t_comentarios.id";
            ResultSet resultados=stmt.executeQuery(sql);
            while(resultados.next())
            {
                String nombre=resultados.getString("nombre_usuario");
                String avatar=resultados.getString("avatar");
                String comentario=resultados.getString("comentario");
                Comentario c=new Comentario(nombre, avatar, comentario);
                lista.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
