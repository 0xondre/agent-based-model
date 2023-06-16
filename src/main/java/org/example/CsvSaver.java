package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class used to save files to csv
 */
public class CsvSaver {
    /**
     * CsvSaver class constructor
     * @throws FileNotFoundException
     */
    public CsvSaver() throws FileNotFoundException {

    }
    /**
     * File we save our data in
     */
    private File csvFile = new File("balance.csv");
    /**
     * PrintWriter object used to save data in file
     */
    private PrintWriter out = new PrintWriter(csvFile);
    /**
     * Method that saves data to file
     * @param citizens List of the citizens
     * @param i turn
     */
    public void saveToCsv(List<Citizen> citizens, int i){
        out.printf("\nTura %d\n", i);
        for(Citizen citizen : citizens){
            out.print(citizen.getSymbol() + ",");
            out.print(citizen.getBalance() + ",");

        }
    }
    /**
     * Closing csvFile
     */
    public void close(){
        out.close();
    }
}
