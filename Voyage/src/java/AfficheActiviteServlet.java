import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Activite;
import model.Bouquet;
import model.BouquetActivite;

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/AfficheActiviteServlet"})
public class AfficheActiviteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            Class.forName("org.postgresql.Driver");
       
        Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage", "postgres", "root");
        List<Bouquet> all = new Bouquet().GetAllBouquet(connexion);
        request.setAttribute("bouquet", all);
        request.getRequestDispatcher("AfficheActivite.jsp").forward(request, response);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
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
        Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage", "postgres", "root");
        String idbouquet = request.getParameter("bouquet");
        BouquetActivite bq = new BouquetActivite().GetByIdBouquet(connexion, idbouquet);
        List<Activite> allactivite = bq.getActivitels();
        List<Bouquet> all = new Bouquet().GetAllBouquet(connexion);
        request.setAttribute("bouquet", all);
        request.setAttribute("activite", allactivite);
        request.getRequestDispatcher("AfficheActivite.jsp").forward(request, response);
    
        } catch (SQLException ex) {
            Logger.getLogger(AfficheActiviteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
