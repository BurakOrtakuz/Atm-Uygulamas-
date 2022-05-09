package Database;

public class DatabaseFactory {//Factory metod
    public static IDatabaseRepository databaseFactory(String databaseName){
        IDatabaseRepository databaseRepository = null;
        switch (databaseName.toLowerCase()){
            case "postgresql":
                databaseRepository= new PostgreSql("postgres","123456789");
                break;
        }
        return databaseRepository;
    }
}
