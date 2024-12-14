import java.awt.*;
import java.applet.Applet;
//importing for key listeners
import java.awt.event.*;
 
// implementing time in this class and keylisteners
//methods for keylistener are keypressed, keyreleased and keyTyped
public class Game extends Applet implements Runnable, KeyListener{
	//thread method is where the time starts and where it SLEEPS
	//we're gonna use the thread to initiate the time loop at 60th of a second
	Thread t;//the time starting
	
	//to remove the flickering we need to create a buffer image in order to blit and display it on the main page
	Image offscreen_Image;
	Graphics offscreen_g;
	
	//variables for the arrow keys basically mapping keys for the user to use (keys track of which keys are pressed) for walking
	boolean up_pressed = false;
	boolean dn_pressed = false;
	boolean rt_pressed = false;
	boolean lt_pressed = false;
	boolean attack_pressed = false;
	String health = "100";

	//making a place for one kirby 
	Kirby kirby = new Kirby(100, 530, 20, 27);//extends rect class: (place on x axis,  place on y-axis, how wide, how long)
	//SuperKirby kirby = new SuperKirby(100, 530);

	//making a place for shooter2
	Shooter shooter1 = new Shooter(470, 415, 42, 43);//because extends rect class
	//projectiles for shooter 1
	Projectile fire1 = new Projectile(445, 423, 30, 30);
	Projectile fire2 = new Projectile(500, 423, 30, 30);
	//making a place for shooter2
	Shooter shooter2 = new Shooter(870, 319, 42, 43);//because extends rect class
	Projectile fire3 = new Projectile(845, 327, 30, 30);
	Projectile fire4 = new Projectile(900, 327, 30, 30);
	
	//place shooter 3
	Shooter shooter3 = new Shooter(350, 223, 42, 43);
	Projectile fire5 = new Projectile(325, 231, 30, 30);
	Projectile fire6 = new Projectile(380, 231, 30, 30);
	
	//making the place for a goomba (goombas will be evading the player in a fast manner and 
	Goomba goomba1 = new Goomba(277, 140, 20, 18);
	Goomba goomba2 = new Goomba(900, 250, 20, 18);

	//loading in the background for lvl-1
	ImageLayer level1 = new ImageLayer("Level_1.png");
	ImageLayer winpic = new ImageLayer("pixx_3.png");

	//heartClass heart1 = new heartClass(100, 80, 20, 18);
	
	//rectangle for collision for the bottom of the screen
	//start, top, right, bottom Rect bottom = new Rect(80, 0, 1142, 560);
	Rect bottom = new Rect(80, 560, 1142, 1);
	Rect top = new Rect(80, 93, 1142, 1);
	//making the rects for the drops in the map
	Rect bottomFall1 = new Rect(363, 557, 162, 1);
	Rect bottomFall2 = new Rect(773, 557, 162, 1);
	Rect topDrop1 = new Rect(363, 90, 162, 1);
	Rect topDrop2 = new Rect(773, 90, 162, 1);

	//Making rects for the walls
	Rect wallL = new Rect(50,70,28,500);
	Rect wallR = new Rect(1222,70,28,500);
	
	//creating tps basically to the top of the map
	Rect tp1 = new Rect(363, 611, 162, 1);
	Rect tp2 = new Rect(773, 611, 162, 1);

	
	//platforms that kirby can jump on and enemies will be on//platforms start from top left go down then go to top right
	Rect platform1Top = new Rect(277, 170, 210, 2);
	Rect platform1Bottom = new Rect(277, 187, 210, 2);
	Rect platform2Top = new Rect(235, 265, 293, 2); //shooter 3 on
	Rect platform2Bottom = new Rect(235, 283, 293, 2);
	Rect platform3Top = new Rect(197, 363, 373, 2);
	Rect platform3Bottom = new Rect(197, 380, 373, 2);
	Rect platform4Top = new Rect(155, 459, 455, 2);//shooter 1 on
	Rect platform4Bottom = new Rect(155, 478, 455, 2);
	Rect platform5Top = new Rect(809, 170, 214, 2);
	Rect platform5Bottom = new Rect(809, 187, 214, 2);
	Rect platform6Top = new Rect(770, 265, 293, 2);
	Rect platform6Bottom = new Rect(770, 283, 293, 2);
	Rect platform7Top = new Rect(730, 363, 373, 2);//shooter 2 on
	Rect platform7Bottom = new Rect(730, 380, 373, 2);
	Rect platform8Top = new Rect(690, 459, 455, 2);
	Rect platform8Bottom = new Rect(690, 478, 455, 2);

/* making sure the images draw
	//animation(name, how many images, type of image, how fast(duration))
	Animation kirbyWalkR = new Animation("Kirby_WalkR_", 4, ".PNG", 10);
	Animation kirbyWalkL = new Animation("Kirby_WalkL_", 4, ".PNG", 10);
	Animation kirbyJump = new Animation("Kirby_Jump_", 6, ".PNG", 10);
	Animation kirbyRoll = new Animation("Kirby_Roll_", 4, ".PNG", 10);
*/


	
	
