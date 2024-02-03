public class Main {
    public static void main(String[] args) {
        Database.setCredentials("root","12345", "localhost:3306", "test");
        Database.isConnected();
        Database.createTables();

        Statements state = new Statements();
        state.insertTest("Gosho", 14);
        state.printAll();
    }
}