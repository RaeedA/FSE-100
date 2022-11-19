let jumpImg;
let cloudImg;
let headImg;
let shoulderImg;
let legImg;
let i;
let counter = 0;
let secondCounter = 0;
let seperateCounter = 0;
let showImage = false;
let firstSqaure;
let secondSquare;
let thirdSquare;
let fourthSquare;

function preload(){
  jumpImg = loadImage("Person_Jumping.webp");
  cloudImg = loadImage("Cloud_Background.webp");
  headImg = loadImage("Man_touching_Head.jpg");
  shoulderImg = loadImage("Shoulder_Stretch.jpg")
  legImg = loadImage("Leg_Stretch.jpg");
  
}

function setup() {
  createCanvas(720, 400);
}

function draw() {
  seperateCounter++;
  i = int(random(1,5));
  secondCounter = secondCounter + 5;
  background(162, 218, 255);
  image(cloudImg, 0, 0, 720, 400);
  /*if(seperateCounter < 50) {
    textSize(35, 35);
    text("Booting...", 25, 25);
  }
  */
//First Box
  firstSquare = rect(360, 200, 150, 150);
//Second Box
  secondSquare = rect(160, 200, 150, 150);
//Third Box
  thirdSquare = rect(160, 25, 150, 150);
//Fourth Box
  //rect(360, 25, 150, 150);
  fourthSquare = rect(360, 25, 150, 150);
  
  for(let a = 0; a < 200; a++)  {
    imageFlash(i);
    print(a);
  }

  

  print("i: " + i);
  print("counter: " + counter);
}

//Makes the Image flicker
function imageFlash(i) {
  if (i == 1) {
    strokeWeight(4);
    stroke(100, 378, 782);
    textSize(35, 35);
    text("JUMP!", 550, 200);
    noStroke();
    image(jumpImg, 360, 200, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 2) {
    strokeWeight(4);
    stroke(100, 378, 782);
    textSize(35, 35);
    textWrap(WORD);
    text("Touch Your Head!", 550, 200, 100);
    noStroke();
    image(headImg, 160, 200, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 3) {
    strokeWeight(4);
    stroke(100, 378, 782);
    textSize(35, 35);
    textWrap(WORD);
    text("Touch Your Shoulders!", 550, 200, 100);
    noStroke();
    image(shoulderImg, 160, 25, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 4) {
    strokeWeight(4);
    stroke(100, 378, 782);
    textSize(35, 35);
    textWrap(WORD);
    text("Rais Your Leg!", 550, 200,100);
    noStroke();
    image(legImg, 360, 25, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
}