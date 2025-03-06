import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
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


        if(id > 0)
        {
            String jpql = "DELETE FROM StudentEntity WHERE id = :id";

            Query query = em.createQuery(jpql, StudentEntity.class);

            query.setParameter("id", id);

            int rows = query.executeUpdate();

            em.getTransaction().commit();
        }

        // inchidere EntityManager
        em.close();
        factory.close();

        request.getRequestDispatcher("search_and_update.jsp").forward(request, response);
    }
}
