import java.awt.*;

//an animation is an array of images that goes through all the images as the current increases

public class Animation {
	//reference to the array
	Image[] image;
	//starting at the default image
	int current = 0;
	//controls how fast its going
	int duration = 10;
	int delay;
	
	//need the parameters of the name of the animation, how many images are in it, and the extension(such as GIF, PNG, etc.)
	public Animation(String name, int count, String ext, int duration) {
		//making array for image
		image = new Image[count];
		//when i is at 0 start the cycle till the i is less than the count and i increments up by 1
		for(int i = 0; i < count; i++) {
			//this goes through all the images
			image[i] = Toolkit.getDefaultToolkit().getImage(name + i + ext);
			//setting duration
			this.duration = duration;
			//whatever duration is, is the delay
			delay = duration;
		}
	}
	
	//to show idle image when user isn't clicking anything
	public Image stillImage() {
		return image[0];
	}
	
	//setting the current image (so the idle)
	public Image currentImage() {
		//slowing down the current per second  
		//if something happens we increase
		if(delay == 0) {
			current++;
			//making sure it cycles around 
			if(current == image.length)   current = 1;
			//set delay back to where it started so we repaint slower
			delay = duration;
		}
		//delay going down by 1
		delay--;
		
		return image[current];
	}
}
