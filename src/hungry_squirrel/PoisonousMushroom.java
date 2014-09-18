package hungry_squirrel;

public class PoisonousMushroom extends Entity {
    public static final int TOTAL_MUSHROOMS = 5;
    public static final int NUTRITION_POINTS = -15;
    public static final char MUSHROOM_SYMBOL = 'M';
    
    private static PoisonousMushroom[] mushroomsArray;

    public PoisonousMushroom() {
        symbol = MUSHROOM_SYMBOL;
    }
    
    @Override
    public void create() {
        int x,y;
        
        mushroomsArray = new PoisonousMushroom[TOTAL_MUSHROOMS];
        for(int i=0;i<TOTAL_MUSHROOMS;i++) {
            mushroomsArray[i] = new PoisonousMushroom();
            
            do {
                x = (int)(Math.random()*(Maze.MAX_MAZE_ROW-1));
                y = (int)(Math.random()*(Maze.MAX_MAZE_COLUMN-1));
            } while (!Maze.isEmpty(x, y));
            
            mushroomsArray[i].put(x,y);
        }
    }
    
    public int isMushroom(char symbol) {
        if (symbol == MUSHROOM_SYMBOL)
            return NUTRITION_POINTS;
        return 0;
    }
}
