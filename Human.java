package Project2;

import java.awt.Color;

/**
 * A class to represent Humans. When there is not a zombie apocalypse occurring,
 * humans should follow these simple rules: if (1 in 10 chance): turn to face a
 * random direction (up/down/left/right) Move in the current direction one space
 * if not blocked by a wall
 * 
 * We will add additional rules for dealing with sighting or running into
 * zombies later.
 */
public class Human {
	int x;
	int y;
	int d;

	public Human(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	/**
	 * Update the human's position following the rules above.
	 * 
	 * @param rand
	 */
	public void update() {
		
		int numAttempts = 0;
		if (Helper.nextDouble() <= 0.1) {
			d = Helper.nextInt(4);
		}
		switch (checkZombies()) {

		case 0:

			d = 2;

			while (!checkSurroundings() && d != 0) {
				d = Helper.nextInt(4);

			}

		case 1:

			d = 3;

			while (!checkSurroundings() && d != 1) {
				d = Helper.nextInt(4);

			}

		case 2:

			d = 0;

			while (!checkSurroundings() && d != 2) {
				d = Helper.nextInt(4);

			}

		case 3:

			d = 1;

			while (!checkSurroundings() && d != 3) {
				d = Helper.nextInt(4);

			}

		default:

			while (!checkSurroundings()) {
				d = Helper.nextInt(4);

			}

		}
		if (d == 0) {
			y++;
		}
		if (d == 1) {
			x++;
		}
		if (d == 2) {
			y--;
		}
		if (d == 3) {
			x--;
		}
	}

	public boolean checkSurroundings() {

		if (((y + 1) >= ZombieSim.world.getHeight()) && (d == 0)) {
			return false;
		}
		if (((y - 1) <= 0) && (d == 2)) {
			return false;
		}
		if (((x + 1) >= ZombieSim.world.getWidth()) && (d == 1)) {
			return false;
		}
		if (((x - 1) <= 0) && (d == 3)) {
			return false;
		}
		if (!checkWall()) {
			return false;
		}

		return true;
	}

	public boolean checkWall() {
		int minusx = x - 1;
		int minusy = y - 1;
		int plusx = x + 1;
		int plusy = y + 1;
		boolean [][]wallCheck=ZombieSim.world.getWalls();

		if ((d == 0) && (wallCheck[x][plusy])) {
		
			return false;
		}
		if ((d == 2) && (wallCheck[x][minusy])) {
			
			return false;
		}
		if ((d == 1) && (wallCheck[plusx][y])) {
			
			return false;
		}
		if ((d == 3) && (wallCheck[minusx][y])) {
		
			return false;
		}

		return true;
	}

	public int checkZombies() {

		for (int i = 0; i < ZombieSim.world.getZombies().size(); i++) {
			int checkX = ZombieSim.world.getZombies().get(i).x;
			int checkY = ZombieSim.world.getZombies().get(i).y;

			if (checkX == x && (checkY > y - 11 && checkY < y)) {
				// checks to see if human is within 10 spaces to the south
				return 2;
			}
			if (checkX == x && (checkY < y + 11 && checkY > y)) {
				// checks to see if human is within 10 spaces to the north
				return 0;
			}
			if (checkY == y && (checkX > x - 11 && checkX < x)) {
				// checks to see if human is within 10 spaces to the west
				return 3;
			}
			if (checkY == y && (checkX < x + 11 && checkX > x)) {
				// checks to see if human is within 10 spaces to the east
				return 1;
			}
		}
		return 5;
	}

	public void draw() {

		ZombieSim.world.dp.setPenColor(City.dp.YELLOW);
		ZombieSim.world.dp.drawDot(x, y);

	}
}
