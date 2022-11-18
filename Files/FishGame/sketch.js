//Final variables
let fishWidth = 45
let fishHeight = 25
let bucketWidth = 39*4
let bucketHeight = 18*4
let bucketX;

//Setup variables
let bucketImg;
let fishColors = [];

//Changing variables
let score = 0;
let currentFish = null;
let currentIndex = 0;
let fishes = [];
let xOffset = 0.0;
let yOffset = 0.0;

function preload() {
  bucketImg = loadImage("Images/Basket.png");
  redFish = loadImage("Images/Red Fish.png");
  orangeFish = loadImage("Images/Orange Fish.png");
  yellowFish = loadImage("Images/Yellow Fish.png");
    greenFish = loadImage("Images/Green Fish.png");
  purpleFish = loadImage("Images/Purple Fish.png");
  fishColors = [redFish, orangeFish, yellowFish, greenFish, purpleFish]
}

function setup() {
  createCanvas(720, 400)
  bucketX = width/2-bucketWidth
}

function draw() {
  // Try to spawn a new fish
  if(random() > .99) {
    fishes.push(new Fish())
  }
  
  //Draws background, fish, bucket, and score
  background(35, 150, 250);
  fishes.forEach(function(fish) {
    fish.update();
    push()
    var scl = fish.scl
    scale(scl, 1)
    image(fish.color, scl*fish.x, fish.y, fishWidth, fishHeight)
    pop()
  })
  image(bucketImg, bucketX, 25, bucketWidth, bucketHeight);
  push()
  textSize(bucketHeight/4)
  text("Score: " + score, 25, 25+bucketHeight/4)
  pop()
}

function mousePressed() {
  var i = 0
  fishes.forEach(function(fish) {
    var hover =
      mouseX > fish.x - fishWidth &&
      mouseX < fish.x + fishWidth &&
      mouseY > fish.y - fishHeight &&
      mouseY < fish.y + fishHeight
    if(hover && currentFish == null) {
      currentFish = fish
      currentIndex = i;
      fish.lock = true
      xOffset = mouseX - fish.x
      yOffset = mouseY - fish.y
    }
    i++;
  })
}

function mouseDragged() {
  if (currentFish != null) {
    currentFish.x = mouseX - xOffset;
    currentFish.y = mouseY - yOffset;
  }
}

function mouseReleased() {
  //Checks if fish is in bucket
  if (currentFish != null) {
    if(currentFish.x > bucketX-10 &&
      currentFish.x < width/2+10 &&
      currentFish.y > 15 &&
      currentFish.y < 85)
    {
      score++;
      fishes.splice(currentIndex, 1)
      currentFish = null;
      currentIndex = 0;
    }
    else {
      currentIndex = 0;
      currentFish.lock = false
      currentFish = null
    }
  }
}


class Fish {
  constructor() {
    this.y = random(50, height-fishHeight/2)
    do {
    this.speed = random(-2,2)
    } while (this.speed < 1.0 && this.speed > -1.0)
    if(this.speed < 0) {
      this.x = width;
      this.scl = 1;
    }
    else if (this.speed > 0) {
      this.x = 0;
      this.scl = -1;
    }
    this.color = random(fishColors)
    this.lock = false
  }
  
  update() {
    if(!this.lock) {
      this.x += this.speed
    }
  }
  
  display() {
    
  }
}