package com.example;

import com.mysql.cj.protocol.Resultset;

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

                PreparedStatement pstmt = conn.prepareStatement(query2);
                ps.setString(1, name);
                ps.setLong(2, contactNo);
                pstmt.setString(3, bookName);
                int row = pstmt.executeUpdate();
                System.out.println("Book issued Successfully");
            }
            else {
                System.out.println("user data not found , register yourself first");
            }
        } catch (SQLException e){
            System.out.println("error : "+ e.getMessage());
            e.printStackTrace();
        }
    }


}
