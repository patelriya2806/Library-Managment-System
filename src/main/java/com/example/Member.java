package com.example;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class Member {
    private String name;
    private long contactNo;
    private Connection conn=null;

    Member(Connection conn){
        this.conn=conn;
    }
    Scanner sc = new Scanner(System.in);

    public void issueBook(){
        try{
            String query1 = "select * from members where name = ? and contactNo = ?";
            String query2 = "insert into issueBook(name,contactNo,bookName) values(?,?,?)";

            System.out.print("Enter your name : ");
            name = sc.nextLine();
            System.out.print("enter your contact number : ");
            contactNo = sc.nextLong();
            sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(query1);
            ps.setString(1, name);
            ps.setLong(2, contactNo);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.print("Enter the book name to issue : ");
                String bookName = sc.nextLine();
                String query3 = "select * from books where bookName = ?";
                PreparedStatement ps2 = conn.prepareStatement(query3);
                ps2.setString(1,bookName);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()){
                    PreparedStatement pstmt = conn.prepareStatement(query2);
                    pstmt.setString(1, name);
                    pstmt.setLong(2, contactNo);
                    pstmt.setString(3, bookName);
                    int row = pstmt.executeUpdate();
                    System.out.println("Book issued Successfully");
                }
                else {
                    System.out.println("Book may not available");
                }
            }
            else {
                System.out.println("user data not found , register yourself first");
            }
        } catch (SQLException e){
            System.out.println("error : "+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void searchIssuedBooks(){
        try {
            String query1 = "select * from issueBook";
            String query2 = "select * from issueBook where bookName = ?";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getLong(2) +  " " + rs.getString(3));
            }
            System.out.println("Do you want to search any specific book data ? (y/n)");
            String ans = sc.next();
            sc.nextLine();
            if(ans.equalsIgnoreCase("y")){
                System.out.print("Enter the book name : ");
                String bookName = sc.nextLine();

                PreparedStatement ps2 = conn.prepareStatement(query2);
                ps2.setString(1,bookName);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    System.out.println(rs2.getString(1) + " " + rs2.getLong(2) +  " " + rs2.getString(3));
                }
            }
        }
        catch (SQLException e){
            System.out.println("error : "+ e.getMessage());
            e.printStackTrace();
        }
    }


}
