package com.company.Dao;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class GeneralDao {

    public void searchAndDeleteBookWithID(String authorID, int arrLocation) throws IOException {
        File bookFile = new File("book.txt");
        File tempFile = new File("tempBook.txt");

        BufferedReader reader = new BufferedReader(new FileReader(bookFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Scanner scanner = new Scanner(System.in);
        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            String[] splitBookLine = currentLine.split(",");

            if(splitBookLine[arrLocation].equals(authorID)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(bookFile);
    }

    public void List(File name) throws IOException {
        File bookFile = new File("book.txt");
        File authFile = new File("author.txt");
        File pubFIle = new File("publisher.txt");

        BufferedReader reader = new BufferedReader(new FileReader(name));
        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            System.out.println(currentLine);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("  Press [Enter] to continue");
        scan.nextLine();
        reader.close();

    }

    public boolean doesExist(File filename,int authorID) throws IOException {
        File authFile = new File("author.txt");
        File pubFile = new File("publisher.txt");


        boolean isExist = false;
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            String[] splitBookLine = currentLine.split(",");

            if(parseInt(splitBookLine[0]) == (authorID)) {
                isExist = true;
                break;
            } else {
                isExist = false;
            }
        }

        reader.close();
        return isExist;

    }

}
