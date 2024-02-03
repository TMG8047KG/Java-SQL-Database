import java.sql.*;

public class Statements extends Database{
    public void insertTest(String name, int age){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO test(name, age) VALUES(?,?)")){
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
            System.out.printf("Saved %s, age: %d%n", name, age);
        }catch (SQLException e){
            System.out.println("Insertion unsuccessful!");
        }
    }

    public void printAll() {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM test")){
            try(ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                while (rs.next()){
                    for(int i = 1; i <= columns; i++){
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
