import java.awt.*;
//to make layers like in photoshop for the background
public class ImageLayer {
	Image image;

	
	//grabbing the image from the files 
	public ImageLayer(String filename) {
		this.image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	
	//creating graphics context in draw
	public void draw(Graphics g) {
		for(int i = 0; i < 2; i++)
		//g.drawImage(image, 0, 0, null);
			//
			g.drawImage(image, 0, 0, null);
	}
	
}
