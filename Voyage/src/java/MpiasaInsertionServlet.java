/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import model.CategorieMpiasa;
import model.Connexion;
import model.Mpiasa;

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/MpiasaInsertionServlet"})
public class MpiasaInsertionServlet extends HttpServlet {

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
            List<CategorieMpiasa> lsC=CategorieMpiasa.GetAll();
            request.setAttribute("lscate",lsC);
            request.getRequestDispatcher("insertionMpiasa.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MpiasaInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try{
            String nom=request.getParameter("nom");
            Date dt=Date.valueOf(request.getParameter("dtn"));
            int categorie=Integer.parseInt(request.getParameter("categorie"));
            double salaire=Double.parseDouble(request.getParameter("salaire"));
            Date dtembauche=Date.valueOf(request.getParameter("dtembauche"));

            Mpiasa toInsert=new Mpiasa(nom,dt,categorie,salaire, dtembauche);
            toInsert.Insert(new Connexion().GetConnection());
            List<CategorieMpiasa> lsC=CategorieMpiasa.GetAll();
            request.setAttribute("lscate",lsC);
            request.getRequestDispatcher("insertionMpiasa.jsp").forward(request, response);
        } catch (Exception ex) {
            try {
                String message=ex.getMessage();
                request.setAttribute("erreur",message);
                List<CategorieMpiasa> lsC=CategorieMpiasa.GetAll();
                request.setAttribute("lscate",lsC);
                request.getRequestDispatcher("insertionMpiasa.jsp").forward(request, response);
            } catch (Exception ex1) {
                Logger.getLogger(MpiasaInsertionServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
