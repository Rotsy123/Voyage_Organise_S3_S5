import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
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
@WebServlet(urlPatterns = {"/BouquetActiviteServlets"})
public class BouquetActiviteServlets extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
       
        Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage", "postgres", "root");
        List<Bouquet> all = new Bouquet().GetAllBouquet(connexion);
        List<Activite> allactivite = new Activite().GetAllActivite(connexion);
        System.out.println(all.size()+"  "+all.get(0).getIdBouquet());
        request.setAttribute("bouquet", all);
        request.setAttribute("activite", allactivite);
        request.getRequestDispatcher("BouquetActivite.jsp").forward(request, response);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder=new StringBuilder();
            String line;
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage", "postgres", "root");
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String jsonData=stringBuilder.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(jsonData+" DATA");
            BouquetActivite[] bouquet = objectMapper.readValue(jsonData, BouquetActivite[].class);
            System.out.println(bouquet.length+" TAILLE");
            
            for (int i = 0; i < bouquet.length; i++) {
                BouquetActivite bouquetActivite = new BouquetActivite(bouquet[i].getIdbouquet(), bouquet[i].getIdactivite());
                bouquetActivite.Insert(connexion);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BouquetActiviteServlets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
