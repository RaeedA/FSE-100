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
    textSize(40, 40);
    text("JUMP!", 500, 200);
    image(jumpImg, 360, 200, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 2) {
    textSize(40, 40);
    text("Touch Your Head!", 500, 200);
    image(headImg, 160, 200, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 3) {
    textSize(40, 40);
    text("Touch Your Shoulders!", 500, 200);
    image(shoulderImg, 160, 25, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
  if (i == 4) {
    textSize(40, 40);
    text("LEG!", 500, 200);
   image(legImg, 360, 25, 150, 150);
    counter++;
    if (counter > 250) {
      rect(360, 200, 150, 150);
    }
    counter = 0;
  }
}