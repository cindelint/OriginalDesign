void setup() {
  size(500,500);
  frameRate(60);
}

int screen = 0;
int shoot = 0;
int angle = 0;
float a = 0, b = 30.0;
int dtX = 200;
float x = 200, y = 360;

void draw() {
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
}

void keyPressed() {
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

void dt(float xPos, float yPos) {
  rectMode(CENTER);
  noStroke();

  pushMatrix();
  if (keyPressed && key == 'q' && angle>-9) {
    angle--;
    if (screen == 0) {
      a--;
      b-=abs(angle/3);
      x-=abs(angle/1.5);
      y+=abs(angle/2.5);
    }
  }
  if (keyPressed && key == 'e' && angle<9) {
    angle++;
    if (screen == 0) {
      a++;
      b-=abs(angle/3);
      x+=abs(angle/1.5);
      y+=abs(angle/2.5);
    }
  }



  translate(xPos,yPos-12.5);
  rotate(angle*PI/30);
  translate(-xPos,-yPos+12.5);

  fill(140, 140, 140);
  rect(xPos,yPos-20,15,40);
  fill(120,120,120);
  triangle(xPos-3,yPos,xPos+7.5,yPos,xPos+7.5,yPos-41);
  popMatrix();

  fill(188, 9, 9);
  rect(xPos,yPos,75,25);
  for (int i=-12; i<12; i++) {
    fill(188-i*6,9,9);
    rect(xPos,yPos+i,75,1);
  }

  fill(124, 113, 113);
  ellipse(xPos-26,yPos+13,20,20);
  ellipse(xPos,yPos+13.5,20,20);
  ellipse(xPos+26,yPos+13,20,20);

  fill(105, 96, 96);
  arc(xPos-26,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
  arc(xPos,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
  arc(xPos+26,yPos+13,20,20,-PI/4-(200-dtX)*PI/60,3*PI/4-(200-dtX)*PI/60);
}
