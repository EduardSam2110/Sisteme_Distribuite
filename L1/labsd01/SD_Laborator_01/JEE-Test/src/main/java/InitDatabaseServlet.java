import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class InitDatabaseServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC Driver loaded!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("SQLite JDBC Driver not found!");
        }

        try {
            System.out.println("Initializing database...");

            // Se creează o conexiune la baza de date (se creează dacă nu există)
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            System.out.println("Database connected!");

            try (Statement statement = connection.createStatement()) {
                statement.setQueryTimeout(30);

                // Crearea tabelei (dacă nu există deja)
                statement.executeUpdate("DROP TABLE IF EXISTS person");
                statement.executeUpdate("CREATE TABLE person (id INTEGER PRIMARY KEY, name TEXT)");

                // Inserarea unor date
                statement.executeUpdate("INSERT INTO person VALUES (1, 'leo')");
                statement.executeUpdate("INSERT INTO person VALUES (2, 'yui')");

                // Citirea și afișarea datelor
                try (ResultSet rs = statement.executeQuery("SELECT * FROM person")) {
                    while (rs.next()) {
                        System.out.println("Read from DB: ID = " + rs.getInt("id") + ", Name = " + rs.getString("name"));
                    }
                }
            }

            httpServletResponse.getWriter().print("Database initialized successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            httpServletResponse.getWriter().print("SQL Exception: " + e.getMessage());
        } finally {
            // Închidem conexiunea la final pentru a evita problemele de memorie
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/*
Tasks:
- Fa o pagina de ReadStudent cu baza de date, in care se va apela prima data functia de initializare,
apoi userul va completa formularul si va apasa pe Trimite si sa afiseze un mesaj gen "Student inscris",
va fi un buton cu Afiseaza studentii inscrisi si va redirectiona la ProcessStudents
- Fa o pagina de ProcessStudent cu baza de date, in care se vor afisa toti studentii din baza de date
 */
