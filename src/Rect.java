import java.awt.Graphics;

//class to represent axis aligned rectangles
public class Rect {
	//variables for the rectangle
	int x;
	int y;
	
	int w;
	int h;
	
	
	//gravity variables for y dimensions since it only effects the y since kirby goes down
	int gy;
	
	//variables velocity for x and y dimensions
	int vx;
	int vy;
	
	//constructor to initialize the numbers for the variables
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	
	//function to set kirby on the floor
	public void setY(int y) {
		this.y = y;
	}
	

	//function to set kirby on the floor
	public void setX(int x) {
		this.x = x;
	}
	
	//writing function to teach the rectangle how to move
	public void moveBy(int dx, int dy) {
		x+= dx;
		y += dy;
	}
	
	//shows kibys image to sit still
	public void idle(int dx, int dy) {
		x = dx;
		y = dy;
	}
	
	//move up means your delta y is decreasing
	public void moveUp(int dy) {
		y -= dy;
	}
	//move down means your delta y is increasing
	public void moveDown(int dy) {
		y += dy;
	}
	//move left means your delta x is decreasing
	public void moveLeft(int dx) {
		x -= dx;
	}
	//move right means your delta x is increasing
	public void moveRight(int dx) {
		x += dx;
	}
	
	//move function for the velocity
	public void move() {
		x += vx;
		y += vy;
	}
	
	//function for setting the velocity
	public void setVelocity(int vx, int vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	//gravity is a course that works in the y dimension only. Gravity is a force that accelerates you downwards
	//the actual number for gravity is 9.2 m/s^2 in the kinetric system. in the english system its 32 ft/s^2
	//strength of gravity is up to me and i set it in game code
	public void setGravity(int gy) {
		y -= gy;
	}
	
	//bounce function so when it hits the paddle it'll bounce back
	public void bounceOffVerticalSurface() {
		vx = -vx;
	}
	

	//bounce function so when it hits the floor it'll bounce back
	public void bounceOffHorizontalSurface() {
		vy = -vy;
	}
	//draw method to draw the rectangle
	public void draw(Graphics g) {
		//photo of the object the actual object is the varibles
		g.drawRect(x, y, w, h);
	}
	
	//method to make sure rect don't overlap
	public boolean overlaps(Rect r) {
		return(x + w >= r.x) && 
			(r.x + r.w >= x) && 
			(r.y + r.h >= y) &&
			(  y + h >= r.y);
	}
	
}

