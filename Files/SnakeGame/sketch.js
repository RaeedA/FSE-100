// the snake is divided into small segments, which are drawn and edited on each 'draw' call
let numSegments = 2;
let up = 2;
let right = 3;
let down = 4;
let left = 5;
let currentDirection = right;
let inputDirection = right;
let nextDirection = right;
let score = 0;

const xStart = 0; //starting x coordinate for snake
const yStart = 250; //starting y coordinate for snake
const diff = 10;

let xCor = [];
let yCor = [];

let xFruit = 0;
let yFruit = 0;
let scoreElem;
let kidImg;
let redImg;
let greerImg;

function preload(){
  kidImg = loadImage("UIMainMenuKid.png");
  redImg = loadImage("Red_Square.png");
  greerImg = loadImage("Green_Square.jpg");
}

function setup() {
  scoreElem = createDiv('Score: 0');
  scoreElem.position(20, 20);
  scoreElem.id = 'score';
  scoreElem.style('color', 'white');

  createCanvas(500, 500);
  frameRate(15);
  stroke(255);
  strokeWeight(10);
  updateFruitCoordinates();

  for (let i = 0; i < numSegments; i++) {
    xCor.push(xStart + i * diff);
    yCor.push(yStart);
  }
}

function draw() {
  background(0);
  for (let i = 0; i < numSegments; i++) {
    image(greerImg, xCor[i] - 5, yCor[i] - 5, 10, 10);
  }
  updateSnakeCoordinates();
  checkGameStatus();
  checkForFruit();
  image(redImg, xFruit - 5, yFruit - 5, 10, 10);
  
}

function updateSnakeCoordinates() {
  for (let i = 0; i < numSegments - 1; i++) {
    xCor[i] = xCor[i + 1];
    yCor[i] = yCor[i + 1];
  }
  currentDirection = nextDirection
  switch (currentDirection) {
    case right:
      xCor[numSegments - 1] = xCor[numSegments - 2] + diff;
      yCor[numSegments - 1] = yCor[numSegments - 2];
      break;
    case up:
      xCor[numSegments - 1] = xCor[numSegments - 2];
      yCor[numSegments - 1] = yCor[numSegments - 2] - diff;
      break;
    case left:
      xCor[numSegments - 1] = xCor[numSegments - 2] - diff;
      yCor[numSegments - 1] = yCor[numSegments - 2];
      break;
    case down:
      xCor[numSegments - 1] = xCor[numSegments - 2];
      yCor[numSegments - 1] = yCor[numSegments - 2] + diff;
      break;
  }
}

function checkGameStatus() {
  if (
    xCor[xCor.length - 1] > width ||
    xCor[xCor.length - 1] < 0 ||
    yCor[yCor.length - 1] > height ||
    yCor[yCor.length - 1] < 0 ||
    checkSnakeCollision()
  ) {
    noLoop();
    scoreElem.html('Game over! Score: ' + score);
  }
}

function checkSnakeCollision() {
  const snakeHeadX = xCor[xCor.length - 1];
  const snakeHeadY = yCor[yCor.length - 1];
  for (let i = 0; i < xCor.length - 1; i++) {
    if (xCor[i] === snakeHeadX && yCor[i] === snakeHeadY) {
      return true;
    }
  }
}

function checkForFruit() {
  image(redImg, xFruit - 5, yFruit - 5, 10, 10);
  if (xCor[xCor.length - 1] === xFruit && yCor[yCor.length - 1] === yFruit)
  {
    score++;
    scoreElem.html('Score: ' + score);
    xCor.unshift(xCor[0]);
    yCor.unshift(yCor[0]);
    numSegments++;
    updateFruitCoordinates();
  }
}

function updateFruitCoordinates() {
  xFruit = floor(random(10, (width - 100) / 10)) * 10;
  yFruit = floor(random(10, (height - 100) / 10)) * 10;
}

function keyPressed() {
  switch (keyCode) {
    case 65:
      inputDirection = left;
      break;
    case 68:
      inputDirection = right;
      break;
    case 87:
      inputDirection = up;
      break;
    case 83:
      inputDirection = down;
      break;
  }
  if ((currentDirection % 2 != inputDirection % 2)) {
    nextDirection = inputDirection;
  }
}
