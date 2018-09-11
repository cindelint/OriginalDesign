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
int xPos = 200;
float x = 200, y = 360;

public void draw() {
  noStroke();
  for (int i=0; i<500; i++) {
    fill(220+i/2, 67+i/3, 60-i*2);
    rect(250,i,500,1);
  }

  fill(124, 198, 105);
  rect(250,475,520,60);
  dt(xPos,430);
  if (keyCode == UP || key == 'w') {
    screen = 1;
  }
  if (screen == 1) {
    ball();
  }
}

public void keyPressed() {
  if (keyCode == LEFT || key == 'a') {
    xPos-=10;
    if (screen == 0) {
      x-=10;
    }
  }
  if (keyCode == RIGHT || key == 'd') {
    xPos+=10;
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

public void dt(float x, float y) {
  rectMode(CENTER);
  noStroke();

  pushMatrix();
  if (keyPressed && key == 'q' && angle>-9) {
    angle--;
    if (screen == 0) {
      a--;
    }
  }
  if (keyPressed && key == 'e' && angle<9) {
    angle++;
    if (screen == 0) {
      a++;
    }
  }

  translate(x,y-12.5f);
  rotate(angle*PI/30);
  translate(-x,-y+12.5f);

  fill(140, 140, 140);
  rect(x,y-20,15,40);
  fill(120,120,120);
  triangle(x-3,y,x+7.5f,y,x+7.5f,y-41);
  popMatrix();

  fill(188, 9, 9);
  rect(x,y,75,25);
  for (int i=-12; i<12; i++) {
    fill(188-i*6,9,9);
    rect(x,y+i,75,1);
  }

  fill(124, 113, 113);
  ellipse(x-26,y+13,20,20);
  ellipse(x,y+13.5f,20,20);
  ellipse(x+26,y+13,20,20);

  fill(105, 96, 96);
  arc(x-26,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
  arc(x,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
  arc(x+26,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
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
