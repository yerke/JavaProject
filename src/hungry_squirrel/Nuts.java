package hungry_squirrel;

public class Nuts extends Entity {
    public static final int TOTAL_NUTS = 5;
    
    private static Nuts[] nutsArray;

    @Override
    public void create() {
        int x,y;
        
        nutsArray = new Nuts[TOTAL_NUTS];
        for(int i=0;i<TOTAL_NUTS;i++) {
            if (Math.random()>0.5) {
                nutsArray[i] = new Almond();
            }
            else {
                nutsArray[i] = new Peanut();
            }
            
            do {
                x = (int)(Math.random()*(Maze.MAX_MAZE_ROW-1));
                y = (int)(Math.random()*(Maze.MAX_MAZE_COLUMN-1));
            } while (!Maze.isEmpty(x, y));
            
            nutsArray[i].put(x,y);
        }
    }
    
    public int isNuts(char symbol) {
        if (symbol == Almond.NUT_SYMBOL)
            return Almond.NUTRITION_POINTS;
        if (symbol == Peanut.NUT_SYMBOL)
            return Peanut.NUTRITION_POINTS;
        return 0;
    }
}
