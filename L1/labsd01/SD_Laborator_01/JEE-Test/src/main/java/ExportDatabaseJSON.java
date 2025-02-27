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

public class ExportDatabaseJSON extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        if(keyword == null)
            keyword = "";

        Gson gson = new Gson();
        List<StudentBean> stud = new ArrayList<>();
        Connection connection = null;
        FileWriter writer = new FileWriter("/home/iedi/Documents/Sisteme_Distribuite/L1/labsd01/SD_Laborator_01/JEE-Test/studenti.json");
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM studenti");

            while (rs.next()) {

                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                int varsta = rs.getInt("varsta");

                if(nume != null && prenume != null) {
                    if(keyword.equals(""))
                    {
                        stud.add(new StudentBean(nume, prenume, varsta));
                    }
                    else if (nume.contains(keyword) || prenume.contains(keyword) || Integer.toString(varsta).contains(keyword)) {
                        stud.add(new StudentBean(nume, prenume, varsta));
                    }
                }
            }
            gson.toJson(stud, writer);
            writer.close();
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

        request.getRequestDispatcher("./read-student-db").forward(request, response);
    }
}
