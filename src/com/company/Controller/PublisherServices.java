package com.company.Controller;

import com.company.Dao.GeneralDao;
import com.company.model.Publisher;

import java.io.*;
import java.util.*;

public class PublisherServices {
    private File pubFile = new File("publisher.txt");
    private RandomID randomPublisherID = new RandomID();
    GeneralDao publisherDao = new GeneralDao();
    private Publisher publisher = new Publisher();
    List<String> publisherList = new ArrayList<>();

    Integer PublisherID;
    public void addPublisher() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the Name of the Publisher you wish to add?");
        publisher.setPubName(scanner.nextLine());
        System.out.println("What is the Address of " + publisher.getPubName());
        publisher.setPubAddr(scanner.nextLine());

        publisher.setPubId(randomPublisherID.getRandomID(pubFile));
        PublisherID = publisher.getPubId();


        try {
            PrintWriter publisherOutput = new PrintWriter(new FileWriter(pubFile, true));
            publisherOutput.println(publisher.getPubId() + "," + publisher.getPubName() + "," + publisher.getPubAddr()) ;
            publisherOutput.flush();
            publisherOutput.close();

        }
        catch (IOException ex)
        {
            System.out.println("ERROR: " + ex);
        }
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("---------- You Hav Successfully Created a New Author: [" + publisher.getPubName() + "] ----------");
        System.out.println("--------------  [" +publisher.getPubName() + "'s] ID is [" + publisher.getPubId()+ "] ---------------");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void removePublisher() throws IOException {
        File tempFile = new File("tempPub.txt");

        BufferedReader reader = new BufferedReader(new FileReader(pubFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the ID of the Publisher you want to delete");
        String deleteID = scanner.nextLine();
        publisherDao.searchAndDeleteBookWithID(deleteID,3);
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
        boolean successful = tempFile.renameTo(pubFile);
        return;

    }

    public void updatePublisher() throws IOException {
        File tempFile = new File("tempPub.txt");

        BufferedReader reader = new BufferedReader(new FileReader(pubFile));
        FileWriter fileWriter = new FileWriter(tempFile,true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(writer);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your Publisher's ID");
        String publisherId = scanner.nextLine();
        System.out.println("Write the new name for this Publisher");
        String newName = scanner.nextLine();


        String publisherLine;
        while((publisherLine = reader.readLine()) != null)
        {

            String[] splitArr = publisherLine.split(",");

            if(splitArr[0].equals(publisherId)) {
                printWriter.println(splitArr[0] + "," + newName  + ","+ splitArr[2]);
                continue;
            }

            printWriter.println(publisherLine);
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(pubFile);
        return;
    }
    public void listPublisher () throws IOException {
        publisherDao.List(pubFile);
    }
}
