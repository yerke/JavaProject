package hungry_squirrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Squirrel extends Entity {
    public static final char SQUIRREL_SYMBOL = '@';
    
    public boolean successfullyMoved;
    public char oldValue;
    
    public int points;
    public int totalNumberOfNutsCollected;
    
    public Squirrel() {
        symbol = SQUIRREL_SYMBOL;
        points = 0;
        totalNumberOfNutsCollected = 0;
    }
    
    @Override
    public void create() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            int x, y;
            boolean success = false;
            
            while(!success) {
                System.out.print("Enter the Squirrel position (x position,y position): ");
                line = br.readLine();
                
                String[] components = line.split(",");
                if (components.length != 2) {
                    System.out.println("Bad format!");
                    continue;
                }
                
                components[0] = components[0].trim();
                components[1] = components[1].trim();
                
                try {
                    x = Integer.parseInt(components[0]);
                    y = Integer.parseInt(components[1]);
                }
                catch (NumberFormatException e) {
                    System.out.println("Not a number!");
                    continue;
                }
                
                try {
                    if (!Maze.available(x-1, y-1)) {
                        System.out.println("Position not available. Try again!");
                        continue;
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                
                put(x-1, y-1);
                success = true;
                
                System.out.println("User input accepted.");
            }
        }
        catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }
    
    private void move(int newX, int newY) {
        this.successfullyMoved = false;
        if (Maze.isValidCoordinates(newX, newY) && Maze.available(newX, newY)) {
            this.clearCurrentPosition();
            this.successfullyMoved = true;
            this.oldValue = this.put(newX, newY);
        }
    }
    
    public void moveLeft() {
        move(xPosition,yPosition-1);
    }
    
    public void moveRight() {
        move(xPosition,yPosition+1);
    }
    
    public void moveUp() {
        move(xPosition-1,yPosition);
    }
    
    public void moveDown() {
        move(xPosition+1,yPosition);
    }
    
}
