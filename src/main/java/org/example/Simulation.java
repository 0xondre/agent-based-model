package org.example;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Simulation class, used as main class of this simulation
 */
public class Simulation {
    /**
     * main board object, whole simulation is taking place on this board
     */
    private static Board board;
    /**
     * list of citizens
     */
    private List<Citizen> citizens;
    /**
     * simulation class constructor
     */
    public Simulation() {
        board = new Board();
        citizens = new ArrayList<>();
    }
    /**
     * add citizen to citizens list
     */
    public void addCitizen(Citizen citizen) {
        citizens.add(citizen);
    }
    /**
     * remove citizen from citizens list
     */
    public void removeCitizen(Citizen citizen) {
        citizens.remove(citizen);
    }
    /**
     * interactions between objects
     */
    public char[][] interactions(char[][] data){
        // list used to store info about objects that should be removed from board
        List<Citizen> removeList = new ArrayList<>();
        // list used to store info about objects that should be added to the board
        List<Citizen> addList = new ArrayList<>();
        // useful temp list storing citizens that have already moved
        List<Citizen> tempCitizen = new ArrayList<>();
        // add one test objects placed out of the board
        tempCitizen.add(new Normie(Options.BOARD_WIDTH+1,Options.BOARD_HEIGHT+1,1));

        // update the board state
        for (Citizen citizen : citizens) {
            // move method from Citizen class
            citizen.move();
            for(Citizen temp : tempCitizen)
                if(citizen.getPosX()==temp.getPosX() && citizen.getPosY()==temp.getPosY())
                {
                    switch (citizen.getSymbol()) {

                        // normie interactions
                        case 'N' -> {
                            // interactions between normie and normie OR normie and policeman
                            if (temp.getSymbol() == 'N' || temp.getSymbol() == 'P') {
                                if(!citizen.haveMated()) {
                                    addList.add(board.generateCitizen(citizen.getPosX(), citizen.getPosY()));
                                    citizen.Mated();
                                }
                            }// interactions between normie and murderer
                            else if (temp.getSymbol() == 'M') {
                                removeList.add(citizen);
                                citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                            }// interactions between normie and thief
                            else {
                                int money = new Random().nextInt(15);
                                citizen.setBalance(money * -1);
                                citizens.get(tempCitizen.indexOf(temp)-1).setBalance(money);
                            }
                        }

                        // policeman interactions
                        case 'P' -> {
                            // interactions between policeman and normie OR policeman and policeman
                            if (temp.getSymbol() == 'N' || temp.getSymbol() == 'P') {
                                if(!citizen.haveMated()) {
                                    addList.add(board.generateCitizen(citizen.getPosX(), citizen.getPosY()));
                                    citizen.Mated();
                                }
                            }// interactions between policeman and thief OR policeman and murderer
                            else {
                                removeList.add(temp);
                                citizen.setBalance(new Random().nextInt(20));
                            }
                        }

                        // Thief interactions
                        case 'T' -> {
                            // interactions between thief and normie
                            if (temp.getSymbol() == 'N') {
                                int money = new Random().nextInt(15);
                                citizen.setBalance(money);
                                citizens.get(tempCitizen.indexOf(temp)-1).setBalance(money * -1);
                            }// interactions between thief and thief
                            else if (temp.getSymbol() == 'T') {
                                if (new Random().nextInt(2) == 1) {
                                    removeList.add(temp);
                                    citizen.setBalance(new Random().nextInt(20));
                                } else {
                                    removeList.add(citizen);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                                }
                            } // interactions between thief and murder OR thief and policeman
                            else {
                                removeList.add(citizen);
                                citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                            }
                        }

                        // murderer interactions
                        case 'M' -> {
                            // interactions between murder and normie OR murderer and thief
                            if (temp.getSymbol() == 'N' || temp.getSymbol() == 'T') {
                                removeList.add(temp);
                                citizen.setBalance(new Random().nextInt(20));
                            }// interactions between murder and murderer
                            else if (temp.getSymbol() == 'M') {
                                if (new Random().nextInt(2) == 1) {
                                    removeList.add(temp);
                                    citizen.setBalance(new Random().nextInt(21));
                                } else {
                                    removeList.add(citizen);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(21));
                                }
                            }// interactions between murder and policeman
                            else {
                                removeList.add(citizen);
                                citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                            }
                        }
                    }
                }
            // if citizen on removeList go to next loop iteration
            if(removeList.contains(citizen)){
                continue;
            }
            // sets citizen cords on the map
            data[citizen.getPosX()][citizen.getPosY()] = citizen.getSymbol();
            tempCitizen.add(citizen);

        }
        // deletes eliminated citizens
        for(Citizen citizen : removeList){
            removeCitizen(citizen);
        }

        // adds generated citizens
        for(Citizen citizen : addList){
            addCitizen(citizen);
        }
        // return board state
        return data;
    }
    /**
     * method that starts whole simulation
     */
    public void start() throws FileNotFoundException {
        int i=1;
        // CsvSaver object used to manipulate csv file
        CsvSaver csvSaver = new CsvSaver();
        while (i<=Options.TURN_AMOUNT) {
            char[][] data = new char[Options.BOARD_WIDTH][Options.BOARD_HEIGHT];

            // Interactions in this turn
            data = interactions(data);

            // Save to csv
            csvSaver.saveToCsv(citizens,i);

            // Print the board state
            board.printBoard(data);
            System.out.println("Ilosc obiektów na mapie " + citizens.size());
            System.out.println("Tura " + i);

            // Pause between turns
                try {
                    Thread.sleep(Options.TURN_TIME);  // Time between turns
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            i++;
        }
        // close file.csv
        csvSaver.close();
    }
    /**
     * main class
     */
    public static void main(String[] args) throws FileNotFoundException {
        Simulation simulation = new Simulation();

        // Create a starting amount of citizens
        for (int i = 0; i < Options.STARTING_NUMBER_OF_CITIZENS; i++) {
            Citizen citizen = board.generateCitizen();
            simulation.addCitizen(citizen);
        }

        // start the simulation
        simulation.start();

    }
}

