//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class AquariumGame implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;

   public Bubbles[] bob = new Bubbles[10];

	public BufferStrategy bufferStrategy;
	public Image fishPic;
	public Image fishPic2;
	public Image fishPic3;
	public Image fishPic4;
	public Image sharkyPic;
	public Image background;
	public Image bubbles;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish goldFish;
	private Fish blueFish;
	private Fish redFish;
	private Fish purpleFish;
	private Shark sharky;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		AquariumGame ex = new AquariumGame();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public AquariumGame() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
		sharky = new Shark(123,321);
		sharky.width = 200;
		sharky.height = 200;
		bubbles = Toolkit.getDefaultToolkit().getImage("bubbles.jpg");
		fishPic = Toolkit.getDefaultToolkit().getImage("goldfish.png"); //load the picture
		goldFish = new Fish(10,100);
		fishPic2 = Toolkit.getDefaultToolkit().getImage("bluefish-removebg-preview.png");
		blueFish = new Fish(300,60);
		fishPic3 = Toolkit.getDefaultToolkit().getImage("redfish-removebg-preview.png");
		redFish = new Fish(500,50);
		fishPic4 = Toolkit.getDefaultToolkit().getImage("purplefish-removebg-preview.png");
		purpleFish = new Fish(25,35);
		sharkyPic = Toolkit.getDefaultToolkit().getImage("sharkImage.png");
		background = Toolkit.getDefaultToolkit().getImage("background.png");

		for(int x=0;x<10; x++){
			bob[x]=new Bubbles((int)(Math.random()*250), 100);
		}


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


		public void moveThings() {
			//tells different fish to bounce/wrap
			goldFish.bounce();
			blueFish.bounce();
			redFish.wrap();
			purpleFish.wrap();
			sharky.wrap();
			for(int x=0; x<bob.length; x++) {
				bob[x].bounce();
			}

			collisions();
		}
		//code for when the different fish collide
			public void collisions() {
				if (redFish.rec.intersects(blueFish.rec)) {
					blueFish.height = blueFish.height + 5;
					blueFish.width = blueFish.width + 5;
				}
					//calls the move( ) code in the objects
					goldFish.move();
					blueFish.move();
					redFish.move();
					purpleFish.move();
					sharky.move();

					//red gets faster when intersects w gold
				if (redFish.rec.intersects(goldFish.rec)){
					redFish.dx = redFish.dx*2;
					redFish.dy = redFish.dy*2;
				}
				//gold gets bigger when intersects w blue
				if (goldFish.rec.intersects(blueFish.rec)){
					goldFish.height = goldFish.height + 5;
					goldFish.width = goldFish.width + 5;

				}

				//shark eats all following fish when they intersect
				if (goldFish.rec.intersects(sharky.rec)){
					goldFish.isAlive = false;
			//		sharky.dx = sharky.dx+1;
			//		sharky.dy = sharky.dy+1;
				}
				if (blueFish.rec.intersects(sharky.rec)){
					blueFish.isAlive = false;
			//		sharky.dx = sharky.dx+1;
			//		sharky.dy = sharky.dy+1;
				}
				if (redFish.rec.intersects(sharky.rec)){
					redFish.isAlive = false;
			//		sharky.dx = sharky.dx+1;
			//		sharky.dy = sharky.dy+1;
				}
				if (purpleFish.rec.intersects(sharky.rec)){
					purpleFish.isAlive = false;
				//	sharky.dx = sharky.dx+1;
				//	sharky.dy = sharky.dy+1;

				}
			}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
	  canvas.addKeyListener(this);
      System.out.println("DONE graphic setup");

   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the fish and shark if they are alive
		g.drawImage(background, 0,0,WIDTH, HEIGHT, null);
		if (goldFish.isAlive) {
			g.drawImage(fishPic, goldFish.xpos, goldFish.ypos, goldFish.width, goldFish.height, null);
		}
		if (blueFish.isAlive) {
			g.drawImage(fishPic2, blueFish.xpos, blueFish.ypos, blueFish.width, blueFish.height, null);
		}
		if (redFish.isAlive) {
			g.drawImage(fishPic3, redFish.xpos, redFish.ypos, redFish.width, redFish.height, null);
		}
		if (purpleFish.isAlive) {
			g.drawImage(fishPic4, purpleFish.xpos, purpleFish.ypos, purpleFish.width, purpleFish.height, null);
		}
		g.drawImage(sharkyPic, sharky.xpos, sharky.ypos, sharky.width, sharky.height, null);

		for(int x=0; x<bob.length; x++){
			g.drawImage(bubbles, bob[x].xpos, bob[x].ypos, bob[x].width, bob[x].height, null);
		}
		//making fish come back to life once every fish is dead
		if (goldFish.isAlive==false && redFish.isAlive==false && blueFish.isAlive==false && purpleFish.isAlive==false){
			g.drawImage(fishPic, goldFish.xpos, goldFish.ypos, goldFish.width, goldFish.height, null);
			g.drawImage(fishPic2, blueFish.xpos, blueFish.ypos, blueFish.width, blueFish.height, null);
			g.drawImage(fishPic3, redFish.xpos, redFish.ypos, redFish.width, redFish.height, null);
			g.drawImage(fishPic4, purpleFish.xpos, purpleFish.ypos, purpleFish.width, purpleFish.height, null);
			goldFish.isAlive=true;
			redFish.isAlive=true;
			blueFish.isAlive=true;
			purpleFish.isAlive=true;
		}

		g.dispose();

		bufferStrategy.show();
	}

@Override
public void keyTyped(KeyEvent e) { //don't use this one we don't know what it does

}

@Override
public void keyPressed(KeyEvent e) {
	System.out.println("pressed????");
	System.out.println(e.getKeyChar());
	System.out.println(e.getKeyCode());
	//identify key codes for up, down, left, right arrow keys
	//up=38, down=40, right=39, left=37
	if(e.getKeyCode()==32){
		blueFish.isAlive = true;
		redFish.isAlive = true;
		goldFish.isAlive = true;
		purpleFish.isAlive = true;
	}
}

@Override
public void keyReleased(KeyEvent e) {
	//makes astros stop when key is released
}
}