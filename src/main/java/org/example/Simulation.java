package org.example;

import java.io.FileNotFoundException;
import java.util.*;

public class Simulation {

    private static Board board;
    private List<Citizen> citizens;

        public Simulation() {
            board = new Board();
            citizens = new ArrayList<>();
        }

        public void addCitizen(Citizen citizen) {
            citizens.add(citizen);
        }
        public void removeCitizen(Citizen citizen) {
            citizens.remove(citizen);
        }

        public char[][] interactions(char[][] data){
            // helpful temp variables
            List<Citizen> removeList = new ArrayList<>();
            List<Citizen> addList = new ArrayList<>();
            List<Citizen> tempCitizen = new ArrayList<>();
            tempCitizen.add(new Normie(Options.BOARD_WIDTH+1,Options.BOARD_HEIGHT+1,1));

            // Update the board state
            for (Citizen citizen : citizens) {
                citizen.move();
                for(Citizen temp : tempCitizen)
                    if(citizen.getPosX()==temp.getPosX() && citizen.getPosY()==temp.getPosY())
                    {
                        switch (citizen.getSymbol()) {

                            // Normie interactions
                            case 'N' -> {
                                if (temp.getSymbol() == 'N' || temp.getSymbol() == 'P') {
                                    if(!citizen.haveMated()) {
                                        addList.add(board.generateCitizen(citizen.getPosX(), citizen.getPosY()));
                                        citizen.Mated();
                                    }
                                } else if (temp.getSymbol() == 'M') {
                                    removeList.add(citizen);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                                } else {
                                    int money = new Random().nextInt(15);
                                    citizen.setBalance(money * -1);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(money);
                                }
                            }

                            // Policeman interactions
                            case 'P' -> {
                                if (temp.getSymbol() == 'N' || temp.getSymbol() == 'P') {
                                    if(!citizen.haveMated()) {
                                        addList.add(board.generateCitizen(citizen.getPosX(), citizen.getPosY()));
                                        citizen.Mated();
                                    }
                                } else {
                                    removeList.add(temp);
                                    citizen.setBalance(new Random().nextInt(20));
                                }
                            }

                            // Thief interactions
                            case 'T' -> {
                                if (temp.getSymbol() == 'N') {
                                    int money = new Random().nextInt(15);
                                    citizen.setBalance(money);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(money * -1);
                                } else if (temp.getSymbol() == 'T') {
                                    if (new Random().nextInt(2) == 1) {
                                        removeList.add(temp);
                                        citizen.setBalance(new Random().nextInt(20));
                                    } else {
                                        removeList.add(citizen);
                                        citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                                    }
                                } else {
                                    removeList.add(citizen);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                                }
                            }

                            // Murderer interactions
                            case 'M' -> {
                                if (temp.getSymbol() == 'N' || temp.getSymbol() == 'T') {
                                    removeList.add(temp);
                                    citizen.setBalance(new Random().nextInt(20));
                                } else if (temp.getSymbol() == 'M') {
                                    if (new Random().nextInt(2) == 1) {
                                        removeList.add(temp);
                                        citizen.setBalance(new Random().nextInt(21));
                                    } else {
                                        removeList.add(citizen);
                                        citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(21));
                                    }
                                } else {
                                    removeList.add(citizen);
                                    citizens.get(tempCitizen.indexOf(temp)-1).setBalance(new Random().nextInt(20));
                                }
                            }
                        }
                    }
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
            return data;
        }
        // Saving data from each turn to csv file

        public void start() throws FileNotFoundException {
            int i=1;
            CsvSaver csvSaver = new CsvSaver();
            while (i<=Options.TURN_AMOUNT) {
                char[][] data = new char[Options.BOARD_WIDTH][Options.BOARD_HEIGHT];

                // Interactions in this turn
                data = interactions(data);

                // Save to csv
                csvSaver.saveToCsv(citizens,i);

                // Print the board state
                board.printBoard(data);
                System.out.println(citizens.size());
                System.out.println("Tura " + i);

                // Pause between turns
                    try {
                        Thread.sleep(Options.TURN_TIME);  // Time between turns
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                i++;
            }
            csvSaver.close();
        }
    public static void main(String[] args) throws FileNotFoundException {
        Simulation simulation = new Simulation();

        // Create a starting amount of citizens
        for (int i = 0; i < Options.STARTING_NUMBER_OF_CITIZENS; i++) {

            Citizen citizen = board.generateCitizen();

            simulation.addCitizen(citizen);
        }

        simulation.start();

    }
}

