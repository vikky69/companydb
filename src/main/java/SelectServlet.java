import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;

@WebServlet(name = "SelectServlet", urlPatterns = "/select")
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName") ;// berjom to, 4to poljzovatelj vvel



try //try nuzhen dlja zakritija connectiona

        (Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/company");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM PERSONS WHERE FULL_NAME =?"
        ); // zapominaet vvedennies dannij - ?


         /**
          *
          * Statement stmt = connection.createStatement();
          * ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONS WHERE FULL_NAME LIKE '%" + fullName + "%'");
         */
      PrintWriter out = response.getWriter()
) {
stmt.setString(1, fullName); // baza dannix budet vosprinematj ? kak tekst i ne v koem sluchae kak sql. Eto isklju4aet sql injections
    ResultSet rs = stmt.executeQuery(); //ego sjuda vstavili, kogda napisali PreparedStatement

    out.format("| %5s | %-30s | %15s | %10s | %10s |\n", "ID", "NAME", "SALARY", "POSITION", "DEPARTMENT");
    while (rs.next()) {
        long id = rs.getLong("ID");
        String name = rs.getString("FULL_NAME");
        long positionId = rs.getLong("POSITION_ID");
        long departmentId = rs.getLong("DEPARTMENT_ID");
        BigDecimal salary = rs.getBigDecimal("SALARY");
        out.format("| %5d | %-30s | %15.2f | %10d | %10d |\n", id, name, salary, positionId, departmentId);
    }

    } catch (SQLException e){
    e.printStackTrace();
        System.out.println("Something went wrong!");
    }
}}
