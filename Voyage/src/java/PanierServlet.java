import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BouquetActivite;
import model.Connexion;
import model.Fabrication;
import model.Vente;
import model.Voyage; 

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/PanierServlet"})
public class PanierServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connexion con=new Connexion();
            Connection c=con.GetConnection();
            List<Voyage> ls=Voyage.GetAllVoyage(c);
            request.setAttribute("voyages",ls);
            request.getRequestDispatcher("Panier.jsp").forward(request,response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        BufferedReader reader = request.getReader();
            StringBuilder stringBuilder=new StringBuilder();
            String line;
            Connexion c=new Connexion();
            Connection connexion;
        
            connexion = c.GetConnection();
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String jsonData=stringBuilder.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(jsonData+" DATA");
            Vente[] vente = objectMapper.readValue(jsonData, Vente[].class);
            System.out.println(vente.length+" TAILLE");
            
            for (int i = 0; i < vente.length; i++) { 
                Vente v = new Vente(vente[i].getIdvoyage(), vente[i].getNbProduitLafo(), vente[i].getIdclient(), vente[i].getDtachat());
                Fabrication fabrication = new Fabrication(v.getIdvoyage()+"", v.getNbProduitLafo(), v.getDtachat());
                int f = fabrication.checkDisponibiliteParIdvoyage(connexion);
                if(f == 1){
                        v.Inserer();   
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PanierServlet.class.getName()).log(Level.SEVERE, null, ex);
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
