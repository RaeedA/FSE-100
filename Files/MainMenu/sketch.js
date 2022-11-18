let kidImg;
let gearImg;
let cloudImg;


function preload(){
  kidImg = loadImage("UIMainMenuKid.png");
  gearImg = loadImage("Cartoon_Gear2.jpg");
  cloudImg = loadImage("Cloud_Background.webp")
  
}

function setup() {
  createCanvas(720, 400);
}

function draw() {
//Draws Background
  background(162, 218, 255);
  image(cloudImg, 0, 0, 720, 400);
//Draws clickable elements
//Draws snake Game Box
  fill(255, 255, 255);
  rect(25, 75, 350, 75);
  noFill();
//Snake Game text
  fill(100, 100, 100);
  textSize(55);
  text("Snake Game", 30, 130);
  noFill();
//Second box
  fill(255, 255, 255);
  rect(25, 175, 350, 75);
//Fish Game text
  noFill();
  fill(100, 100, 100);
  textSize(55);
  text("Fish Game", 30, 230);
  noFill();
//Simon Says game box
  fill(255, 255, 255);
  rect(25, 275, 350, 75);
  noFill();
//Simon Says text
  fill(100, 100, 100);
  textSize(55);
  text("Simon Says", 30, 330);
  noFill();
//Draws Image
  image(kidImg, 440, 100, 200, 200);
//Draws settings gea
  //image(gearImg, 500, 25, 25, 25);
}
function mousePressed(){
//Checks if the mosue is over the box 
  if (mouseX < 275  &&
      mouseX > 25   &&
      mouseY < 150  &&
      mouseY > 70
     ) {
    window.open("http://127.0.0.1:5500/Files/SnakeGame/");
  }
  if (mouseX < 275  &&
      mouseX > 25   &&
      mouseY < 250  &&
      mouseY > 170
     ) {
    window.open("http://127.0.0.1:5500/Files/FishGame/");
  }
  if (mouseX < 275  &&
      mouseX > 25   &&
      mouseY < 350  &&
      mouseY > 270
     ) {
    window.open("http://127.0.0.1:5500/Files/SimonSays/");
  }
}