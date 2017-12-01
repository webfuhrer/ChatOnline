/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechat;

import java.util.List;

/**
 *
 * @author luis
 */
public class CrearHTML {
   public static String  pintarComentarios(List<Comentario> lista)
   {
       String aux="";
       for (Comentario c: lista)
       {
           String nombre=c.getNombre();
           String avatar=c.getAvatar();
           String comentario=c.getComentario();
           String imagen=crearImagen(avatar);
           aux+="<p>"+imagen+" "+nombre+" dijo: "+comentario+"</p><hr>";
       }
         return aux;
   }

    private static String crearImagen(String avatar) {
        String img="<img src='"+avatar+"' width='40px'>";
        return img;
    }
           
}
