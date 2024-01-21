/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Connexion;
import model.FabricationVoyage;
import model.Mpiasa;
import model.Voyage;

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/FabricationServlet"})
public class FabricationServlet extends HttpServlet {

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
            Connection c=new Connexion().GetConnection();
            List<Voyage> lsVoyage=Voyage.GetAllVoyage(c);
            List<Mpiasa> lsMpiasa=Mpiasa.GetAll();
            request.setAttribute("lsVoyage", lsVoyage);
            request.setAttribute("lsMpiasa", lsMpiasa);
            c.close();
            request.getRequestDispatcher("Fabrication.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FabricationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            String idVoyage=request.getParameter("voyage");
            String idMpiasa=request.getParameter("mpiasa");
            double horaire=Double.parseDouble(request.getParameter("horaire"));
            FabricationVoyage toInsert=new FabricationVoyage(idVoyage,idMpiasa,horaire);
            toInsert.Inserer();
            processRequest(request,response);
        } catch (Exception ex) {
            Logger.getLogger(MpiasaInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
