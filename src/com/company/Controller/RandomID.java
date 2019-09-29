package com.company.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class RandomID {

    public int getRandomID(File name) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(name));
        String currentLine;

        Integer largestID = 1;
        while ((currentLine = reader.readLine()) != null)
        {
            String[] splitCurrentLine = currentLine.split(",");
            Integer currentID = parseInt(splitCurrentLine[0]);
            largestID = Math.max(largestID, currentID);

        }


        Integer newId = largestID + 1;
        return newId;
    }
}
