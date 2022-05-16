//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class testDB {
//    public static void main(String[] args) {
//        try (Connection con = DB.getConnection();
//                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stmt.executeQuery("SELECT * FROM username");) {
//            rs.next();
//           User a = new User("Amirreza", "password", "email", "mode");
//           User a1 = new User("ali", "password", "email", "mode");
//           User a2 = new User("reza", "password", "email", "mode");
//           a2.setUsername("frank");
//           a1.setEmail("Amir@mail.com");
//           a.setPassword("newPASS");
//           a.incStreak();
//           a2.incStreakFreeze();
//           System.out.println(a.getUsername());
//           System.out.println(a.getEmail());
//           System.out.println(a1.getEmail());
//           User a3 = new User("frank", "password");
//           System.out.println(a3.getUsername());
//        } catch (SQLException e) {
//            System.err.println(e);
//            e.printStackTrace();
//        }
//    }
//}
