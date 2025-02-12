import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Fish {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the fish is alive or dead.
    public Rectangle rec;

    public void bounce(){
        //bounce of east wall
        if (xpos>1000){
            dx=-dx;
        }
        //bounce of west wall
        if (xpos<10){
            dx=-dx;
        }

//bounce of south wall
        if(ypos>690){
            dy=-dy;
        }

//bounce of north wall
        if (ypos<10) {
            dy=-dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;

        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
    public void wrap(){
        if (xpos>1000){
            xpos = 0;
        } else
        if (xpos<0){
            xpos=1000;
        }
        if (ypos>700){
            ypos=0;
        }else
        if (ypos<0){
            ypos=700;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Fish(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =5;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
 
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
    }
}






