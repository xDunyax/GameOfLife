public interface IGameOfLife {

	public static final int ALIVE = 1;
	public static final int DEAD = 0;
	public static final int SIZE = 40;



	/*
	 * Meine Kommentarensprache:
	 * + = Habe ich gemacht und verstanden
	 * - = Habe ich noch nicht gemacht, da es echt schwierig ist
	 *   = Habe ich noch nicht gemacht
	 * ! = Habe ich gemacht aber nicht ganz verstand, deshalb muss es noch mit jemanden ausgesprochen werden
	 *
	 * Grund:
	 * Es dient zu Orientierung.
	 *
	 * Anwendung:
	 * Es muss in einem Kommentar angewandt werden. Außerdem muss es auf oder neben einer Klasser, Methode
	 * etc. hingeschrieben. Die Einleitung wird mit einem Hash-Tag vollzogen ( # )
	 */


	/**
	 * init values
	 * left lower corner has position x=0 y=0 - grid[0][0]
	 * right upper corner has position x=SIZE-1 y=SIZE-1 - grid[SIZE-1][SIZE-1]
	 */
	public abstract void init();

	/**
	 * show the grid
	 * left lower corner has position x=0 y=0 - grid[0][0]
	 * right upper corner has position x=SIZE-1 y=SIZE-1 - grid[SIZE-1][SIZE-1]
	 */
	public abstract void showGrid();

	/**
	 * set cell alive
	 *
	 * @param x
	 *            x-position
	 * @param y
	 *            y-position
	 */
	public abstract void setAlive(int x, int y);

	/**
	 * set cell dead
	 *
	 * @param x  x-position
	 * @param y  y-position
	 */
	public abstract void setDead(int x, int y);

	/**
	 * 
	 * how many neighbours do I have
	 *
	 * @param x x-position
	 * @param y y-position
	 * @return number of neighbours
	 */
	public abstract int getLiveNeighbors(int x, int y);

	/**
	 * 
	 * simulate a round
	 *
	 */
	public abstract void runGeneration();

	/**
	 * 
	 * simulate many rounds
	 * 
	 * @param howMany_
	 *            how often should we run the lot
	 */
	public abstract void runGenerations(int howMany_);

	/**
	 * @return the grid
	 */
	public abstract int[][] getGrid();

}