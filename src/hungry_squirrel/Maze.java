package hungry_squirrel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    public static final int MAX_MAZE_ROW = 20;
    public static final int MAX_MAZE_COLUMN = 50;
    public static final char WALL_SYMBOL = '*';
    public static final char EMPTY_SYMBOL = ' ';
    
    private static char[][] maze;
    private static int rows, columns;
    
    // will read the file and initialize maze array.
    public static int create(String filename) {
        File file;
        FileReader fin;
        BufferedReader reader;
        String line;
        
        maze = new char[MAX_MAZE_ROW][MAX_MAZE_COLUMN];
        rows = 0;
        columns = 0;
        boolean firstTime = true;
        
        try {
            file = new File(filename);
            fin = new FileReader(file);
            reader = new BufferedReader(fin);
                        
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    // reached the end of the file?
                    continue;
                }
                

                if (firstTime) {
                    firstTime = false;
                    columns = line.length();
                }
                else {
                    if (line.length() != columns) {
                        throw new IllegalArgumentException("The number of columns is not consistent!");
                    }
                }
                
                maze[rows] = line.toCharArray();
                rows++;                
            }
            
            reader.close();
            fin.close();
        }
        catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
            return -1;
        }
        return 0;
    }
    
    // displays the maze structure and the containing entities on console.
    public static void display() {
        if (maze==null) {
            throw new IllegalArgumentException("Invoke Maze.create() method first!");
        }
        
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    
    public static boolean isValidCoordinates(int row, int col) {
        return !(row < 0 | row >= rows | col < 0 | col >= columns);
    }
    
    // return true if there is no wall. otherwise return false.
    public static boolean available(int row, int col) {
        if (!isValidCoordinates(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds!");
        }
        
        return maze[row][col] != WALL_SYMBOL;
    }
    
    public static boolean isEmpty(int row, int col) {
        if (!isValidCoordinates(row, col)) {
            return false;
        }
        
        return maze[row][col] == EMPTY_SYMBOL;
    }
    
    public static int getNumberOfRows() {
        return rows;
    }
    
    public static int getNumberOfColumns() {
        return columns;
    }
    
    public static char getMazeCell(int row, int col) {
        if (!isValidCoordinates(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds!");
        }
        
        return maze[row][col];
    }
    
    public static char changeMazeCellAndReturnOldValue(int row, int col, char newValue) {
        if (!isValidCoordinates(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds!");
        }
        
        char oldValue = maze[row][col];
        maze[row][col] = newValue;
        return oldValue;
    }
}
