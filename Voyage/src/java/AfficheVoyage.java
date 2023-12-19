/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Activite;
import model.Connexion;
import model.Voyage;

/**
 *
 * @author USER
 */
@WebServlet(urlPatterns = {"/AfficheVoyage"})
public class AfficheVoyage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AfficheVoyage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AfficheVoyage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {       
        Connexion c=new Connexion();
        Connection connexion=c.GetConnection();
        List<Activite> all = new Activite().GetAllActivite(connexion);
        request.setAttribute("activite", all);
        request.getRequestDispatcher("AfficheVoyage.jsp").forward(request, response);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AfficheActiviteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
        Connexion conn=new Connexion();
        Connection c=conn.GetConnection();
        String idactivite= request.getParameter("activite");
        System.out.println(idactivite);
        List<Activite> all = new Activite().GetAllActivite(c);
        request.setAttribute("activite", all);
        
            List<Voyage> allVoyage=new Voyage().getByIdActivite(c, idactivite);
            request.setAttribute("voyage",allVoyage);
            request.getRequestDispatcher("AfficheVoyage.jsp").forward(request,response);
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheVoyage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AfficheVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
