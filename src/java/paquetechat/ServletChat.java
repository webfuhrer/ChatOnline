/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletChat", urlPatterns = {"/ServletChat"})
public class ServletChat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String accion=request.getParameter("accion");
                if (accion.equals("login"))
                {
                       String usuario=request.getParameter("usuario");
                       String password=request.getParameter("password");
                       boolean usuario_correcto=AccesoBD.comprobarUsuario(usuario, password);
                       if (usuario_correcto)
                       {
                           
                            
                            HttpSession sesion=request.getSession();
                            //Ponemos al usr en sesión
                            sesion.setAttribute("usuario", usuario);
                           //2-Pondria los comentarios en otro atributo
                           List<Comentario> lista=AccesoBD.recuperarComentarios();
                           request.setAttribute("lista_comentarios", lista);
                           //3-Mando al usuario a vercomentarios.jsp
                           request.getRequestDispatcher("vercomentarios.jsp").forward(request, response);
                       }
                       else
                       {
                           //1-Mandaría al usuario de vuelta a index.html
                           request.setAttribute("error", "Error en la autenticación");
                           request.getRequestDispatcher("indice.jsp").forward(request, response);
                           
                       }
                       
                }
                if (accion.equals("registro"))
                {
                    String usuario=request.getParameter("usuario");
                    String password=request.getParameter("password");
                    String avatar=request.getParameter("avatar");
                    Usuario u=new Usuario(usuario, password, avatar);
                    AccesoBD.insertarUsuario(u);
                             HttpSession sesion=request.getSession();
                            //Ponemos al usr en sesión
                            sesion.setAttribute("usuario", usuario);
                           //2-Pondria los comentarios en otro atributo
                           List<Comentario> lista=AccesoBD.recuperarComentarios();
                           request.setAttribute("lista_comentarios", lista);
                           //3-Mando al usuario a vercomentarios.jsp
                           request.getRequestDispatcher("vercomentarios.jsp").forward(request, response);
                    
                }
                if (accion.equals("insertar_comentario"))
                {
                    HttpSession sesion=request.getSession();
                    String usuario=(String)sesion.getAttribute("usuario");
                    String comentario=request.getParameter("comentario");
                    AccesoBD.insertarComentario(usuario, comentario);
                    request.setAttribute("usuario", usuario);
                           //2-Pondria los comentarios en otro atributo
                           List<Comentario> lista=AccesoBD.recuperarComentarios();
                           request.setAttribute("lista_comentarios", lista);
                           //3-Mando al usuario a vercomentarios.jsp
                           request.getRequestDispatcher("vercomentarios.jsp").forward(request, response);
                }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
