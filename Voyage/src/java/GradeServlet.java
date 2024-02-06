import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grade;

/**
 *
 * @author ROTSY
 */
@WebServlet(urlPatterns = {"/GradeServlet"})
public class GradeServlet extends HttpServlet {

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
            if(request.getParameter("nomgrade")!=null){
                String nom = request.getParameter("nomgrade");
                int min = Integer.parseInt(request.getParameter("min"));
                int max = Integer.parseInt(request.getParameter("max"));
                int salaire =Integer.parseInt(request.getParameter("salaire"));
                Grade grade = new Grade(nom, min, max, salaire);
                grade.Inserer();
            }
            
            List<Grade> liste = Grade.GetAll();
            request.setAttribute("grade", liste);
            request.getRequestDispatcher("insertionGrade.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(GradeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    private int parseIntOrDefault(String str, int defaultValue) {
    if (str != null && !str.isEmpty()) {
        return Integer.parseInt(str);
    }
    return defaultValue;
}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        int min = parseIntOrDefault(request.getParameter("min"), 0);
        int max = parseIntOrDefault(request.getParameter("max"), 0);
        int salaire = parseIntOrDefault(request.getParameter("salaire"), 0);

        int id = Integer.parseInt(request.getParameter("idgrade"));
        Grade grade = new Grade(id,nom, min, max, salaire);
        
        try {
            grade.Update();
            List<Grade> liste = Grade.GetAll();
        request.setAttribute("grade", liste);
        request.getRequestDispatcher("insertionGrade.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GradeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
