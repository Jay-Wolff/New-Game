import java.awt.Graphics;
//class that represents the main character kirby
public class SuperKirby { 
	//position variables
	double px;
	double py;
	
	//acceleration variables 
		double ax = 0;
		double ay = 0;
		
		//variables velocity for x and y dimensions
		double vx = 0;
		double vy = 0;
		
	//soilder needs an animation for everything he can do(walking, shooting, jumping, etc) so it needs to have an array
	Animation[] anim;
	
	//when kirby moves up we need that pose to be called so we make constance
	static final int UP = 0; //equals 1 cause the pose for up is the first one
	static final int DN = 1;
	static final int LT = 2;
	static final int RT = 3;
	
	//extra instance variable to keep track of each pose he's doing
	int pose;
	
	//if the character isnt moving (if user isnt clicking buttons)
	boolean moving = false;
	
	//constructor that loads Kirby(name of character, pose(what he's doing up, dwn, lft, etc),  String Name, String[] pose 
	public SuperKirby(int px, int py) {
		//calling super so it grabs from the constructor in the Rect class
		this.px=px;
		this.py=py;
		//array of images
		anim = new Animation[4];
		//array of strings for the moves
		String[] pose = {"Jump", "Roll", "WalkL", "WalkR"};
		//so when its a different number of animations it still goes through
		for(int i = 0; i < anim.length; i++) {
			anim[i] = new Animation("Kirby_" + pose[i] + "_", 4, ".PNG", 10);
		}
	}
	
	//function for setting the velocity
		public void setVelocity(int vx, int vy) {
			this.vx = vx;
			this.vy = vy;
		}
		//function for setting the acceleration
		public void setAcceleration(int ax, int ay) {
			this.ax = ax;
			this.ay = ay;
		}	
	//coding the physics so acceleration and velocity can be used to manipulate a characters gravity/capabilities
	public void moveBasedOnPhysics() {
		//x and y get updated based on the x&y in the velocity dimension
		px +=vx;
		py += vy;
		//velocity in the x&y get updated based on the acceleration dimension
		vx += ax;
		vy += ay;
	}
	
	//need draw method for anything/ anyone that goes on the screen 
	public void draw(Graphics g) {
		if(moving) {
		//current image gets the animation image and plays it for 10 consecutive seconds
		g.drawImage(anim[pose].currentImage(), (int)px, (int)py, null);
		//else setting image to still image when nothing clicked
		}else g.drawImage(anim[pose].stillImage(), (int)px , (int)py , null);
		
		//when nothing is clicked after something has been clicked then it turns moving to false
		moving =false;
		//sating to draw kirby now that it has a value
		//g.drawRect(x, y, w, h);
		
	}
}

/*CMP 405 NOTES 12/7
 							**ALL THESE NOTES EXPLAIN AN AUTOTOMONOUS SYSTEM **
 		**RIP AND OSPF IS JUST FOR ROUTERS AMOUNGST "friends" and are inside the autotomonous system and the A-system is under a whole network **
*OSPF - link state (means information about all of the edges of the graphs of the ethernet cable connection)
so its a larger quantity of info
*IN OSPF you are learning about and telling your neighbors about the current state of all of the edges of the physical network
*OSPF has weighted graphs so it'll know which link is more expensive(meaning the one that requires more transmission)
*the cost of transmission between you and the person next to you

*RIP sends edges so basically it treats all links to send things as the same unit cost
*RIP distance protocol		both(OSPF & RIP) send messages to one another about what they learned about the details of the router itself
*both send "ads" to their neighbors about updates about the router
*both learn their distance from themselves to the routers based on the number of hops

*the announcement RIP sends is known as the distance vector

*all routers need to know is which IP should he used for the best forwarding link
*routing protocol collectively figures out that going from me to that destination, and the "going" would be done in the least
amount of steps. and the way we learn what the appropriate choices with destination and direction for the appropriate links
is by running RIP, which involves exchanging the distance vectors in the "ads"

*Your forwarding labels are your immediate neighbors

*
*/