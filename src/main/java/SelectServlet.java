import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "SelectServlet", urlPatterns = "/select")
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try ( //try nuzhen dlja zakritija connectiona
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/company");
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("SELECT * FROM POSITIONS ");

        ){
        while (rs.next()){
            long id = rs.getLong("id");
            String name = rs.getString("name");
            System.out.println("id: " + id + "; name: " + name);
        }

    } catch (SQLException e){
        System.out.println("Something went wrong!");
    }
}}