	//this is a method hardcoded in applet already its like the main method of applet
	//init sets the stage and gets ready for what is put in the plane
	public void init() {
		//Giving value to the image and the offscreen 
		offscreen_Image = this.createImage(1600,1200);
		offscreen_g = offscreen_Image.getGraphics();
		
		//dont want to use the keyboard and calls into the key listener
		requestFocus();
		//adding the keylistener function in order for the buttons clicked to be heard by the code itself
		//and this class is the keylistener (found in the keylistener function)
		addKeyListener(this);
		//stating this class is runnable
		t = new Thread(this);
		//starting the thread
		//saying operating system i have a thread i need to run. run this thread
		t.start();
	}
	
	
	
	//run method for runnable - THIS IS THE GAME LOOP
	public void run() {
		while(true) {
			
			//this is where the action in all games take place because this is where time takes place
			//in one second it'll move by 60
			
		//when user clicks up he jumps so the gravity must go up which makes it less in my code
		if(up_pressed) {
			//has to write move up so the pose is the jump animation
			kirby.moveUp(1);
			//setting kirbys velocity to -9 so he has "buoyancy" 
			kirby.setVelocity(0,-9); // change x so kirby moves forward x set amount when clicked
			//setting acceleration for his y when hes jumping 
			kirby.setAcceleration(0, 1);
			//calling this method so all actions affect each other with velocity and acceleration
			kirby.moveBasedOnPhysics();

			//kirby.moveUp(3);
		}
		if(dn_pressed)  kirby.moveDown(3);
		if(lt_pressed)  kirby.moveLeft(3);
		if(rt_pressed)  kirby.moveRight(3); 
		if(attack_pressed) kirby.moveAttack();
			
		
		//setting gravity for kirby so he's being brought down
		kirby.setGravity(-3);
		
		//this is to cover the overlapping of the basic floor
		if(kirby.overlaps(bottom)) kirby.setY(530);
		if(kirby.overlaps(top)) kirby.setY(93);
		
		//overlap to make kirby fall to bottom of map then map to the top of the board
		if(kirby.overlaps(bottomFall1)) {
			kirby.setGravity(12);
			kirby.setY(561);
		}
		
		//overlap to make kirby fall to bottom of map then map to the top of the board
		if(kirby.overlaps(bottomFall2)) {
			kirby.setGravity(12);
			kirby.setY(561);
		}
		
		//overlaps for the attack of kirby towards the shooters
		
		//when he passed the top rect he'll simply go through and be placed
		if(kirby.overlaps(topDrop1)) 			kirby.setY(93);
		//when kirby overlaps the second hole on the right top of the screen
		if(kirby.overlaps(topDrop2)) 			kirby.setY(93);
		//overlap to make kirby go to the top after falling in the first hole
		if(kirby.overlaps(tp1)) 				kirby.setY(0);
		//overlap to make kirby go to the top after falling in the second hole
		if(kirby.overlaps(tp2)) 				kirby.setY(0);		
		//making sure kirby cant go through the walls
		if(kirby.overlaps(wallL)) 				kirby.setX(80);
		if(kirby.overlaps(wallR)) 				kirby.setX(1200);
		
		//overlap for platform 1
		if(kirby.overlaps(platform1Top)) 		kirby.setY(140);
		if(kirby.overlaps(platform1Bottom)) 	kirby.setY(190);
		
		//overlap for platform2
		if(kirby.overlaps(platform2Top))		kirby.setY(236);
		if(kirby.overlaps(platform2Bottom))		kirby.setY(285);
		
		//overlap for platform3
		if(kirby.overlaps(platform3Top))		kirby.setY(333);
		if(kirby.overlaps(platform3Bottom))		kirby.setY(383);
		
		//overlap for platform4
		if(kirby.overlaps(platform4Top))		kirby.setY(430);
		if(kirby.overlaps(platform4Bottom))		kirby.setY(480);
		
		//overlap for platform 1
		if(kirby.overlaps(platform5Top)) 		kirby.setY(140);
		if(kirby.overlaps(platform5Bottom)) 	kirby.setY(190);
		
		//overlap for platform2
		if(kirby.overlaps(platform6Top))		kirby.setY(237);
		if(kirby.overlaps(platform6Bottom))		kirby.setY(285);
		
		//overlap for platform3
		if(kirby.overlaps(platform7Top))		kirby.setY(333);
		if(kirby.overlaps(platform7Bottom))		kirby.setY(383);
		
		//overlap for platform4
		if(kirby.overlaps(platform8Top))		kirby.setY(430);
		if(kirby.overlaps(platform8Bottom))		kirby.setY(480);

		
		//basic movement for goomba
		if(dn_pressed)  goomba1.moveDown(4);
		if(lt_pressed)  goomba1.moveLeft(4);
		if(rt_pressed)  goomba1.moveRight(4);
		//goomba gravity
		//setting gravity for goomba1 so he's being brought down
		goomba1.setGravity(-3);
		//this is to cover the overlapping of the basic floor
		if(goomba1.overlaps(bottom)) goomba1.setY(539);
		if(goomba1.overlaps(top)) goomba1.setY(93);
		//overlap to make goomba1 fall to bottom of map then map to the top of the board
		if(goomba1.overlaps(bottomFall1)) {
			goomba1.setGravity(12);
			goomba1.setY(561);
		}
		//overlap to make goomba1 fall to bottom of map then map to the top of the board
		if(goomba1.overlaps(bottomFall2)) {
			goomba1.setGravity(12);
			goomba1.setY(561);
		}
		//goomba overlaps
		//when he passed the top rect he'll simply go through and be placed
		if(goomba1.overlaps(topDrop1)) 			goomba1.setY(93);
		//when kirby overlaps the second hole on the right top of the screen
		if(goomba1.overlaps(topDrop2)) 			goomba1.setY(93);
		//overlap to make kirby go to the top after falling in the first hole
		if(goomba1.overlaps(tp1)) 				goomba1.setY(0);
		//overlap to make kirby go to the top after falling in the second hole
		if(goomba1.overlaps(tp2)) 				goomba1.setY(0);		
		//making sure kirby cant go through the walls
		if(goomba1.overlaps(wallL)) 			goomba1.setX(80);
		if(goomba1.overlaps(wallR)) 			goomba1.setX(1200);
		//overlap for platform 1
		if(goomba1.overlaps(platform1Top)) 		goomba1.setY(150);
		if(goomba1.overlaps(platform1Bottom)) 	goomba1.setY(190);
		//overlap for platform2
		if(goomba1.overlaps(platform2Top))		goomba1.setY(247);
		if(goomba1.overlaps(platform2Bottom))	goomba1.setY(285);
		//overlap for platform3
		if(goomba1.overlaps(platform3Top))		goomba1.setY(343);
		if(goomba1.overlaps(platform3Bottom))	goomba1.setY(383);
		//overlap for platform4
		if(goomba1.overlaps(platform4Top))		goomba1.setY(440);
		if(goomba1.overlaps(platform4Bottom))	goomba1.setY(480);
		//overlap for platform 1
		if(goomba1.overlaps(platform5Top)) 		goomba1.setY(150);
		if(goomba1.overlaps(platform5Bottom)) 	goomba1.setY(190);
		//overlap for platform2
		if(goomba1.overlaps(platform6Top))		goomba1.setY(247);
		if(goomba1.overlaps(platform6Bottom))	goomba1.setY(285);
		//overlap for platform3
		if(goomba1.overlaps(platform7Top))		goomba1.setY(343);
		if(goomba1.overlaps(platform7Bottom))	goomba1.setY(383);
		//overlap for platform4
		if(goomba1.overlaps(platform8Top))		goomba1.setY(440);
		if(goomba1.overlaps(platform8Bottom))	goomba1.setY(480);				
	
		
		//goomba gravity
		//setting gravity for goomba1 so he's being brought down
		goomba2.setGravity(-3);
		//this is to cover the overlapping of the basic floor
		if(goomba2.overlaps(bottom)) goomba2.setY(539);
		if(goomba2.overlaps(top)) goomba2.setY(93);
		//overlap to make goomba1 fall to bottom of map then map to the top of the board
		if(goomba2.overlaps(bottomFall1)) {
			goomba2.setGravity(12);
			goomba2.setY(561);
		}
		//overlap to make goomba1 fall to bottom of map then map to the top of the board
		if(goomba2.overlaps(bottomFall2)) {
			goomba2.setGravity(12);
			goomba2.setY(561);
		}
		//goomba overlaps
		//when he passed the top rect he'll simply go through and be placed
		if(goomba2.overlaps(topDrop1)) 			goomba2.setY(93);
		//when kirby overlaps the second hole on the right top of the screen
		if(goomba2.overlaps(topDrop2)) 			goomba2.setY(93);
		//overlap to make kirby go to the top after falling in the first hole
		if(goomba2.overlaps(tp1)) 				goomba2.setY(0);
		//overlap to make kirby go to the top after falling in the second hole
		if(goomba2.overlaps(tp2)) 				goomba2.setY(0);		
		//making sure kirby cant go through the walls
		if(goomba2.overlaps(wallL)) 			goomba2.setX(80);
		if(goomba2.overlaps(wallR)) 			goomba2.setX(1200);
		//overlap for platform 1
		if(goomba2.overlaps(platform1Top)) 		goomba2.setY(150);
		if(goomba2.overlaps(platform1Bottom)) 	goomba2.setY(190);
		//overlap for platform2
		if(goomba2.overlaps(platform2Top))		goomba2.setY(247);
		if(goomba2.overlaps(platform2Bottom))	goomba2.setY(285);
		//overlap for platform3
		if(goomba2.overlaps(platform3Top))		goomba2.setY(343);
		if(goomba2.overlaps(platform3Bottom))	goomba2.setY(383);
		//overlap for platform4
		if(goomba2.overlaps(platform4Top))		goomba2.setY(440);
		if(goomba2.overlaps(platform4Bottom))	goomba2.setY(480);
		//overlap for platform 1
		if(goomba2.overlaps(platform5Top)) 		goomba2.setY(150);
		if(goomba2.overlaps(platform5Bottom)) 	goomba2.setY(190);
		//overlap for platform2
		if(goomba2.overlaps(platform6Top))		goomba2.setY(247);
		if(goomba2.overlaps(platform6Bottom))	goomba2.setY(285);
		//overlap for platform3
		if(goomba2.overlaps(platform7Top))		goomba2.setY(343);
		if(goomba2.overlaps(platform7Bottom))	goomba2.setY(383);
		//overlap for platform4
		if(goomba2.overlaps(platform8Top))		goomba2.setY(440);
		if(goomba2.overlaps(platform8Bottom))	goomba2.setY(480);				
	
		
		
		//overlaps for the shooter 
		//setting gravity for shooter1 so he's being brought down
		shooter1.setGravity(0);
		shooter2.setGravity(0);
		shooter3.setGravity(0);
				if(shooter3.overlaps(platform2Top))		shooter3.setY(219);
				if(shooter1.overlaps(platform4Top))		shooter1.setY(427);
				if(shooter2.overlaps(platform7Top))		shooter1.setY(333);

			//shooter 1
			if(attack_pressed && kirby.overlaps(shooter1)){
				shooter1.setY(700);
				fire1.setY(680);
				fire2.setY(710);
				
			} else if((shooter1.throwPL(kirby) == true)&& (kirby.moving == true)) {
				//fire1.chase(kirby);
				fire1.moveLeft(3);
				if(fire1.overlaps(wallL)) {
					fire1.setY(800);
				}
			} else if ((shooter1.throwPR(kirby) == true) && (kirby.moving == true) ) {
				fire2.moveRight(3);
			}
			
			//shooter2
			if(attack_pressed && kirby.overlaps(shooter2)){
				shooter2.setY(700);
				fire3.setY(680);
				fire4.setY(710);
				
			} else if((shooter2.throwPL(kirby) == true)&& (kirby.moving == true)) {
				//fire1.chase(kirby);
				fire3.moveLeft(3);
				if(fire1.overlaps(wallL)) {
					fire1.setX(100);
				}
			} else if ((shooter2.throwPR(kirby) == true) && (kirby.moving == true) ) {
				fire4.moveRight(3);
			}
			//shooter3
			if(attack_pressed && kirby.overlaps(shooter3)){
				shooter3.setY(700);
				fire5.setY(680);
				fire6.setY(710);
				
			} else if((shooter3.throwPL(kirby) == true)&& (kirby.moving == true)) {
				//fire1.chase(kirby);
				fire5.moveLeft(3);
				if(fire5.overlaps(wallL)) {
					fire5.setY(100);
				}
			} else if ((shooter3.throwPR(kirby) == true) && (kirby.moving == true) ) {
				fire6.moveRight(3);
			}
			
			if(fire1.overlaps(kirby) || fire2.overlaps(kirby) || fire3.overlaps(kirby) || fire4.overlaps(kirby) || fire5.overlaps(kirby) || fire6.overlaps(kirby)) {
				kirby.setY(530);
				kirby.setX(100);
			}
			
		//making the projectile stuff
			if(fire1.overlaps(wallL)) 			fire1.setY(700);
			if(fire1.overlaps(wallR)) 			fire1.setY(700);
			if(fire2.overlaps(wallL)) 			fire2.setY(700);
			if(fire2.overlaps(wallR)) 			fire2.setY(700);
			if(fire3.overlaps(wallL)) 			fire3.setY(700);
			if(fire3.overlaps(wallR)) 			fire3.setY(700);
			if(fire4.overlaps(wallL)) 			fire4.setY(700);
			if(fire4.overlaps(wallR)) 			fire4.setY(700);
			if(fire5.overlaps(wallL)) 			fire5.setY(700);
			if(fire5.overlaps(wallR)) 			fire5.setY(700);
			if(fire6.overlaps(wallL)) 			fire6.setY(700);
			if(fire6.overlaps(wallR)) 			fire6.setY(700);
			
			
		//making the method for the death animation when kirby hits the goomba	
			if(attack_pressed && kirby.overlaps(goomba1)){
				goomba1.moveStompDeath();
				goomba1.setY(800);
				
			}else {
			//where the goomba would evade kirby
			goomba1.evade(kirby);
			}
			
			
			if(attack_pressed && kirby.overlaps(goomba2)){
				goomba2.moveStompDeath();
				goomba2.setY(800);
				
			}else {
			//where the goomba would evade kirby
			goomba2.evade(kirby);
			}
			
			//repaint is an object we call to run our paint method through our operating system
			//takes the picture and shows it to everyone
			repaint();	
			
		try {
			//wanting to run loop at 1/60th of a second
			t.sleep(15);
		}//don't actually need an exception 
		catch(Exception x) {};
		}
	}
	
