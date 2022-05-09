package Database;

import java.sql.*;

public class PostgreSql implements IDatabaseRepository {
    private Connection c = null;
    private String username;
    private String password;

    PostgreSql(String username,String password){
        this.password=password;
        this.username=username;
    }
    @Override
    public void openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ObjectUserAndPassword",
                            username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public boolean verifyUser(String usersUsername, String usersPassword) {
        Statement stmt;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"UsernameAndPassword\";" );
            while ( rs.next() ) {
                String  username = rs.getString("username");
                String password  = rs.getString("password");
                if(usersUsername.equals(username)&& usersPassword.equals(password) ){
                    return true;
                }
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return false;
    }


    @Override
    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
