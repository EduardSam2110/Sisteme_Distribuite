import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadStudentDatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> studenti = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM studenti");

            while (rs.next()) {
                String student = "ID: " + rs.getInt("id") + ", " +
                        "Nume: " + rs.getString("nume") + ", " +
                        "Prenume: " + rs.getString("prenume") + ", " +
                        "Varsta: " + rs.getInt("varsta");
                studenti.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("eroare", e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("studenti", studenti);
        request.getRequestDispatcher("students-database-view.jsp").forward(request, response);
    }
}