	//update call to make sure the image updates before the flickering - STOPS THE FLICKERING
	public void update(Graphics g) {
		//clearing the offscreen
		offscreen_g.clearRect(0, 0, 1600, 1200);
		paint(offscreen_g);
		//drawing offscreen image onto the main image screen
		g.drawImage(offscreen_Image, 0, 0, null);
	}
	
	
	
	//paint to draw out the things
	public void paint(Graphics g) {
		
	/* making sure the images draw
		//drawing current image in the soilder animation
		g.drawImage(kirbyWalkR.currentImage(), 100, 100, null);
		g.drawImage(kirbyWalkL.currentImage(), 200, 200, null);
		g.drawImage(kirbyJump.currentImage(), 300, 300, null);
		g.drawImage(kirbyRoll.currentImage(), 400, 400, null);
	*/
		
		
		
		//g.drawString(health, 500, 500);
		//if something is going to be drawn in the background put the things on top after it
		level1.draw(g);
//		winpic.draw(g);
		
		shooter1.draw(g);
		shooter2.draw(g);
		shooter3.draw(g);
		goomba2.draw(g);
		
		if((shooter1.throwPL(kirby) == true)&& (kirby.moving == true)) {
		fire1.draw(g);
		
		}
		if((shooter1.throwPR(kirby) == true)&& (kirby.moving == true)) {
			fire2.draw(g);
		}
		
		if((shooter2.throwPL(kirby) == true)&& (kirby.moving == true)) {
			fire3.draw(g);
		
		}
		if((shooter2.throwPR(kirby) == true)&& (kirby.moving == true)) {
			fire4.draw(g);
		}
		if((shooter3.throwPL(kirby) == true)&& (kirby.moving == true)) {
			fire5.draw(g);
		
		}
		if((shooter3.throwPR(kirby) == true)&& (kirby.moving == true)) {
			fire6.draw(g);
		}


		
		if((attack_pressed ==  true && kirby.overlaps(goomba1)) && (attack_pressed ==  true&& kirby.overlaps(shooter1)) && (attack_pressed ==  true && kirby.overlaps(shooter2)) 
				&& (attack_pressed ==  true && kirby.overlaps(shooter3)) && (attack_pressed ==  true&& kirby.overlaps(goomba2))){
			winpic.draw(g);
		} 
		
		
		
		
		kirby.draw(g);
//		g.setColor(Color.red);

		goomba1.draw(g);
//		g.setColor(Color.blue);
		//drawing rectangles to see if its on the right part for collison theory
//		bottom.draw(g);
//		top.draw(g);
//		wallL.draw(g);
//		wallR.draw(g);
//		
//		g.setColor(Color.green);
//		bottomFall1.draw(g);
//		bottomFall2.draw(g);
//		topDrop1.draw(g);
//		topDrop2.draw(g);
//		tp1.draw(g);
//		tp2.draw(g);
//		
//		g.setColor(Color.CYAN);
//		platform1Top.draw(g);
//		platform1Bottom.draw(g);
//		platform2Top.draw(g);
//		platform2Bottom.draw(g);
//		platform3Top.draw(g);
//		platform3Bottom.draw(g);
//		platform4Top.draw(g);
//		platform4Bottom.draw(g);
//		platform5Top.draw(g);
//		platform5Bottom.draw(g);
//		platform6Top.draw(g);
//		platform6Bottom.draw(g);
//		platform7Top.draw(g);
//		platform7Bottom.draw(g);
//		platform8Top.draw(g);
//		platform8Bottom.draw(g);
//		

	}
	
	
	//reporting which key was pressed // action event parameter is e and you call it to tell which key was pressed
	public void keyPressed(KeyEvent e) {
		//getting keycode for the VK
		//if action event e equals the VK_UP then up pressed is true
		if(e.getKeyCode() == KeyEvent.VK_W)  up_pressed = true;
		//if action event e equals the VK_DOWN then dn pressed is true
		if(e.getKeyCode() == KeyEvent.VK_S)  dn_pressed = true;
		//if action event e equals the VK_LEFT then lf pressed is true
		if(e.getKeyCode() == KeyEvent.VK_A)  lt_pressed = true;
		//if action event e equals the VK_RIGHT then rt pressed is true
		if(e.getKeyCode() == KeyEvent.VK_D)  rt_pressed = true;
		//if action event e equals the VK_SPACE then attack pressed is true
		if(e.getKeyCode() == KeyEvent.VK_SPACE)  attack_pressed = true;


	}
	//when the user isn't clicking the VK anymore we set the boolean equals to false
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)  up_pressed = false;
		if(e.getKeyCode() == KeyEvent.VK_S)  dn_pressed = false;
		if(e.getKeyCode() == KeyEvent.VK_A)  lt_pressed = false;
		if(e.getKeyCode() == KeyEvent.VK_D)  rt_pressed = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)  attack_pressed = false;

	}
	
	//not doing anything in this method (not necessary for game)
		public void keyTyped(KeyEvent e) {}
		
	}
	
	

