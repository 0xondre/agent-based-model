package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvSaver {
    public CsvSaver() throws FileNotFoundException {

    }
    File csvFile = new File("balance.csv");
    PrintWriter out = new PrintWriter(csvFile);
    public void saveToCsv(List<Citizen> citizens, int i){
        out.printf("\nTura %d\n", i);
        for(Citizen citizen : citizens){
            out.print(citizen.getSymbol() + ",");
            out.print(citizen.getBalance() + ",");

        }
    }
    public void close(){
        out.close();
    }
}
