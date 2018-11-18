import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    private VisualGameOfLife visualGameOfLife;


    public GameOfLife(){
        x_SIZE = 40;
        y_SIZE = 40;
        field = new int[y_SIZE][x_SIZE];
        nextGeneration = new int[y_SIZE][x_SIZE];
        init();
    }

    public GameOfLife(int field_width, int field_height) {
         x_SIZE = field_width;
         y_SIZE = field_height;
        field = new int[y_SIZE][x_SIZE];
        nextGeneration = new int[y_SIZE][x_SIZE];
        init();
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
        int posX, posY;
        try{
            this.field[y][x] = ALIVE;
        }catch (ArrayIndexOutOfBoundsException e){
            posX = x;
            posY = y;

            if( (posY > -1 && posY < this.x_SIZE) && posX >= this.y_SIZE) this.field[posX-this.y_SIZE][posY] = ALIVE;

            if( (posY > -1 && posY < this.x_SIZE) && posX < 0) this.field[y_SIZE+posX][posY] = ALIVE;

            if( (posX > -1 && posX < this.y_SIZE) && posY >= this.x_SIZE) this.field[posX][posY-this.x_SIZE] = ALIVE;

            if( (posX > -1 && posX < this.y_SIZE) && posY < 0) this.field[posX][this.x_SIZE+posY] = ALIVE;


            // Eckpunkte Kontrollieren

            if ( posX >= this.y_SIZE && posY >= this.x_SIZE) this.field[posX-this.y_SIZE][posY-this.x_SIZE] = ALIVE; // Oben Rechts

            if ( posX >= this.y_SIZE && posY < 0) this.field[posX-this.y_SIZE][this.x_SIZE+posY] = ALIVE; // Oben Links

            if ( posX < 0 && posY < 0) this.field[this.y_SIZE+posX][this.x_SIZE+posY] = ALIVE; // Unten Links

            if ( posX < 0 && posY >= this.x_SIZE) this.field[this.y_SIZE+posX][posY-this.x_SIZE] = ALIVE; // Unten Rechts

        }

    }

    @Override
    public void setDead(int x, int y) {
        this.field[y][x] = DEAD;
    }

    // Ich konnte die Ecken mithilfe einer Donau-Welt wie es gefordert wurde
    // Dort behandel ich die Exceptions durch eine Versuchte kontrolle der Zellen
    @Override
    public int getLiveNeighbors(int x, int y) {
        // Counter
        int neighbors = 0;
        // Zähler für die zu kontrollierenden Zellen
        int neighborH, neighborW;
        // Zwei For-Schleifen
        for (int h = 1; h >= -1; h--) {
            for (int w = -1; w <= 1; w++) {
                neighborH = x+h;
                neighborW = y+w;
                try{
                    if(this.field[neighborH][neighborW] == ALIVE && (x != neighborH || y != neighborW )) neighbors++;

                }catch(IndexOutOfBoundsException e){

                    if( (neighborW > -1 && neighborW < this.x_SIZE) && neighborH >= this.y_SIZE) if(this.field[0][neighborW] == ALIVE) neighbors++;

                    if( (neighborW > -1 && neighborW < this.x_SIZE) && neighborH < 0) if(this.field[this.y_SIZE-1][neighborW] == ALIVE) neighbors++;

                    if( (neighborH > -1 && neighborH < this.y_SIZE) && neighborW >= this.x_SIZE) if(this.field[neighborH][0] == ALIVE) neighbors++;

                    if( (neighborH > -1 && neighborH < this.y_SIZE) && neighborW < 0) if(this.field[neighborH][this.x_SIZE-1] == ALIVE) neighbors++;

                    // Eckpunkte kontrollieren

                    if ( neighborH >= this.y_SIZE && neighborW >= this.x_SIZE) if(this.field[0][0] == ALIVE) neighbors++;

                    if ( neighborH >= this.y_SIZE && neighborW < 0) if(this.field[0][this.x_SIZE-1] == ALIVE) neighbors++;

                    if ( neighborH < 0 && neighborW < 0) if(this.field[this.y_SIZE-1][this.x_SIZE-1] == ALIVE) neighbors++;

                    if ( neighborH < 0 && neighborW >= this.x_SIZE) if(this.field[this.y_SIZE-1][0] == ALIVE) neighbors++;

                }
            }
        }
        return neighbors;
    }

    @Override
    public void runGeneration() {
        showGrid();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //System.out.println("Position: ("+i+"/"+j+") Nachbarn: "+getLiveNeighbors(i,j));
                if(field[i][j] == ALIVE) nextGeneration[i][j] = (this.getLiveNeighbors(i,j) < 2 || this.getLiveNeighbors(i,j) > 3) ? DEAD:ALIVE;
                else nextGeneration[i][j] = (this.getLiveNeighbors(i,j) == 3) ? ALIVE:DEAD;

            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = nextGeneration[i][j];
            }
        }

    }

    @Override
    public void runGenerations(int howMany_) {
        for (int i = 1; i <= howMany_; i++) {
            runGeneration();
            System.out.println(" ------  Generation: "+i+"  -------");
        }
    }

    @Override
    public int[][] getGrid() {
        return field;
    }

    public void graffic(int duration){
        visualGameOfLife = new VisualGameOfLife(this.getGrid());

        int counter = 1;
        while(counter <= duration){
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            visualGameOfLife.refresh(this.getGrid());
            runGeneration();
            System.out.println(" ------  Generation: "+counter+"  -------");
            counter++;
        }
    }


    public void testGlider(int x, int y){
        this.setAlive(x,y);
        this.setAlive(x-1,y+1);
        this.setAlive(x-1,y+2);
        this.setAlive(x-2,y);
        this.setAlive(x-2,y+1);
    }

    public void testSpecificField(){
        this.setAlive(20, 18);
        this.setAlive(21, 18);
        this.setAlive(22, 18);
        this.setAlive(18, 18);
        this.setAlive(17, 18);
        this.setAlive(16, 18);
        this.setAlive(20, 20);
        this.setAlive(21, 20);
        this.setAlive(22, 20);
        this.setAlive(18, 20);
        this.setAlive(17, 20);
        this.setAlive(16, 20);
        this.setAlive(16, 19);
        this.setAlive(22, 19);
    }

    public static void main(String[] args) {
        // Siehe MainGUI
    }
}
