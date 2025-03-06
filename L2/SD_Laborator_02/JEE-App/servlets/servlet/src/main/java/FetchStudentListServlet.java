import ejb.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FetchStudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String varstaString = request.getParameter("varsta");
        int varstaInt = -1;
        try
        {
            varstaInt = Integer.parseInt(varstaString);
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

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Lista studenti</h2>");
        responseText.append("<table border='1'><thead><tr><th>ID</th><th>Nume</th><th>Prenume</th><th>Varsta</th></thead>");
        responseText.append("<tbody>");

        String jpql = "SELECT s FROM StudentEntity s WHERE 1=1";

        if (nume != null && !nume.isEmpty()) {
            jpql += " AND s.nume LIKE :nume";
        }
        if (prenume != null && !prenume.isEmpty()) {
            jpql += " AND s.prenume LIKE :prenume";
        }
        if (varstaInt > 0) {
            jpql += " AND s.varsta = :varsta";
        }
        if (id > 0) {
            jpql += " AND s.id = :id";
        }

        TypedQuery<StudentEntity> query = em.createQuery(jpql, StudentEntity.class);

        if (nume != null && !nume.isEmpty()) {
            query.setParameter("nume", "%" + nume + "%");
        }
        if (prenume != null && !prenume.isEmpty()) {
            query.setParameter("prenume", "%" + prenume + "%");
        }
        if (varstaInt > 0) {
            query.setParameter("varsta", varstaInt);
        }
        if (id > 0) {
            query.setParameter("id", id);
        }

        List<StudentEntity> results = query.getResultList();
        for (StudentEntity student : results) {
            // se creeaza cate un rand de tabel HTML pentru fiecare student gasit
            responseText.append("<tr><td>" + student.getId() + "</td><td>" +
                    student.getNume() + "</td><td>" + student.getPrenume() +
                    "</td><td>" + student.getVarsta() + "<a href='./search_and_update.jsp'></a></td></tr>");
        }

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
