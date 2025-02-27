import beans.StudentBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateStudentDatabaseServlet extends HttpServlet { // delete or modify a student
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nume = request.getParameter("numeStudent");
        String prenume = request.getParameter("prenumeStudent");
        int varsta = Integer.parseInt(request.getParameter("varstaStudent"));
        int id = Integer.parseInt(request.getParameter("idStudent"));


        List<String> studenti = new ArrayList<>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");

            PreparedStatement ps = connection.prepareStatement("UPDATE studenti SET nume = ?, prenume = ?, varsta = ? WHERE id = ?");

            ps.setString(1, nume);
            ps.setString(2, prenume);
            ps.setInt(3, varsta);
            ps.setInt(4, id);

            ps.executeUpdate();

            studenti.add("ID: " +  id + " " + nume + " " + prenume + " " + varsta);


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
