import java.awt.Color;
import java.awt.*;
import java.awt.geom.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
private Canvas myCanvas;

/**
* Create a BallDemo object. Creates a fresh canvas and makes it visible.
*/
public BallDemo()
{
myCanvas = new Canvas("Ball Demo", 600, 500);
myCanvas.setVisible(true);
}

/**
* This method demonstrates some of the drawing operations that are
* available on a Canvas object.
*/
public void drawDemo()
{
myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
myCanvas.setForegroundColor(Color.red);

myCanvas.drawString("We can draw text, ...", 20, 30);
myCanvas.wait(1000);

myCanvas.setForegroundColor(Color.black);
myCanvas.drawString("...draw lines...", 60, 60);
myCanvas.wait(500);
myCanvas.setForegroundColor(Color.gray);
myCanvas.drawLine(200, 20, 300, 50);
myCanvas.wait(500);
myCanvas.setForegroundColor(Color.blue);
myCanvas.drawLine(220, 100, 370, 40);
myCanvas.wait(500);
myCanvas.setForegroundColor(Color.green);
myCanvas.drawLine(290, 10, 320, 120);
myCanvas.wait(1000);

myCanvas.setForegroundColor(Color.gray);
myCanvas.drawString("...and shapes!", 110, 90);

myCanvas.setForegroundColor(Color.red);

// the shape to draw and move
int xPos = 10;
Rectangle rect = new Rectangle(xPos, 150, 30, 20);

// move the rectangle across the screen
for(int i = 0; i < 200; i ++) {
myCanvas.fill(rect);
myCanvas.wait(10);
myCanvas.erase(rect);
xPos++;
rect.setLocation(xPos, 150);
}
// at the end of the move, draw once more so that it remains visible
myCanvas.fill(rect);
}

/**
* Simulates bouncing balls
*/
public void bounce(int numberOfBalls)
{
int ground = 400; // position of the ground line
myCanvas.setVisible(true);
// draw the ground
myCanvas.drawLine(50, ground, 550, ground);
  
// create and show the balls
Random random = new Random();
HashSet<BouncingBall> balls = new HashSet<>();
for(int i=0; i<numberOfBalls; i++) {
Dimension size = myCanvas.getSize();
int x = random.nextInt((int) size.getWidth());
int y = random.nextInt((int) size.getHeight() / 2);
BouncingBall ball = new BouncingBall(x, y, 16, Color.blue, ground, myCanvas);
balls.add(ball);
ball.draw();
}
// make them bounce
boolean finished = false;
while(!finished) {
myCanvas.wait(50); // small delay
Iterator<BouncingBall> it = balls.iterator();
finished = true;
while(it.hasNext()) {
BouncingBall ball = it.next();
ball.move();
// stop once all balls has travelled a certain distance on x axis
if(ball.getXPosition() < 550) {
finished = false;
}
}
}
Iterator<BouncingBall> it = balls.iterator();
while(it.hasNext()) {
BouncingBall ball = it.next();
ball.erase();
}
}
  
/**
* Simulates bouncing balls in a box
*/
public void boxBounce(int numberOfBalls)
{
myCanvas.setVisible(true);
// draw the box
Rectangle box = new Rectangle(50,50,300,300);
myCanvas.draw(box);
// crate and show the balls
Random random = new Random();
HashSet<BoxBall> balls = new HashSet<>();
for(int i=0; i<numberOfBalls; i++) {
Dimension size = myCanvas.getSize();
int x = (int) box.getX() + random.nextInt((int) box.getWidth() - 16);
int y = (int) box.getY() + random.nextInt((int) box.getHeight() - 16);
int xSpeed = random.nextInt(30);
int ySpeed = random.nextInt(30);
Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
BoxBall ball = new BoxBall(x, y, xSpeed, ySpeed, 16, color, box, myCanvas);
balls.add(ball);
ball.draw();
}
// make them bounce
boolean finished = false;
while(!finished) {
myCanvas.wait(50); // small delay
Iterator<BoxBall> it = balls.iterator();
  
finished = true;
while(it.hasNext()) {
BoxBall ball = it.next();
ball.move();
// stop only once all balls has stopped moving
if(ball.isMoving()) {
finished = false;
}
}
}
Iterator<BoxBall> it = balls.iterator();
while(it.hasNext()) {
BoxBall ball = it.next();
ball.erase();
}
}
  
public void drawFrame() {
int borderSize = 20;
Dimension size = myCanvas.getSize();
Rectangle r = new Rectangle(borderSize, borderSize, (int) size.getWidth() - 2*borderSize, (int) size.getHeight() - 2*borderSize);
myCanvas.draw(r);
}
}

