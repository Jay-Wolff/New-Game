import java.awt.*;
//class that represents the main character kirby
public class Kirby extends Rect{ //extends rect for the velocity method and overlapping method/ints
		//acceleration variables 
		int ax = 0;
		int ay = 0;
		
		//variables velocity for x and y dimensions
		int vx = 0;
		int vy = 0;
		
	//kirby needs an animation for everything he can do(walking, shooting, jumping, etc) so it needs to have an array
	Animation[] anim;
	
	
	//when kirby moves up we need that pose to be called so we make constance
	static final int UP = 0; //equals 0 cause the pose for up is the first one
	static final int DN = 1;
	static final int LT = 2;
	static final int RT = 3;
	static final int ATTACK = 4;

	//extra instance variable to keep track of each pose he's doing
	int pose;
	
	//if the character isnt moving (if user isnt clicking buttons)
	boolean moving = false;
	
	//things for the shooter
	Animation[] shooter;
	int poseShooter;
	static final int SL = 0; //equals 0 cause the pose for up is the first one
	static final int SR = 1;
	static final int FL = 2;
	static final int FR = 3;
	boolean shooterMoving = false;
	
	//things for goomba
	Animation[] goomba;
	int poseGoomba;
	static final int WL = 0; //equals 0 cause the pose for up is the first one
	static final int WR = 1;
	static final int ST = 2;
	boolean movingGoomba = false;
	
	//things for fireball
	Animation[] projectile;
	int poseFire;
	static final int FF = 0; //equals 0 cause the pose for up is the first one
	static final int CT = 1;
	boolean movingFire = false;
	
	//things for fireball contact
	Animation [] contact;
	int hit;
	static final int DH = 0; //direct hit
	//static final int FH = 1; //final hit
	boolean madeContact = false;
	
	
	Animation [] life;
	int heart;
	static final int HB = 0; //heartbeat
	boolean showlife = false;
	
	//need the load up of the shooters fireball too 
	
	//kirby roll
	
	
	//constructor that loads Kirby(name of character, pose(what he's doing up, dwn, lft, etc),  String Name, String[] pose 
	public Kirby(int x, int y, int w, int h) {
		//calling super so it grabs from the constructor in the Rect class
		super(x,y,w,h);
		//array of images
		anim = new Animation[5];//the number is the amount of poses in the array of strings
		//array of strings for the moves
		String[] pose = {"Jump", "Roll", "WalkL", "WalkR", "Attack"}; 
		//so when its a different number of animations it still goes through
		for(int i = 0; i < anim.length; i++) {
			anim[i] = new Animation("Kirby_" + pose[i] + "_", 4, ".PNG", 10);
		}
		
	//making animation for shooter
		//array of images
		shooter = new Animation[4];
		//array of strings for the moves
		String[] poseShooter = {"WalkL", "WalkR", "FireL", "FireR"}; //, "Attack"
		//so when its a different number of animations it still goes through
		for(int i = 0; i < shooter.length; i++) {
			shooter[i] = new Animation("Shooter_" + poseShooter[i] + "_", 4, ".PNG", 10);
		}

	//making animation for life
			//array of images
			life = new Animation[1];
			//array of strings for the moves
			String[] heart = {"life"}; //, "Attack"
			//so when its a different number of animations it still goes through
			for(int i = 0; i < life.length; i++) {
				life[i] = new Animation("heart_" + poseShooter[i] + "_", 2, ".PNG", 10);
			}
	//making animation method for fireball left shoot
	//array of images
		projectile = new Animation[1];
		//array of strings for the moves
		String[] poseFire = {"L"}; 
		//so when its a different number of animations it still goes through
		for(int i = 0; i < projectile.length; i++) {
			projectile[i] = new Animation("Fireball_" + poseFire[i] + "_", 4, ".PNG", 10);
		}
	
	//making animation method for goomba
	//array of images
		goomba = new Animation[3];
		//array of strings for the moves
		String[] poseGoomba = {"WalkL", "WalkR", "Stomp"}; 
		//so when its a different number of animations it still goes through
		for(int i = 0; i < goomba.length; i++) {
			goomba[i] = new Animation("Goomba_" + poseGoomba[i] + "_", 4, ".PNG", 10);
		}
		
	//making the animation for the contact of the projectile
		contact = new Animation[1];
		//array of strings for the moves
		String[] hit = {"Contact"}; 
		//so when its a different number of animations it still goes through
		for(int i = 0; i < contact.length; i++) {
			contact[i] = new Animation("Fireball_" + hit[i] + "_", 3, ".PNG", 10);
		} 
	
		
	}// closes movement animation
	
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
		x +=vx;
		y += vy;
		//velocity in the x&y get updated based on the acceleration dimension
		vx += ax;
		vy += ay;
	}
	
/* MAKING SURE IT MOVES	*/
//moveBy function
public void moveBy(int dx, int dy) {
	x += dx;
	y += dy;
}
	
	//want to pick the correct animation im using based on key control
	//move up means your delta y is decreasing
	public void moveUp(int dy) {
		y -= dy;
		pose = UP;
		moving = true;
		heart = HB;
		showlife = true;
	}
	//move down means your delta y is increasing
	public void moveDown(int dy) {
		y += dy;
		pose = DN;
		moving = true;
	}
	//move left means your delta x is decreasing
	public void moveLeft(int dx) {
		x -= dx;
		pose = LT;
		moving = true;
		//for the shooter
		poseShooter = FL;
		shooterMoving = true;
		//for the goomba
		poseGoomba = WR;
		movingGoomba = true;
	}
	//move right means your delta x is increasing
	public void moveRight(int dx) {
		x += dx;
		pose = RT;
		moving = true;
		//for the shooter
		poseShooter = FR;
		shooterMoving = true;
		//for the goomba
		poseGoomba = WL;
		movingGoomba = true;
	}
	//attack setting
	public void moveAttack() {
		pose = ATTACK;
		moving = true;
	}
	
	//goomba stomp setting
	public void moveStompDeath() {
		poseGoomba = ST;
		movingGoomba = false;
	}
	public void shooterStopsL() {
		poseShooter = SL;
		shooterMoving = false;
	}
	
	public void shooterStopsR() {
		poseShooter = SR;
		shooterMoving = false;
	}
	
	//shooter to the left animation
	public void shootLeft(){
		//x += dx;
		poseShooter = FL;
		shooterMoving = false;
	}
	//shooter to the right animation
	public void shootRight() {
		//x += dx;
		poseShooter = FR;
		shooterMoving = true;
	}
	
	public void showHearts() {
		heart = HB;
		showlife = true;
	}
	
	public void fireFollow() {
		poseFire = CT;
		movingFire = true;
	}
	
	public void madeContact() {
		hit = DH;
		madeContact = true;
	}
	//need draw method for anything/ anyone that goes on the screen 
	public void draw(Graphics g) {
		if(moving) {
		//current image gets the animation image and plays it for 10 consecutive seconds
		g.drawImage(anim[pose].currentImage(), x, y, null);
		//else setting image to still image when nothing clicked
		}else g.drawImage(anim[pose].stillImage(), x ,y , null);
		//when nothing is clicked after something has been clicked then it turns moving to false
		moving =false;
		
		
		//stating to draw kirby now that it has a value
		//g.drawRect(x, y, w, h);
		
	}
}
