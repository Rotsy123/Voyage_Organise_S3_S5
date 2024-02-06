/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategorieMpiasa;
import model.Connexion;
import model.Vente;
import model.Voyage;

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/VenteInsertion"})
public class VenteInsertion extends HttpServlet {

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
        
        try {
            Connexion con=new Connexion();
            Connection c=con.GetConnection();
            List<Voyage> ls=Voyage.GetAllVoyage(c);
            request.setAttribute("voyages",ls);
            request.getRequestDispatcher("insertionVente.jsp").forward(request,response);
        } catch (Exception ex) {
            
            Logger.getLogger(VenteInsertion.class.getName()).log(Level.SEVERE, null, ex);
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
                        processRequest(request, response);

           
        } catch (Exception ex) {
            Logger.getLogger(ClientInsertion.class.getName()).log(Level.SEVERE, null, ex);
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
            int matricule=Integer.parseInt(request.getParameter("matricule"));
            int idvoyage=Integer.parseInt(request.getParameter("voyage"));
            int nb=Integer.parseInt(request.getParameter("nb"));
            Date dtAchat=Date.valueOf(request.getParameter("dtachat"));
            Vente toInsert=new Vente(idvoyage,nb,matricule,dtAchat);
            Connexion con=new Connexion();
            toInsert.Inserer();
            Connection c=con.GetConnection();
            List<Voyage> ls=Voyage.GetAllVoyage(c);
            request.setAttribute("voyages",ls);
            request.getRequestDispatcher("insertionVente.jsp").forward(request,response);
        }
         catch (Exception ex) { 
            try {
                Connexion con=new Connexion();
                Connection c=con.GetConnection();
                
                List<Voyage> ls=Voyage.GetAllVoyage(c);
                request.setAttribute("voyages",ls);
               
                 
            } catch (Exception ex1) {
                String message=ex.getMessage();
                request.setAttribute("erreur",message);
                request.getRequestDispatcher("insertionVente.jsp").forward(request, response);
            }
             String message=ex.getMessage();
                request.setAttribute("erreur",message);
                request.getRequestDispatcher("insertionVente.jsp").forward(request, response);
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
