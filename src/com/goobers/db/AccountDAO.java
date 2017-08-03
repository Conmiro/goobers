package com.goobers.db;


import com.goobers.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private Connection connection = null;

    private static final int ACCOUNT_ID = 1;

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

    public void addUser(User user) {
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO goobers.User (UserName,PassPhrase,PIN,AccountID)\n" +
                            "VALUES (?,?,?,?);");

            stmt.setString(1,user.getUserName());
            stmt.setString(2, user.getPassPhrase() );
            stmt.setString(3, user.getPin());
            stmt.setInt(4,ACCOUNT_ID);

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUser(User user) {


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
