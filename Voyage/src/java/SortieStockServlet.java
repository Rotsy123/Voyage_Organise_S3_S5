/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Fabrication;
import model.Voyage;

/**
 *
 * @author USER
 */
@WebServlet(urlPatterns = {"/SortieStockServlet"})
public class SortieStockServlet extends HttpServlet {

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
        Connexion conn=new Connexion();
        
        try {
            List<Voyage> allVoyage=new Voyage().GetAllVoyage(conn.GetConnection());
            request.setAttribute("voyages",allVoyage);
            
            request.getRequestDispatcher("sortie.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EntreeStockServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        Date dt=Date.valueOf(request.getParameter("date"));
        int nbBillet=Integer.parseInt(request.getParameter("nombreBillets"));
        
        String idvoyage=request.getParameter("voyage");
        Fabrication toInsert;
        try {
            toInsert = new Fabrication(idvoyage,nbBillet,dt);
            Connexion conn=new Connexion();
            toInsert.Insert(conn.GetConnection());
            List<Voyage> allVoyage=new Voyage().GetAllVoyage(conn.GetConnection());
            request.setAttribute("voyages",allVoyage);
            request.getRequestDispatcher("sortie.jsp").forward(request, response);
        } catch (Exception ex) {
                System.out.println("ERREUR: "+ ex.getMessage());
             request.setAttribute("message",ex.getMessage());
            
            request.getRequestDispatcher("sortie.jsp").forward(request, response);
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
