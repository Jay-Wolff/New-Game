//need to update so it moves with the character on the screen going through the 
public class Camera {
	
	static int x;
	static int y;
	
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
}
