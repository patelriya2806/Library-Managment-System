package com.example;

import java.util.Scanner;
import java.sql.*;

public class main {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        try {
            conn = DBconnection.getConnection();
            System.out.println("connected to mySQL");

            Book book = new Book(conn);

            System.out.println("   ===== library managment system =====   ");
            while (true) {
                System.out.println("Select the appropriate operation to perform :");
                System.out.println("1.Add the book \n2.Search the book \n3.Issue book\n4.Serach issued book\n5.Add a library Member\n6.Show the list of members \n7.exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        book.addBook();
                        break;
                    case 2:
                        book.searchBook();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println(" invalid choice ");
                }
                if (choice == 7) {
                    break;
                }
            }
        } catch(SQLException e){
            System.out.println("error : " + e.getMessage());
        }
    }
}
