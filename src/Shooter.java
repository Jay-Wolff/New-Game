import java.awt.*;
//this is for the fireball enemy
public class Shooter extends Kirby{
	
	
	//overloaded constructor that is loaded because its the whereabouts of the enemy imported from the Rect class
	public Shooter(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	//chase method so he goes after kirby
	public boolean chase(Kirby kirby) {
		
		int xdiff = (Math.abs(x - kirby.x));
		int ydiff = (Math.abs(y - kirby.y));
		
		//you can set specific velocity and stuff in here
		if((ydiff < 400)&& (xdiff< 400)) { // if i make it 300 pix away he'll stop chasing 
		//if my x is bigger than kirbys x move me to the left
			if(ydiff > 35) {
				if (y > kirby.y) { // the + and - is how far away need to be for the stuff in the if statement
					moveUp(0);
					return true;
				} else
				if (y < kirby.y) {
					moveDown(0);
					return true;
				}
			}
			if(xdiff > 35) {
				if (x > kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
					moveLeft(0);
					return true;
				}else 
				if (x < kirby.x) {
					moveRight(0);
					return true;
				}
			}
		}
		return false;
	}
	
	//chase method so he goes after kirby
	public void evade(Kirby kirby) {
		
		//you can set specific velocity and stuff in here
		if((Math.abs(x - kirby.x) < 100) && (Math.abs(y - kirby.y) < 100))  { // if i make it 300 pix away he'll stop chasing 
		//if my x is bigger than kirbys x move me to the left
			if (x > kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
				moveRight(1);
			}
			if (x < kirby.x) {
				moveLeft(1);
			}
			if (y > kirby.y) { // the + and - is how far away need to be for the stuff /lin the if statement
				moveDown(2);
			} else
			if (y < kirby.y) {
				moveUp(2);
			}
		}
	}
	
	//method to throw projectile
	public boolean throwPL(Kirby kirby) {
		//you can set specific velocity and stuff in here
				if((Math.abs(x - kirby.x) < 200) && (Math.abs(y - kirby.y) < 100))  { // if i make it 300 pix away he'll stop chasing 
				//if my x is bigger than kirbys x move me to the left
					if (x > kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
						shootLeft();
						return true;
					}
				
				}
				return false;
			}
	
	//method to throw projectile
	public boolean throwPR(Kirby kirby) {
		//you can set specific velocity and stuff in here
				if((Math.abs(x - kirby.x) < 200) && (Math.abs(y - kirby.y) < 100))  { // if i make it 300 pix away he'll stop chasing 
				//if my x is bigger than kirbys x move me to the left
					if (x < kirby.x) { // the + and - is how far away need to be for the stuff in the if statement
						shootRight();
						return true;
					}
				
				}
				return false;
			}
	
	//drawing the shooter on the screen
	public void draw(Graphics g) {
		//for the shooter
		if(shooterMoving) {
		//current image gets the animation image and plays it for 10 consecutive seconds
		g.drawImage(shooter[poseShooter].currentImage(), x, y, null);
		//else setting image to still image when nothing clicked
		}else g.drawImage(shooter[poseShooter].stillImage(), x ,y , null);
		//when nothing is clicked after something has been clicked then it turns moving to false
		shooterMoving = false;
		
		//putting collision rect
		//g.drawRect(x, y, w, h);
		
	}
	
}
