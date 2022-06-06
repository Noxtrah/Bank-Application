import java.sql.*;

public class DatabaseService {
    String url = "jdbc:mysql://localhost:3306/bankdb";
    String user = "bank";
    String password = "securepassword";

    private Connection connect(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex){
            connection = null;
        }
        return connection;
    }

    //Create (Add Account)
    int AddAccount(String firstName, String lastName, String ssn, AccountType accountType, Double balance){
        int userId = -1, accountId = -1;
        Connection connection = connect();
        try {
            connection.setAutoCommit(false);
            //Add User
            String addUserSql = "insert into users(FirstName, LastName, SSN) values (?,?,?)";
            PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
            addUser.setString(1,firstName);
            addUser.setString(2,lastName);
            addUser.setString(3,ssn);
            addUser.executeUpdate();
            ResultSet addUserResults = addUser.getGeneratedKeys();
            if(addUserResults.next()){
                userId = addUserResults.getInt(1);
            }
            //Add Account
            String addAccountSql = "insert into accounts(Type, Balance) values (?,?)";
            PreparedStatement addAccount = connection.prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
            addAccount.setString(1,accountType.name());
            addAccount.setDouble(2,balance);
            addAccount.executeUpdate();
            ResultSet addAccountResults = addAccount.getGeneratedKeys();
            if(addAccountResults.next()){
                accountId = addAccountResults.getInt(1);
            }
            //Link User to Account
            if(userId > 0 && accountId > 0){
                String linkAccountSql = "insert into mappings(UserId, AccountId) values (?,?)";
                PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql);
                linkAccount.setInt(1,userId);
                linkAccount.setInt(2,accountId);
                linkAccount.executeUpdate();
                connection.commit();
            }
            else{
                connection.rollback();
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("An error has occured: " + ex.getMessage());
        }
        return accountId;
    }
    //Read
    //Update
    //Delete
}
