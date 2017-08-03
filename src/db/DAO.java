package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private Connection connection = null;

    public AccountDAO() {

        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection(
                    "jdbc:mysql://mydbinstance.c3xdqfzq9yis.us-east-1.rds.amazonaws.com:3306/goobers",
                    "root",
                    "goobers123");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<String> getTableNameList() {

        List<String> nameList = new ArrayList<String>();

        try {
            PreparedStatement stmt = connection.prepareStatement("select    *\n" +
                    "from      information_schema.tables\n" +
                    "where     table_type='view';");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nameList.add(rs.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return nameList;


    }

    public int getAccountBalance(String accountType) {



        return 0;
    }

    public void transferFunds(String fromAccount, String toAccount) {



    }

    /**
     *
     * @return Full or Liability
     */
    public String getInsuranceLevel() {


        return "";
    }

    public void addUser(UserProfile user) {

    }

    public void removeUser(UserProfile user) {


    }

    /**
     *
     * @param type Full or Liability
     */
    public void updateInsurance(String type) {

    }

    public void payBill(String name, int amount) {

    }




}
