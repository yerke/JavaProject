package hungry_squirrel;

public abstract class Entity implements IEntity {
    public char symbol;
    protected int xPosition;
    protected int yPosition;
    
    @Override
    public char put(int newX, int newY) {
        if (!Maze.available(newX, newY)) {
            throw new IllegalArgumentException("Cannot put in a position occupied by wall!");
        }
        xPosition = newX;
        yPosition = newY;
        
        return Maze.changeMazeCellAndReturnOldValue(xPosition, yPosition, symbol);
    }
    
    protected void clearCurrentPosition() {
        if (!Maze.isValidCoordinates(xPosition, yPosition)) {
            throw new IllegalArgumentException("Cannot clear the current position!");
        }
        
        Maze.changeMazeCellAndReturnOldValue(xPosition, yPosition, Maze.EMPTY_SYMBOL);
    }
}
