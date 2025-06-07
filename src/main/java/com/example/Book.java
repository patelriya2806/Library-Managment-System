package com.example;

import java.sql.*;
import java.util.*;

public class Book {
    private String bookName;
    private String author;
    private Connection conn = null;

    Book(Connection conn){
        this.conn = conn;
    }
    Scanner sc = new Scanner(System.in);

    public void addBook(){
        try {
            String query = "insert into books(bookName ,author) values(?,?)";
            System.out.print("Enter the book name : ");
            bookName = sc.nextLine();
            System.out.print("Enter the author name :");
            author = sc.nextLine();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,bookName);
            ps.setString(2,author);
            int i = ps.executeUpdate();
            System.out.println("no of rows inserted = " + i);
        } catch (SQLException e){
            System.out.println("error : "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void searchBook(){
        boolean isfound = false;
        try{
            String query = "select * from books where LOWER(bookName) = LOWER(?)";
            System.out.print("enter the book name to search :");
            bookName = sc.nextLine();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,bookName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                isfound = true;
                int id = rs.getInt("id");
                String name = rs.getString("bookName");
                String author = rs.getString("author");
                System.out.println("book found at : "+ id + " " + name + " Written by : " + author);
            }
            if(isfound==false){
                System.out.println("book not found");
            }
        } catch (SQLException e){
            System.out.println("error : "+e.getMessage());
            e.printStackTrace();
        }
    }
}
