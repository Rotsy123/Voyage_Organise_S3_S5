/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bouquet;
import model.Voyage;
import model.CategorieLieu;
import model.Connexion;

/**
 *
 * @author USER
 */
@WebServlet(urlPatterns = {"/VoyageInsertion"})
public class VoyageInsertion extends HttpServlet {

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
        Connection c;
        try {
            c = conn.GetConnection();
        
        List<CategorieLieu> allCateLieu=new CategorieLieu().GetAllCategorieLieu(c);
        List<Bouquet> allBouquet = new Bouquet().GetAllBouquet(c);
        request.setAttribute("bouquet",allBouquet);
        request.setAttribute("cateLieu", allCateLieu);
        request.getRequestDispatcher("voyageInsertion.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VoyageInsertion.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("ato");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder=new StringBuilder();
            String line;
            Connexion c=new Connexion();
            Connection connexion=c.GetConnection();
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            System.out.println("ato");

            String jsonData=stringBuilder.toString();
            System.out.println(jsonData);

            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(jsonData+" DATA");
            Voyage[] voyage = objectMapper.readValue(jsonData, Voyage[].class);
        System.out.println(voyage.length+" TAILLE");
        for(int i=0; i<voyage.length; i++){
            voyage[i].Insert(connexion);
        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoyageInsertion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VoyageInsertion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VoyageInsertion.class.getName()).log(Level.SEVERE, null, ex);
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
