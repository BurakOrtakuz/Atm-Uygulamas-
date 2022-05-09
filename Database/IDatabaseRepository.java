package Database;

public interface IDatabaseRepository {
    void openConnection();
    boolean verifyUser(String usersUsername,String usersPassword);
    void closeConnection();
}
