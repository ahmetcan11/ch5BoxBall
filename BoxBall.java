
/**
 * BoxBall class creates an object which holds a box and inside of the
 * box moving balls that speeds are determined random
 * @author (Ahmet Can Turk)
 * @version 03.25.2019
 */
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
* A ball that bounces inside a box.
*
*/
public class BoxBall
{   
private float friction = 0.8f;
private Ellipse2D.Double circle;
private Color color;
private int diameter;
private float xPosition;
private float yPosition;
private final Rectangle bounds;
private Canvas canvas;
private float xSpeed;
private float ySpeed;// initial downward speed
  
Random random = new Random();
  

  
/**
* Constructor for objects of class BoxBall
*
* @param xPos the horizontal coordinate of the ball
* @param yPos the vertical coordinate of the ball
* @param xSpeed the horizontal speed of the ball
* @param ySpeed the vertical speed of the ball
*
* @param ballDiameter the diameter (in pixels) of the ball
* @param ballColor the color of the ball
* @param bounds the rectangle the ball should bounce withing
* @param drawingCanvas the canvas to draw this ball on
*/
public BoxBall(int xPos, int yPos, int xSpeed, int ySpeed, int ballDiameter, Color ballColor,
Rectangle boundingRectangle, Canvas drawingCanvas)
{
xPosition = xPos;
yPosition = yPos;
this.xSpeed = xSpeed;
this.ySpeed = ySpeed;
color = ballColor;
diameter = ballDiameter;
bounds = boundingRectangle;
canvas = drawingCanvas;
}

/**
* draw() method draws this ball at its current position onto the canvas.
**/
public void draw()
{
canvas.setForegroundColor(color);
canvas.fillCircle((int)xPosition, (int)yPosition, diameter);
}

/**
* erase() method this ball at its current position.
**/
public void erase()
{
canvas.eraseCircle((int)xPosition, (int)yPosition, diameter);
}

/**
* Move this ball according to its position and speed and redraw.
**/
public void move()
{   
// remove from canvas at the current position
erase();
  
// small amount of x friction to prevent it from sliding too long
xSpeed *= 0.99f;
  
// stop ball from moving in x when a very low value
if (Math.abs(xSpeed) < 1.0f) {
xSpeed = 0;
}
  
//#2
  
//#5
  
// compute new position
yPosition += ySpeed;
  
//#3
  
// check if it has hit the border
if(yPosition >= (bounds.getMaxY() - diameter) && ySpeed > 0) {
yPosition = (int)(bounds.getMaxY() - diameter);
ySpeed = -ySpeed * friction;
if(ySpeed > 0) {
ySpeed = 0;
}
  
}
else if(yPosition <= (bounds.getMinY()) && ySpeed < 0) {
yPosition = (int)(bounds.getMinY()) + 1;
ySpeed = -ySpeed * friction;
if(ySpeed < 0) {
ySpeed = 0;
}
}
//#4

// draw again at new position
draw();
}

/**
* return the horizontal position of this ball
*/
public float getXPosition()
{
return xPosition;
}

/**
* return the vertical position of this ball
*/
public float getYPosition()
{
return yPosition;
}
  
/**
* return true if the ball is still moving
*/
public boolean isMoving() {
return (xSpeed != 0 || ySpeed != 0);
}

}

