/**
 * @author Ajmal
 * @since 13.11.18
 * @version 1.9
 * */

public class GameOfLife implements IGameOfLife {


    public final int x_SIZE;
    public final int y_SIZE;

    public int[][] field;
    public int[][] nextGeneration;


    public GameOfLife(){
        x_SIZE = 40;
        y_SIZE = 40;
        field = new int[y_SIZE][x_SIZE];
        nextGeneration = new int[y_SIZE][x_SIZE];
    }

    public GameOfLife(int field_width, int field_height) {
         x_SIZE = field_width;
         y_SIZE = field_height;
        field = new int[y_SIZE][x_SIZE];
        nextGeneration = new int[y_SIZE][x_SIZE];
    }



    @Override
    public void init() {
        for (int[] array: field) {
            for (int cell: array) {
                cell = DEAD;
            }
        }
    }

    @Override
    public void showGrid() {
        for (int i = field.length-1; i >= 0; i--) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }

    @Override
    public void setAlive(int x, int y) {
        this.field[y][x] = ALIVE;
    }

    @Override
    public void setDead(int x, int y) {
        this.field[y][x] = DEAD;
    }

    @Override
    public int getLiveNeighbors(int x, int y) {
        int neighbors = 0;

        if(field[y-1][x-1] == ALIVE) neighbors++;
        if(field[y-1][x] == ALIVE) neighbors++;
        if(field[y-1][x+1] == ALIVE) neighbors++;

        if(field[y][x-1] == ALIVE) neighbors++;
        if(field[y][x+1] == ALIVE) neighbors++;

        if(field[y+1][x-1] == ALIVE) neighbors++;
        if(field[y+1][x] == ALIVE) neighbors++;
        if(field[y+1][x+1] == ALIVE) neighbors++;

        return neighbors;
    }

    @Override
    public void runGeneration() {

    }

    @Override
    public void runGenerations(int howMany_) {

    }

    @Override
    public int[][] getGrid() {
        return field;
    }


    public static void main(String[] args) {
        GameOfLife g = new GameOfLife(5,5);
        g.setAlive(3,3);
        g.setAlive(3,4);
        g.setAlive(1,4);
        g.setAlive(0,0);
        g.setAlive(4,4);
        g.showGrid();
        System.out.println(g.getLiveNeighbors(0,0));
    }
}
