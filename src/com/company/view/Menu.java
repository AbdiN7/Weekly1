package com.company.view;

import com.company.Controller.BookServices;
import com.company.Controller.PublisherServices;
import com.company.Controller.Services;

import java.io.IOException;
import java.util.Scanner;

public class Menu
{
    BookServices bookActions = new BookServices();
    PublisherServices publisherActions = new PublisherServices();
    Services action = new Services();
    public void runMenu ()
    {
        showHeader();
        showMenu();
        int choices = getInput();
        preformMenuAction(choices);

    }
    public void runBookMenu()
    {
        try{
            bookMenu();
            int choices = getInput();
            preformBookAction(choices);
        } catch (IOException e)
        {
            System.out.println("Error");
        }

    }
    public void runNewBookAuthMenu()
    {
        try{
            newBookAuthMenu();
            int choices = getInput();
            preformNewBookAuthAction(choices);
        } catch (IOException e)
        {
            System.out.println("Error");
        }

    }
    public void runNewBookPubMenu()
    {
        try{
            newBookPubMenu();
            int choices = getInput();
            preformNewBookPubAction(choices);
        } catch (IOException e)
        {
            System.out.println("Error");
        }

    }
    public void runAuthMenu()
    {
        try{
            authorMenu();
            int choices = getInput();
            preformAuthAction(choices);
        } catch (IOException e)
        {
            System.out.println("Error");
        }

    }
    public void runPubMenu()
    {
        try{
            pubMenu();
            int choices = getInput();
            preformPubAction(choices);
        } catch (IOException e)
        {
            System.out.println("Error");
        }

    }

    public void showHeader ()
    {
        System.out.println("_______________________________________");
        System.out.println("|                                     |");
        System.out.println("|              WELCOME  TO            |");
        System.out.println("|           BARNES AND MOBILE         |");
        System.out.println("|_____________________________________|");
    }
    public void showMenu ()
    {
        System.out.println("______________________________________");
        System.out.println("|                                    |");
        System.out.println("| 1) Go To Book Menu                 |");
        System.out.println("| 2) Go To Author Menu               |");
        System.out.println("| 3) Go To Publisher Menu            |");
        System.out.println("|                                    |");
        System.out.println("| 0) Exit The Application            |");
        System.out.println("|____________________________________|");

    }
    public int getInput()
    {
        Scanner kbInput = new Scanner(System.in);

        int choices = -1;
        while( choices < 0 || choices > 5)
        {
            try {

                System.out.println("\nEnter Your Selection Here: ");
                choices = Integer.parseInt(kbInput.nextLine());
            } catch (NumberFormatException e)
            {
                System.out.println("Not a valid selection. Please try again.");
            }
        }
        return choices;
    }
    public void bookMenu()
    {
        System.out.println("_____________________________________");
        System.out.println("|                                   |");
        System.out.println("| 1) Add Book                       |");
        System.out.println("| 2) Remove Book                    |");
        System.out.println("| 3) Change BOok                    |");
        System.out.println("| 4) List Books                     |");
        System.out.println("|                                   |");
        System.out.println("| 0) return to Main Menu            |");
        System.out.println("|___________________________________|");
    }
    public void authorMenu()
    {
        System.out.println("_____________________________________");
        System.out.println("|                                   |");
        System.out.println("| 1) Add Author                     |");
        System.out.println("| 2) Remove Author                  |");
        System.out.println("| 3) Change Author                  |");
        System.out.println("| 4) List Authors                   |");
        System.out.println("|                                   |");
        System.out.println("| 0) return to Main Menu            |");
        System.out.println("|___________________________________|");
    }
    public void pubMenu()
    {
        System.out.println("_____________________________________");
        System.out.println("|                                   |");
        System.out.println("| 1) Add Publisher                  |");
        System.out.println("| 2) Remove Publisher               |");
        System.out.println("| 3) Change Publisher               |");
        System.out.println("| 4) List Publisher                 |");
        System.out.println("|                                   |");
        System.out.println("| 0) return to Main Menu            |");
        System.out.println("|___________________________________|");
    }
    public void newBookAuthMenu()
    {
        System.out.println("Does this Book have an Existing Author? ");
        System.out.println(" ______________________________________");
        System.out.println("|   1)yes                              |");
        System.out.println("|   2)no                               |");
        System.out.println("|   3)List Authors                     |");
        System.out.println("|______________________________________|");
    }    public void newBookPubMenu()
    {
        System.out.println("Does this Book have an Existing Publisher? ");
        System.out.println(" __________________________________________");
        System.out.println("|   1)yes                                  |");
        System.out.println("|   2)no                                   |");
        System.out.println("|   3)List Publishers                      |");
        System.out.println("|__________________________________________|");
    }
    private void preformMenuAction(int choices)
    {
        switch (choices){
            case 0:
                System.exit(1);
                break;
            case 1:
                runBookMenu();
                break;
            case 2:
                runAuthMenu();
                break;
            case 3:
                runPubMenu();
                break;
            default:
                System.out.println("Unknown Error");
        }
    }
    private void preformBookAction(int choices) throws IOException
    {
        switch (choices){
            case 0:
                runMenu();
                break;
            case 1:
                runNewBookAuthMenu();
                runBookMenu();
                break;
            case 2:
                bookActions.removeBook();
                runBookMenu();
                break;
            case 3:
                bookActions.updateBook();
                runBookMenu();
                break;
            case 4:
                bookActions.listBooks();
                runBookMenu();
            default:
                System.out.println("Unknown Error");

        }
    }
    private  void preformNewBookAuthAction(int choices) throws IOException
    {
        switch (choices)
        {
            case 1:
                runNewBookPubMenu();
                break;
            case 2:
                bookActions.addBookNewAuthor();
                runNewBookPubMenu();
                break;
            case 3:
                action.listAuthor();
                System.out.println("------------------Authors------------------");
                runNewBookAuthMenu();
                break;
        }
    }
    private  void preformNewBookPubAction(int choices) throws IOException
    {
        switch (choices)
        {
            case 1:
                bookActions.addBookExistingAuthPub();
                break;
            case 2:
                bookActions.addBookNewPublisher();
                break;
            case 3:
                publisherActions.listPublisher();
                System.out.println("------------------Publishers------------------");
                runNewBookPubMenu();
                break;
        }
    }

    private void preformAuthAction(int choices) throws IOException {
        switch (choices){
            case 0:
                runMenu();
                break;
            case 1:
                action.addAuthor();
                runAuthMenu();
                break;
            case 2:
                action.removeAuthor();
                runAuthMenu();
                break;
            case 3:
                action.updateAuthor();
                runAuthMenu();
                break;
            case 4:
                action.listAuthor();
                runAuthMenu();
                break;
            default:
                System.out.println("Unknown Error");

        }
    } private void preformPubAction(int choices) throws IOException
    {
        switch (choices){
            case 0:
                runMenu();
                break;
            case 1:
                publisherActions.addPublisher();
                runPubMenu();
                break;
            case 2:
                publisherActions.removePublisher();
                runPubMenu();
                break;
            case 3:
                publisherActions.updatePublisher();
                runPubMenu();
                break;
            case 4:
                publisherActions.listPublisher();
                runPubMenu();
                break;
            default:
                System.out.println("Unknown Error");

        }
    }


}
