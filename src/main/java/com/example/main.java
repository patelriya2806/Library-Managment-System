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
            if(conn == null){
                System.out.println("not connected to mysql");
            }
            Book book = new Book(conn);
            Member member = new Member(conn);

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
                        System.out.println("Are you a member of library : y/n");
                        String ans = sc.next();
                        if(ans.equalsIgnoreCase("n")){
                            System.out.println("please , enroll yourself FIRST");
                            break;
                        }
                        member.issueBook();
                        break;
                    case 4:
                        member.searchIssuedBooks();
                        break;
                    case 5:
                        member.addMember();
                        break;
                    case 6:
                        member.searchMember();
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
