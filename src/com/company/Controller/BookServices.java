package com.company.Controller;

import com.company.Dao.GeneralDao;
import com.company.model.Book;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BookServices {
    private File bookFile = new File("book.txt");
    private File authFile = new File ("author.txt");
    private File pubFile = new File("publisher.txt");

    private GeneralDao bookDao = new GeneralDao();
    private AuthorServices authActions = new AuthorServices();
    private PublisherServices pubAction = new PublisherServices();
    private RandomID randomBookId = new RandomID();

    private Book book = new Book();

    public void addBook() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the book you want to add");
        book.setBookName(scanner.nextLine());
        book.setBookId(randomBookId.getRandomID(bookFile));
        book.setAuthorId(authActions.AUthorsID);
        book.setPublisherId(pubAction.PublisherID);

        try {
            PrintWriter bookOutput = new PrintWriter(new FileWriter(bookFile, true));
            bookOutput.println(
                    book.getBookId() + "," +
                            book.getBookName() + "," +
                            book.getAuthorId() + ","+
                            book.getPublisherId());
            bookOutput.flush();
            bookOutput.close();

        }
        catch (IOException ex)
        {
            System.out.println("ERROR: " + ex);
        }

    }
    public  void addBookNewPublisher() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------New Publisher Must be added first--------------------------------");
        System.out.println("----------------------    Press [Enter] to Continue   --------------------------------");

        scanner.nextLine();
        pubAction.addPublisher();
        addBook();
    }
    public  void addBookNewAuthor() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------New Authors Should be added First--------------------------------");
        System.out.println("----------------------     Press [Enter] to Continue   --------------------------------");
        scanner.nextLine();
        authActions.addAuthor();
    }

    public void addBookExistingAuthPub() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the book you want to add");
        book.setBookName(scanner.nextLine());

        book.setBookId(randomBookId.getRandomID(bookFile));

        System.out.println("Enter the Author ID of the book: " + book.getBookName());
        try{
            book.setAuthorId(scanner.nextInt());
            if (!bookDao.doesExist(authFile, book.getAuthorId()))
            {
                System.out.println("----------------------------------------");
                System.out.println("     Could not Add Book Using this ID");
                System.out.println("----------------------------------------");
                return;
            }
        } catch (InputMismatchException ime)
        {
            System.out.println("Cannot not use this input as Author ID");
            return;
        }


        scanner.nextLine();

        System.out.println("Enter the Publisher ID of the book: " + book.getBookName());
        try{
            book.setPublisherId(scanner.nextInt());
            if (!bookDao.doesExist(pubFile, book.getPublisherId()))
            {
                System.out.println("----------------------------------------");
                System.out.println("     Could not Add Book Using this ID");
                System.out.println("----------------------------------------");
                return;

            }
        } catch (InputMismatchException ime)
        {
            System.out.println("Cannot not use this input as Publisher ID ");
        }

        scanner.nextLine();

        try {
            PrintWriter bookOutput = new PrintWriter(new FileWriter(bookFile, true));
            bookOutput.println(
                    book.getBookId() + "," +
                            book.getBookName() + "," +
                            book.getAuthorId() + ","+
                            book.getPublisherId());
            bookOutput.flush();
            bookOutput.close();

        }
        catch (IOException ex)
        {
            System.out.println("ERROR: " + ex);
        }

    }

    public void removeBook() throws IOException {
        File tempFile = new File("tempBook.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        BufferedReader reader = new BufferedReader(new FileReader(bookFile));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the ID of the book you want to delete");
        String deleteID = scanner.nextLine();

        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            String[] splitBookLine = currentLine.split(",");

            if(splitBookLine[2].equals(deleteID)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(bookFile);

    }

    public void updateBook() throws IOException {
        File tempFile = new File("tempBook.txt");

        BufferedReader reader = new BufferedReader(new FileReader(bookFile));
        FileWriter fileWriter = new FileWriter(tempFile,true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(writer);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your Book's ID");
        String changeBookID = scanner.nextLine();
        System.out.println("Write the new Name for this book");
        String newName = scanner.nextLine();


        String bookLine;
        while((bookLine = reader.readLine()) != null)
        {

            String[] splitArr = bookLine.split(",");

            if(splitArr[0].equals(changeBookID)) {
                printWriter.println(splitArr[0] + "," + newName);
                continue;
            }

            printWriter.println(bookLine);
            printWriter.flush();

        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(bookFile);
    }
    public void listBooks() throws IOException {
        bookDao.List(bookFile);
    }
}
