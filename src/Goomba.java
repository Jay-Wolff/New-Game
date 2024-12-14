import java.awt.*;

//this is for the stomp run enemy
public class Goomba extends Kirby{
		
		
		//overloaded constructor that is loaded because its the whereabouts of the enemy imported from the Rect class
	public Goomba(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	//chase method so he goes after kirby
	public void chase(Kirby kirby) {
		
		int xdiff = (Math.abs(x - kirby.x));
		int ydiff = (Math.abs(y - kirby.y));
		
		//you can set specific velocity and stuff in here
		if((ydiff < 400)&& (xdiff< 100)) { // if i make it 300 pix away he'll stop chasing 
		//if my x is bigger than kirbys x move me to the left
			if(ydiff > 20) {
				if (y > kirby.y) { // the + and - is how far away need to be for the stuff in the if statement
					moveUp(2);
				} else
				if (y < kirby.y) {
					moveDown(2);
				}
			}
			if(xdiff > 20) {
				if (x > kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
					moveLeft(1);
				}else 
				if (x < kirby.x) {
					moveRight(1);
				}
			}
		}
	}
		
	
	
	//chase method so he runs away from kirby
	public void evade(Kirby kirby) {
		//you can set specific velocity and stuff in here
		if((Math.abs(x - kirby.x) < 300) && (Math.abs(y - kirby.y) < 300))  { // if i make it 300 pix away he'll stop chasing 
		//if my x is bigger than kirbys x move me to the left
			if (x > kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
				moveRight(1);
			}
			if (x < kirby.x) {
				moveLeft(1);
			}
			if (y > kirby.y) { // the + and - is how far away need to be for the stuff in the if statement
					moveDown(2);
				} else
				if (y < kirby.y) {
					moveUp(2);
				}
			}
		}
		
	
	
	
	
	public void draw(Graphics g) {
		//for the goomba
		if(movingGoomba) {
		//current image gets the animation image and plays it for 10 consecutive seconds
		g.drawImage(goomba[poseGoomba].currentImage(), x, y, null);
		//else setting image to still image when nothing clicked
		}else g.drawImage(goomba[poseGoomba].stillImage(), x ,y , null);
		//when nothing is clicked after something has been clicked then it turns moving to false
		moving =false;
		
		//stating to draw goomba
		//g.drawRect(x, y, w, h);

	}
	}