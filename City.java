package Project2;

import java.util.ArrayList;
import java.util.Arrays;

public class City {

	private boolean walls[][];
	private boolean weapons[][];
	private int width, height;
	private Human people[];
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    protected static DotPanel dp;
	
	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */

	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];
		weapons= new boolean[w][h];
		randomBuildings(numB);
		populate(numP);
		zombieSterilizer();
	}
	
	public void addZombie (int x, int y){
		zombies.add (new Zombie(x/ZombieSim.dotSize,y/ZombieSim.dotSize,0,false));
	}

	public void changePeople(Human[] newPeople){
		people=newPeople;
	}
	public boolean[][] getWalls(){
		return walls;
	}
	
	public boolean[][] getWeapons(){
		return weapons;
	}
	
	public Human[] getPeople(){
		return people;
	}
	
	public ArrayList<Zombie> getZombies(){
		return zombies;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	/**
	 * Generates numPeople random people distributed throughout the city.
	 * People must not be placed inside walls!
	 * 
	 * @param numPeople the number of people to generate
	 */

	private void populate(int numPeople)
	{
		people = new Human[numPeople];
		for (int p=0; p < numPeople; p++) {
			int q=Helper.nextInt(width);
			int z=Helper.nextInt(height);

					while (walls[q][z]){
						q=Helper.nextInt(width);
						z=Helper.nextInt(height);
					}

			people[p] = new Human(q, z, Helper.nextInt(3));
		}
		int q=Helper.nextInt(width);
		int z=Helper.nextInt(height);
		while (walls[q][z]||weapons[q][z]){
			q=Helper.nextInt(width);
			z=Helper.nextInt(height);
		}

		zombies.add(new Zombie (q, z, Helper.nextInt(3), false));
	}
	
	
	
	/**
	 * Generates a random set of numB buildings.
	 * 
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;
		
		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			
			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}
	
	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		if (!ZombieSim.StopHumans){
		for (Human p: people) {
			p.update();
		}}
		if (!ZombieSim.StopZombies){
		for (int z=0; z<zombies.size(); z++){
			zombies.get(z).update();
		}
		}
	
	}
	
	/**
	 * Draw the buildings and all humans. Only clears
	 * the screen before drawing if traceMode is false
	 * @param traceMode set to true to leave trails
	 */
	public void draw(){

		drawWalls();
		drawRepellant();
		for (Human p: people) {
			p.draw();
		}
		for (int z=0; z<zombies.size(); z++){
			zombies.get(z).draw();
		}
		dp.repaintAndSleep(ZombieSim.sleepieTime);
	}
	
	public void zombieSterilizer(){

		int tx, ty, tw, th;
		int bldgMaxSize = width/6;
		int bldgMinSize = width/20;
		tx = Helper.nextInt(width);
		ty = Helper.nextInt(height);
		tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
		th = Helper.nextInt(bldgMaxSize) + bldgMinSize;
		
		for(int r = ty; r < ty + th; r++) {
			if(r >= height)
				continue;
			for(int c = tx; c < tx + tw; c++) {

				if(c >= width)
					break;
				
				weapons[c][r] = true;

			}
		}
		
	}

	private void drawRepellant() {
		dp.setPenColor(dp.RED);
		for(int r = 0; r < height; r++) 
		{
			for(int c = 0; c < width; c++) 
			{
				if(weapons[c][r])
				{
						dp.drawDot(c, r);
				}
			}
		}
	}
	/**
	 * Draw the buildings.
	 */
	private void drawWalls() {
		dp.setPenColor(dp.DARK_GRAY);
		for(int r = 0; r < height; r++) 
		{
			for(int c = 0; c < width; c++) 
			{
				if(walls[c][r])
				{
						dp.drawDot(c, r);
				}
			}
		}
	}
	
}
