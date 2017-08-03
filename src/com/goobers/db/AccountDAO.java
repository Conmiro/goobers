package com.goobers.db;


import com.goobers.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private Connection connection = null;

    private static final int ACCOUNT_ID = 1;
    private static final String CAR_NAME = "Mercedes 2018";

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


    public float getAccountBalance(String accountType) {


        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT Balance FROM goobers.Bank WHERE type = (?) and Account_ID = ?;");
            stmt.setString(1, accountType);
            stmt.setInt(2, ACCOUNT_ID);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getFloat("Balance");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }



    }

    public void setAccountBalance(String accountType, Float balance) {


        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE goobers.Bank SET Balance=? WHERE Type =? and Account_ID=?;\n");
            stmt.setFloat(1,balance);
            stmt.setString(2,accountType);
            stmt.setInt(3, ACCOUNT_ID);

            stmt.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Full or Liability
     */
    public String getInsuranceType() {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT PolicyType from goobers.Insurance where Insurance_AccountID = ? and Vehicle = ?;");
            stmt.setInt(1, ACCOUNT_ID);
            stmt.setString(2, CAR_NAME);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getString("PolicyType");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void setInsuranceType(String type) {

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE goobers.Insurance SET PolicyType=? WHERE Vehicle = ? and Account_ID=?;\n");
            stmt.setString(1,type);
            stmt.setString(2, CAR_NAME);
            stmt.setInt(3, ACCOUNT_ID);

            stmt.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }

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

        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM `goobers`.`User` WHERE UserName=?;\n");
            stmt.setString(1, user.getUserName());

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public float getBillBalance(String billName) {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT Amount\n" +
                    "FROM Account_Bill\n" +
                    "INNER JOIN Bill ON Account_Bill.Account_Bill_BillID = Bill.BillID\n" +
                    "WHERE BillName = ? and Account_Bill.Account_Bill_AccountID = ?;");
            stmt.setString(1, billName);
            stmt.setInt(2, ACCOUNT_ID);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getFloat("Amount");

        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }



    }

    public void setBillBalance(String name, float amount) {

        try {
            PreparedStatement stmt = connection.prepareStatement("Update Account_Bill\n" +
                    "Inner Join Bill on Account_Bill.Account_Bill_BillID = Bill.BillID\n" +
                    "Set Amount = ? \n" +
                    "Where Bill.BillName = ? and Account_Bill.Account_Bill_AccountID = ?;");

            stmt.setFloat(1, amount);
            stmt.setString(2, name);
            stmt.setInt(3, ACCOUNT_ID);

            stmt.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
