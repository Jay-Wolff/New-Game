import java.awt.*;

public class Projectile extends Shooter{

	//constructor implementing the ints from the shooter class
	public Projectile(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	//create a method to shoot if the shooter is in close prozimity with kirby
	public void throwP(Kirby kirby) {
		//you can set specific velocity and stuff in here
			if((Math.abs(x - kirby.x) < 200) && (Math.abs(y - kirby.y) < 200))  { // if i make it 300 pix away he'll stop throwing the projectiles 
				if(x < kirby.x) {
					moveLeft(1);
				}
				if(x > kirby.x) {
					moveRight(1);
				}
			}
		
	}
	public void hit(Kirby kirby) {
		madeContact();
	}
	
	
	//animation for the fireball

	
	//draw animation for the fireball reaching cocntact with kirby 
		public void draw(Graphics g) {
			if(movingFire) {
			//current image gets the animation image and plays it for 10 consecutive seconds
			g.drawImage(projectile[poseFire].currentImage(), x, y, null);
			//else setting image to still image when nothing clicked
			}else g.drawImage(projectile[poseFire].stillImage(), x ,y , null);
			//when nothing is clicked after something has been clicked then it turns moving to false
			movingFire =false;
			
			
			//stating to draw kirby now that it has a value
			//g.drawRect(x, y, w, h);
			
		}

}
