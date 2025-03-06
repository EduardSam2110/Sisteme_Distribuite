import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
        String nume_nou = request.getParameter("nume");
        String prenume_nou = request.getParameter("prenume");
        String varstaString = request.getParameter("varsta");
        int varstaInt_nou = -1;
        try
        {
            varstaInt_nou = Integer.parseInt(varstaString);
        }
        catch (Exception e)
        {

        }

        String idString = request.getParameter("id");
        int id = -1;
        try
        {
            id = Integer.parseInt(idString);
        }
        catch (Exception e)
        {

        }

        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        if(id > 0 && (nume_nou != null || prenume_nou != null || varstaInt_nou > 0))
        {
            String jpql = "UPDATE StudentEntity s set";
            boolean otherFields = false;
            if (nume_nou != null && !nume_nou.isEmpty()) {
                jpql += " s.nume = :nume";
                otherFields = true;
            }
            if (prenume_nou != null && !prenume_nou.isEmpty()) {
                if(otherFields == true)
                    jpql += ",";
                jpql += " s.prenume = :prenume";
                otherFields = true;
            }
            if (varstaInt_nou > 0) {
                if(otherFields == true)
                    jpql += ",";
                jpql += " s.varsta = :varsta";
            }

            if(otherFields == true) {
                jpql += " WHERE s.id = :id";

                Query query = em.createQuery(jpql, StudentEntity.class);

                if (nume_nou != null && !nume_nou.isEmpty()) {
                    query.setParameter("nume", nume_nou);
                }
                if (prenume_nou != null && !prenume_nou.isEmpty()) {
                    query.setParameter("prenume", prenume_nou);
                }
                if (varstaInt_nou > 0) {
                    query.setParameter("varsta", varstaInt_nou);
                }

                query.setParameter("id", id);

                int rows = query.executeUpdate();
                em.getTransaction().commit();
            }

        }

        // inchidere EntityManager
        em.close();
        factory.close();

        request.getRequestDispatcher("search_and_update.jsp").forward(request, response);
    }
}
