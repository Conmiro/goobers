package com.goobers.db;


import com.goobers.model.User;
import com.goobers.perms.Manage;
import com.goobers.perms.View;

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

    public User getUserFromPassphrase(String name, String passphrase) {
    	User theUser =  null;
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM USER WHERE UserName = ? AND Passphrase = ?");

            stmt.setString(1, name);
            stmt.setString(2, passphrase);

            ResultSet rs = stmt.executeQuery();
            
            String userName = rs.getString("UserName");
            String passPhrase = rs.getString("Passphrase");
            String pin = rs.getString("PIN");
            boolean canManage = rs.getBoolean("CanManage");
            boolean isOwner = false;
            if(pin != null) {
            	isOwner = true;
            }
            
//          create the user
            if(canManage) {
            	theUser = new User(userName, passPhrase, pin, new Manage(), isOwner);
            } else {
            	theUser = new User(userName, passPhrase, pin, new View(), isOwner);
            }
                        
        } catch (SQLException e) {
//        	return null;
            e.printStackTrace();
        }
		return theUser;
    	
    }
    
    public User getUserFromPin(String name, String passphrase) {
		return null;
    
    }


}
