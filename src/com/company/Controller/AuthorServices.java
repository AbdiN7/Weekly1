package com.company.Controller;


import com.company.Dao.GeneralDao;
import com.company.model.Author;

import java.io.*;
import java.util.*;

public class AuthorServices {
    private File authFile = new File("author.txt");
    private GeneralDao authorDao = new GeneralDao();
    private RandomID randomAuthorID = new RandomID();
    private Author newAuth = new Author();


    public Integer AUthorsID;

    public void addAuthor() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your Author's name");
        newAuth.setAuthorName(scanner.nextLine());
        newAuth.setAuthorId(randomAuthorID.getRandomID(authFile));
        AUthorsID = newAuth.getAuthorId();

        try {
            PrintWriter authOut = new PrintWriter(new FileWriter(authFile, true));
            authOut.println(newAuth.getAuthorId() + "," + newAuth.getAuthorName());
            authOut.flush();
            authOut.close();
        }
        catch (IOException ex)
        {
            System.out.println("ERROR: " + ex);
        }
        System.out.println("---------------------------------------------------------------------------------- ");
        System.out.println("---------- You Hav Successfully Created a New Author: [" + newAuth.getAuthorName()+ "] ----------");
        System.out.println("------------------  ["+newAuth.getAuthorName() + "'s] ID is [" + newAuth.getAuthorId() + "]  ------------------") ;
        System.out.println("----------------------------------------------------------------------------------");
    }

    ////// REMOVING ///////
    ////// REMOVING ///////
    ////// REMOVING ///////
    public void removeAuthor() throws IOException {
        File tempFile = new File("tempAuthor.txt");

        BufferedReader reader = new BufferedReader(new FileReader(authFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your Author's ID");
        String deleteID = scanner.nextLine();
        authorDao.searchAndDeleteBookWithID(deleteID,2);
        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            String[] splitArr = currentLine.split(",");

            if(splitArr[0].equals(deleteID)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(authFile);
        return;
    }
    //////// UPDATE ///////
    //////// UPDATE ///////
    //////// UPDATE ///////
    public void updateAuthor() throws IOException {
        File tempFile = new File("tempAuthor.txt");

        BufferedReader reader = new BufferedReader(new FileReader(authFile));
        FileWriter fileWriter = new FileWriter(tempFile,true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        PrintWriter pwriter = new PrintWriter(writer);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your Author's ID");
        String authId = scanner.nextLine();
        System.out.println("Write the New Name for this author");
        String newName = scanner.nextLine();


        String authorLine;
        while((authorLine = reader.readLine()) != null)
        {

            String[] splitArr = authorLine.split(",");

            if(splitArr[0].equals(authId)) {
                pwriter.println(splitArr[0] + "," + newName);
                continue;
            }

            pwriter.println(authorLine);

        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(authFile);
        return;
    }

    //// LIST ////
    //// LIST ////
    //// LIST ////
    public void listAuthor () throws IOException {
        authorDao.List(authFile);
    }

}
