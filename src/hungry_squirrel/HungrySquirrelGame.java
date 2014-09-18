package hungry_squirrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HungrySquirrelGame {
    public static void main(String[] args) {
        int error = Maze.create("Maze.txt");
        
        if (error != 0) {
            System.out.println("Error code: " + error);
            throw new IllegalArgumentException("Could not load maze file.");
        }
        
        Maze.display();
        //System.out.println("rows: " + Maze.getNumberOfRows() + 
        //        ", columns: " + Maze.getNumberOfColumns());
        
        Squirrel squirrel = new Squirrel();
        squirrel.create();
        Nuts nuts = new Nuts();
        nuts.create();
        PoisonousMushroom mushrooms = new PoisonousMushroom();
        mushrooms.create();
        System.out.println("Enter commands u,d,l,r to move Up, Down, Left, and Right respectively.");
        
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            char command;
            boolean endOfGame = false;
            int cell_points;
            
            while(!endOfGame) {
                Maze.display();
                System.out.print("Enter command: ");
                
                line = br.readLine();
                
                line = line.trim();
                
                if (line.length() != 1) {
                    System.out.println("Bad command! Enter commands u,d,l,r to move Up, Down, Left, and Right.");
                    continue;
                }
                
                command = line.toCharArray()[0];
                
                switch(command) {
                    case 'u':
                        squirrel.moveUp();
                        break;
                    case 'd':
                        squirrel.moveDown();
                        break;
                    case 'l':
                        squirrel.moveLeft();
                        break;
                    case 'r':
                        squirrel.moveRight();
                        break;
                    default:
                        System.out.println("Bad command! Enter commands u,d,l,r to move Up, Down, Left, and Right.");
                        continue;
                }
                
                if (squirrel.successfullyMoved) {
                    cell_points = nuts.isNuts(squirrel.oldValue);
                    if (cell_points != 0) { // it was a nut
                        squirrel.points += cell_points;
                        squirrel.totalNumberOfNutsCollected++;
                        if (squirrel.totalNumberOfNutsCollected == Nuts.TOTAL_NUTS) {
                            endOfGame = true;
                            Maze.display();
                            System.out.println("Squirrel successfully collected all the nuts. Total points " + squirrel.points + ".");
                            System.out.println("Thank you for playing this game.");
                        }
                        else {
                            System.out.println("!!! Squirrel got " + cell_points + 
                                " points (Total " + squirrel.points + " points) !!!");
                        }
                        
                    }
                    else { // it was not a nut
                        cell_points = mushrooms.isMushroom(squirrel.oldValue);
                        if (cell_points != 0) { // it's a mushroom
                            squirrel.points += cell_points;
                            if (squirrel.points < 0) {
                                endOfGame = true;
                                Maze.display();
                                System.out.println("Squirrel ate too many poisonous mushrooms. Total points " + squirrel.points + ".");
                                System.out.println("Thank you for playing this game.");
                            }
                            else {
                                System.out.println("!!! Squirrel got " + cell_points + 
                                " points (Total " + squirrel.points + " points) !!!");
                            }
                        }
                    }
                }
                
            }
        }
        catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }
}
