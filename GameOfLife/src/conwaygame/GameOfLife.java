package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);
        grid = new boolean[StdIn.readInt()][StdIn.readInt()];
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[0].length;j++) {
                if(StdIn.readBoolean() == false) {
                    grid[i][j] = DEAD;
                }
                else {
                    grid[i][j] = ALIVE;
                }
                    
                
            }

        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        if(grid[row][col] == DEAD) {
            return false;
        }
        return true; 
    }   

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        for(int i = 0;i < grid.length;i++) {
            for(int j =0;j < grid[0].length;j++) {
                if(grid[i][j] == true || grid[i][j] == ALIVE) {
                    return true;
                }
            }
        }
        return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        // WRITE YOUR CODE HERE
        int x = 0;
        if(row == 0 && col == 0){ //top left corner
            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[grid.length - 1][grid[0].length - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[grid.length - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            if(grid[row + 1][grid[0].length - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                x++;
            }
        }

        else if(row == 0 && col == grid[0].length -1){ // top right corner
            if(grid[row][0] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[grid.length - 1][col - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[grid.length - 1][0] == ALIVE) { // top right
                x++;
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[row + 1][0] == ALIVE) { // bottom right
                x++;
            }
        }
        else if(row == grid.length - 1 && col == grid[0].length - 1) {//bottom right

            if(grid[row][0] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[0][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[row - 1][0] == ALIVE) { // top right
                x++;
            }
    
            if(grid[0][col - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[0][0] == ALIVE) { // bottom right
                x++;
            }
        }
        else if(row == grid.length - 1 && col == 0) { // bottom left
            
            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[0][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[row - 1][grid[0].length - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            if(grid[0][grid[0].length - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[0][col + 1] == ALIVE) { // bottom right
                x++;
            }
        }

        else if(row == 0) { //top
            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[grid.length - 1][col - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[grid.length - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                x++;
            }

        }
        else if(row == grid.length -1) { //bottom
            
            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[0][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            if(grid[0][col - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[0][col + 1] == ALIVE) { // bottom right
                x++;
            }
        }
        else if(col == 0) {//left most

            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[row - 1][grid[0].length - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            if(grid[row + 1][grid[0].length - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                x++;
            }
        }
        else if(col == grid[0].length - 1) {//right most
            if(grid[row][0] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                x++;
            }
            
            if(grid[row - 1][0] == ALIVE) { // top right
                x++;
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                x++;
            }
    
            if(grid[row + 1][0] == ALIVE) { // bottom right
                x++;
            }
        }

        else{ //regular middle

            
    
            if(grid[row][col - 1] == ALIVE) { //left
                x++;
            }

            if(grid[row - 1][col - 1] == ALIVE) { // top left
                x++;
            }

            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                x++;
            }

            if(grid[row][col + 1] == ALIVE)  { // right
                x++;
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                x++;
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                x++;
            }
    
            
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                x++;
            }
    
            
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                x++;
            }
        }
        
        return x; 

    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        // WRITE YOUR CODE HERE
        
        boolean[][] grid2 = new boolean[grid.length][grid[0].length];
        for(int i = 0;i < grid2.length;i++) {
            for(int j =0;j < grid2[0].length;j++) {
                if(numOfAliveNeighbors(i, j) <= 1 && grid[i][j] == ALIVE) {
                    grid2[i][j] = DEAD;    //maybe not simentoeisly /rule 1
                }
            }
        }


        for(int i = 0;i < grid2.length;i++) { // gen 2
            for(int j =0;j < grid2[0].length;j++) {
                if(numOfAliveNeighbors(i, j) == 3 && grid[i][j] == DEAD) {
                    grid2[i][j] = ALIVE;    //maybe not simentoeisly //rule 2
                }
            }
        }

        for(int i = 0;i < grid2.length;i++) {
            for(int j =0;j < grid2[0].length;j++) {
                if((numOfAliveNeighbors(i, j) == 2 || numOfAliveNeighbors(i, j) == 3) && grid[i][j] == ALIVE) {
                    grid2[i][j] = ALIVE;    //maybe not simentoeisly //rule 3
                }
            }
        }

        for(int i = 0;i < grid2.length;i++) {
            for(int j =0;j < grid2[0].length;j++) {
                if(numOfAliveNeighbors(i, j) >= 4 && grid[i][j] == ALIVE) {
                    grid2[i][j] = DEAD;    //maybe not simentoeisly //rule 4
                }
            }
        }
        

        return grid2;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE
        grid = computeNewGrid();
        int num = 0;

        for(int i = 0;i < grid.length;i++) {
            for(int j =0;j < grid[0].length;j++) {
                if(grid[i][j] == ALIVE) {
                    num++;   
                }
            }
        }
        totalAliveCells = num;
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        // WRITE YOUR CODE HERE
        for(int i = 0;i < n;i++) {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        WeightedQuickUnionUF W = new WeightedQuickUnionUF(grid.length,grid[0].length);  //how to set parent to itself?
        ArrayList<int[]> cords = new ArrayList<int[]>();
        ArrayList<Integer> comms = new ArrayList<Integer>();
      
        
        for(int row = 0;row < grid.length;row++) {
            for(int col = 0;col < grid[0].length;col++) {
                if(numOfAliveNeighbors(row, col) > 0 && grid[row][col] == ALIVE) {

        if(row == 0 && col == 0){ //top left corner
            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
                
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                int[] c = {row,grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                int[] c = {grid.length - 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][grid[0].length - 1] == ALIVE) { // top left
                int[] c = {grid.length - 1,grid[0].length - 1};
                cords.add(c);
            }
            
            if(grid[grid.length - 1][col + 1] == ALIVE) { // top right
                int[] c = {grid.length - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[row + 1][grid[0].length - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                int[] c = {row + 1, col + 1};
                cords.add(c);
            }
        }

        else if(row == 0 && col == grid[0].length -1){ // top right corner
            if(grid[row][0] == ALIVE)  { // right
                int[] c = {row,0};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                int[] c = {grid.length - 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][col - 1] == ALIVE) { // top left
                int[] c = {grid.length - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[grid.length - 1][0] == ALIVE) { // top right
                int[] c = {grid.length - 1, 0};
                cords.add(c);
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, col - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][0] == ALIVE) { // bottom right
                int[] c = {row + 1, 0};
                cords.add(c);
            }
        }
        else if(row == grid.length - 1 && col == grid[0].length - 1) {//bottom right

            if(grid[row][0] == ALIVE)  { // right
                int[] c = {row,0};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[0][col] == ALIVE ) { // below
                int[] c = {0,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                int[] c = {row - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][0] == ALIVE) { // top right
                int[] c = {row - 1, 0};
                cords.add(c);;
            }
    
            if(grid[0][col - 1] == ALIVE) { // bottom left
                int[] c = {0, col - 1};
                cords.add(c);
            }
    
            if(grid[0][0] == ALIVE) { // bottom right
                int[] c = {0, 0};
                cords.add(c);
            }
        }
        else if(row == grid.length - 1 && col == 0) { // bottom left
            
            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                int[] c = {row,grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[0][col] == ALIVE ) { // below
                int[] c = {0,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][grid[0].length - 1] == ALIVE) { // top left
                int[] c = {row - 1,grid[0].length - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                int[] c = {row - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[0][grid[0].length - 1] == ALIVE) { // bottom left
                int[] c = {0, grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[0][col + 1] == ALIVE) { // bottom right
                int[] c = {0, col + 1};
                cords.add(c);
            }
        }

        else if(row == 0) { //top
            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][col]  == ALIVE) { // above
                int[] c = {grid.length - 1,col};
                cords.add(c);
            }
    
            if(grid[grid.length - 1][col - 1] == ALIVE) { // top left
                int[] c = {grid.length - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[grid.length - 1][col + 1] == ALIVE) { // top right
                int[] c = {grid.length - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, col - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                int[] c = {row + 1, col + 1};
                cords.add(c);
            }

        }
        else if(row == grid.length -1) { //bottom
            
            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[0][col] == ALIVE ) { // below
                int[] c = {0,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                int[] c = {row - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                int[] c = {row - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[0][col - 1] == ALIVE) { // bottom left
                int[] c = {0, col - 1};
                cords.add(c);
            }
    
            if(grid[0][col + 1] == ALIVE) { // bottom right
                int[] c = {0, col + 1};
                cords.add(c);
            }
        }
        else if(col == 0) {//left most

            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
            }
    
            if(grid[row][grid[0].length - 1] == ALIVE) { //left
                int[] c = {row,grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][grid[0].length - 1] == ALIVE) { // top left
                int[] c = {row - 1,grid[0].length - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                int[] c = {row - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[row + 1][grid[0].length - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, grid[0].length - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                int[] c = {row + 1, col + 1};
                cords.add(c);
            }
        }
        else if(col == grid[0].length - 1) {//right most
            if(grid[row][0] == ALIVE)  { // right
                int[] c = {row,0};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                int[] c = {row - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][0] == ALIVE) { // top right
                int[] c = {row - 1, 0};
                cords.add(c);
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, col - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][0] == ALIVE) { // bottom right
                int[] c = {row + 1, 0};
                cords.add(c);
            }
        }

        else{ //regular middle

            if(grid[row][col + 1] == ALIVE)  { // right
                int[] c = {row,col+1};
                cords.add(c);
            }
    
            if(grid[row][col - 1] == ALIVE) { //left
                int[] c = {row,col-1};
                cords.add(c);
            }
    
            if(grid[row + 1][col] == ALIVE ) { // below
                int[] c = {row + 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col]  == ALIVE) { // above
                int[] c = {row - 1,col};
                cords.add(c);
            }
    
            if(grid[row - 1][col - 1] == ALIVE) { // top left
                int[] c = {row - 1,col - 1};
                cords.add(c);
            }
            
            if(grid[row - 1][col + 1] == ALIVE) { // top right
                int[] c = {row - 1, col + 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col - 1] == ALIVE) { // bottom left
                int[] c = {row + 1, col - 1};
                cords.add(c);
            }
    
            if(grid[row + 1][col + 1] == ALIVE) { // bottom right
                int[] c = {row + 1, col + 1};
                cords.add(c);
            }
        }
        
        for(int[] a: cords) {
            if(W.find(a[0],a[1]) != W.find(row,col)) {
                W.union(row, col, a[0], a[1]);
                
            }
        }
        cords.removeAll(cords);
        
        if(!comms.contains(W.find(row,col))) {
            comms.add(W.find(row,col));
        }
                } //end of og if
            }
        }

        for (int i = 0;i < grid.length;i++) {
            for(int j = 0;j<grid[0].length;j++) {
                if(grid[i][j] == ALIVE) {
                    if(!comms.contains(W.find(i,j))) {
                        comms.add(W.find(i,j));
                    }
                }
                
            }
        }
        
        return comms.size(); // update this line, provided so that code compiles
    }
}
