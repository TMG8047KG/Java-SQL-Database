import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static String username;
    private static String password;
    private static String host;
    private static String database;

    public static void setCredentials(String user, String password, String host, String database){
        Database.username = user;
        Database.password = password;
        Database.host = host;
        Database.database = database;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection;
//        Examples:
//        1. jdbc:mysql://localhost:3306/test?useSSL=true
//        2. jdbc:mysql://localhost:3306/test?user=gosho&password=12345
//        1. connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=true", username, password);
//        2. connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user="+username+"&password="+password);

//        jdbc:mysql://root:12345@localhost:3306/test

        connection = DriverManager.getConnection("jdbc:mysql://"+username+":"+password+"@"+host+"/"+database);
        return connection;
    }

    public static void isConnected(){
        try {
            DriverManager.getConnection("jdbc:mysql://" + username + ":" + password + "@" + host +"/"+ database);
            System.out.println("Database Connected!");
        }catch (SQLException e){
            System.out.println("Database Not Connected!");
            e.printStackTrace();
        }
    }

    public static void createTables(){
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id), name varchar(255), age int)");
        }catch (SQLException e){
//            e.printStackTrace();
            System.out.printf("Error: %s", e.getMessage());
        }
    }
}
