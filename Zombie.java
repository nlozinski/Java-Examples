package Project2;

import java.awt.Color;
import java.util.ArrayList;

public class Zombie {
	int x;
	int y;
	private int d;
	boolean s;//if zombie is "sterile" or not
	
	public Zombie(int x, int y, int d, boolean s)
	{
		this.x = x;
		this.y = y;
		this.d = d;
		this.s = s;
	}
	
	
	public void update()
	{
			
			int numAttempts=0;
			if (Helper.nextDouble()<=0.2){
				d=Helper.nextInt(4);
			}
			switch (checkHumans(x, y, s)){
			case 0:
				d=0;
			case 1:
				d=1;
			case 2:
				d=2;
			case 3:
				d=3;
			}
			while (!checkSurroundings (x, y, d)){
				
				d=Helper.nextInt(4);
				numAttempts++;
				if (numAttempts>5){
					return;
				}
			
			}
			
			if (ZombieSim.world.getWeapons()[x][y]){
				s=true;
			}
	
			if (d==0){
				y++;
			}
			if (d==1){
				x++;
			}
			if (d==2){
				y--;
			}
			if (d==3){
				x--;
			}
		}
	
	
	public int checkHumans (int x, int y, boolean s){
		for (int i=0; i<ZombieSim.world.getPeople().length; i++){
			int checkX=ZombieSim.world.getPeople()[i].x;
			int checkY=ZombieSim.world.getPeople()[i].y;
			if ((checkX==x&&((checkY==y+1)||(checkY==y-1)))||(checkY==y&&((checkX==x+1)||(checkX==y-1)))){
				//checks to see if human is within 1 position & calls infect
				if (!s){
				infect (ZombieSim.world.getPeople()[i], i);
				}
			}
			if (checkX==x&&(checkY>y-11&&checkY<y)){
				//checks to see if human is within 10 spaces to the south
				return 2;
			}
			if (checkX==x&&(checkY<y+11&&checkY>y)){
				//checks to see if human is within 10 spaces to the north
				return 0;
			}
			if (checkY==y&&(checkX>x-11&&checkX<x)){
				//checks to see if human is within 10 spaces to the west
				return 3;
			}
			if (checkY==y&&(checkX<x+11&&checkX>x)){
				//checks to see if human is within 10 spaces to the east
				return 1;
			}
		}
		return 5;
	}
	public void infect(Human human, int i) {
		ZombieSim.world.getZombies().add(new Zombie(human.x, human.y, human.d, false));
		ZombieSim.world.changePeople(removeHuman(ZombieSim.world.getPeople(), i));
	}

	public static Human[] removeHuman(Human[] original, int element){
		//removes human from the array, shrinks array by 1
	    Human[] newInfected = new Human[original.length - 1];
	    System.arraycopy(original, 0, newInfected, 0, element );
	    System.arraycopy(original, element+1, newInfected, element, original.length - element-1);
	    return newInfected;
	}


	public boolean checkSurroundings (int x, int y, int d){
		
		if (((y+1)>=ZombieSim.world.getHeight())&&(d==0)){
			return false;
		}
		if (((y-1)<=0)&&(d==2)){
			return false;
		}
		if (((x+1)>=ZombieSim.world.getWidth())&&(d==1)){
			return false;
		}
		if (((x-1)<=0)&&(d==3)){
			return false;
		}
		if(!checkWall(x,y,d)){
			return false;
		}

		return true;
	}
	

	
	public boolean checkWall (int x, int y, int d){
		int minusx= x-1;
		int minusy= y-1;
		int plusx= x+1;
		int plusy= y+1;
		if ((d==0)&&(ZombieSim.world.getWalls()[x][plusy])){
			return false;
		}
		if ((d==2)&&(ZombieSim.world.getWalls()[x][minusy])){
			return false;
		}
		if ((d==1)&&(ZombieSim.world.getWalls()[plusx][y])){
			return false;
		}
		if ((d==3)&&(ZombieSim.world.getWalls()[minusx][y])){
			return false;
		}
	
		return true;
	}
	
	public void draw()
	{

		ZombieSim.world.dp.setPenColor (City.dp.MAGENTA);
		ZombieSim.world.dp.drawDot(x, y);

	}
}



