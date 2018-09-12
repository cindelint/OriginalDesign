import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class OriginalDesign extends PApplet {

public void setup() {
  
  frameRate(60);
}

int screen = 0;
int shoot = 0;
int angle = 0;
float a = 0, b = 30.0f;
int dtX = 200;
float x = 200, y = 360;

public void draw() {
  noStroke();
  for (int i=0; i<500; i++) {
    fill(230+i/4, 60+i/3, 200-i/4);
    rect(250,i,500,1);
  }

  fill(124, 198, 105);
  rect(250,475,520,60);
  dt(dtX,430);
  if (keyCode == UP || key == 'w') {
    screen = 1;
  }
  if (screen == 1) {
    ball();
  }
  if (key == 'c') {
    screen = 2;
  }
  if (screen == 2) {
    ball();
  }
}

public void keyPressed() {
  if (keyCode == LEFT || key == 'a') {
    dtX-=10;
    if (screen == 0) {
      x-=10;
    }
  }
  if (keyCode == RIGHT || key == 'd') {
    dtX+=10;
    if (screen == 0) {
      x+=10;
    }
  }
}

public void ball() {

  fill(0xffFFF534);
  noStroke();
  ellipse(x,y,30,30);

  //bouncing off left and right
  if (x<0 || x>500) {
    a = -a;
  }

  //going up the first time
  if (shoot == 0) {
    y-=b;
    b = b/1.1f;
    if (b<=0.5f) {
      shoot++;
    }
  }

  //bouncing
  if (shoot == 1) {
    y+=b;
    if (y>=425 && b+b/1.2f>1.1f) {
      b = -b;
    }

    if (b<0.5f && b>-0.5f) {
      b = -b;
      b++;
    }

    if (b<0) {
      b = b/1.15f;
    } else {
      b = b/0.9f;
    }
  }
  x+=a;
  //println("screen value is " + screen);
  //println("y value is " + y);
  //println("b value is " + b);
  //println("");
}

public void dt(float xPos, float yPos) {
  rectMode(CENTER);
  noStroke();

  pushMatrix();
  if (keyPressed && key == 'q' && angle>-9) {
    angle--;
    if (screen == 0) {
      a--;
      b-=abs(angle/3);
      x-=abs(angle/1.5f);
      y+=abs(angle/2.5f);
    }
  }
  if (keyPressed && key == 'e' && angle<9) {
    angle++;
    if (screen == 0) {
      a++;
      b-=abs(angle/3);
      x+=abs(angle/1.5f);
      y+=abs(angle/2.5f);
    }
  }



  translate(xPos,yPos-12.5f);
  rotate(angle*PI/30);
  translate(-xPos,-yPos+12.5f);

  fill(140, 140, 140);
  rect(xPos,yPos-20,15,40);
  fill(120,120,120);
  triangle(xPos-3,yPos,xPos+7.5f,yPos,xPos+7.5f,yPos-41);
  popMatrix();

  fill(188, 9, 9);
  rect(xPos,yPos,75,25);
  for (int i=-12; i<12; i++) {
    fill(188-i*6,9,9);
    rect(xPos,yPos+i,75,1);
  }

  fill(124, 113, 113);
  ellipse(xPos-26,yPos+13,20,20);
  ellipse(xPos,yPos+13.5f,20,20);
  ellipse(xPos+26,yPos+13,20,20);

  fill(105, 96, 96);
  arc(xPos-26,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
  arc(xPos,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
  arc(xPos+26,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "OriginalDesign" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
