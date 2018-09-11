void setup() {
  size(500,500);
  frameRate(60);
}
int screen = 0;
int shoot = 0;
int angle = 0;
float a = angle, b = 30.0;
int xPos = 200;
float x = 200, y = 360;

void draw() {
  background(#B7D3E8);
  strokeWeight(4);
  stroke(0,0,0);
  line(0,450,500,450);
  dt(xPos,430);
  if (keyCode == UP || key == 'w') {
    screen = 1;
  }
  if (screen == 1) {
    ball();
  }
}

void keyPressed() {
  if (keyCode == LEFT || key == 'a') {
    xPos-=10;
    x-=10;
  }
  if (keyCode == RIGHT || key == 'd') {
    xPos+=10;
    x+=10;
  }
}

void ball() {

  fill(#FFF534);
  noStroke();
  ellipse(x,y,30,30);

  //bouncing off left and right
  if (x<0 || x>500) {
    a = -a;
  }

  //going up the first time
  if (shoot == 0) {
    y-=b;
    b = b/1.1;
    if (b<=0.5) {
      shoot++;
    }
  }

  //bouncing
  if (shoot == 1) {
    y+=b;
    if (y>=425 && b+b/1.2>1.1) {
      b = -b;
    }

    if (b<0.5 && b>-0.5) {
      b = -b;
      b++;
    }

    if (b<0) {
      b = b/1.15;
    } else {
      b = b/0.9;
    }
  }
  x+=a;
  //println("screen value is " + screen);
  //println("y value is " + y);
  //println("b value is " + b);
  //println("");
}

void dt(float x, float y) {
  rectMode(CENTER);
  noStroke();

  pushMatrix();
  if (keyPressed && key == 'q' && angle>-9) {
    angle--;
    a--;
  }
  if (keyPressed && key == 'e' && angle<9) {
    angle++;
    a++;
  }

  translate(x,y-12.5);
  rotate(angle*PI/30);
  translate(-x,-y+12.5);

  fill(140, 140, 140);
  rect(x,y-20,15,40);
  fill(120,120,120);
  triangle(x-3,y,x+7.5,y,x+7.5,y-42.5);
  popMatrix();

  fill(188, 9, 9);
  rect(x,y,75,25);
  for (int i=-12; i<12; i++) {
    fill(188-i*6,9,9);
    rect(x,y+i,75,1);
  }

  fill(124, 113, 113);
  ellipse(x-26,y+13,20,20);
  ellipse(x,y+13.5,20,20);
  ellipse(x+26,y+13,20,20);

  fill(105, 96, 96);
  arc(x-26,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
  arc(x,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
  arc(x+26,y+13,20,20,-PI/4-(200-xPos)*PI/60,3*PI/4-(200-xPos)*PI/60);
}
