import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class ProcessStudentDatabaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));

        Connection connection = null;
        String message;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO studenti(nume, prenume, varsta) VALUES (?, ?, ?)");
            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setInt(3, varsta);
            statement.executeUpdate();

            message = "Student inscris";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Eroare la inscriere: " + e.getMessage();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("formular-db.jsp").forward(request, response);
    }
}
