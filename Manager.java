public class Manager {
    ///
    private Text OpeningText, LeftplayInfo, RightplayInfo;
    private Ball yellowBall, leftgoal, rightgoal;
    private GameArena gameplace;
    private Rectangle OutsideRectangle, InsideRectangle;
    private Magnet whiteop1, whiteop2, whiteop3;
    private Player leftblackob, rightblackob;
    private Line MiddleLine;
    private ServeArea leftTopserve, rightTopserve, rightDownserve, leftDownserve;
    private int speed = 1;
    private boolean served = false;
    private int servePosition = 00;
    private String servePlayer = "left";
    private int lefplayerscore = 00;
    private int rightplayerscore = 00;

    public Manager() {
        gameplace = new GameArena(800, 500, true);
        init();
        start();
    }

    //this method begins the loop for the game
    private void start() {

        placeServe();

        while (true) {

            gameLoopLatency(5);

            if (!served) {
                serveControls();
            } else {
                updateRightBat();
                updateLeftBat();
                ballMove();
                magnetCollideCheck();
                gameOverCheck();
            }
        }
    }
    
    /**
     * 
     * with this method your able to add latency to the game
     * @param millis 
     */

    private void gameLoopLatency(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //this method ensures the update of the left black objection(bat) postion
    private void updateLeftBat() {
        double leftBatX = leftblackob.getXPosition();
        double leftBatY = leftblackob.getYPosition();

        if (gameplace.letterPressed('s')) {
            if (leftblackob.getYPosition() + speed < 400) leftblackob.move(0, speed);
        }
        if (gameplace.letterPressed('w')) {
            if (leftblackob.getYPosition() - speed > 100) leftblackob.move(0, -speed);
        }
        if (gameplace.letterPressed('a')) {
            if (leftblackob.getXPosition() - speed > 100) leftblackob.move(-speed, 0);
        }
        if (gameplace.letterPressed('d')) {
            if (leftblackob.getXPosition() + speed < 400) leftblackob.move(speed, 0);
        }

        leftblackob.setDirection(leftblackob.getXPosition() - leftBatX, leftblackob.getYPosition() - leftBatY);
    }
    //this method ensures the update of the right black objection(bat) postion
    private void updateRightBat() {
        double rightBatX = rightblackob.getXPosition();
        double rightBatY = rightblackob.getYPosition();

        if (gameplace.downPressed()) {
            if (rightblackob.getYPosition() + speed < 400) rightblackob.move(0, speed);
        }
        if (gameplace.upPressed()) {
            if (rightblackob.getYPosition() - speed > 100) rightblackob.move(0, -speed);
        }
        if (gameplace.leftPressed()) {
            if (rightblackob.getXPosition() - speed > 400) rightblackob.move(-speed, 0);
        }
        if (gameplace.rightPressed()) {
            if (rightblackob.getXPosition() + speed < 700) rightblackob.move(speed, 0);
        }

        rightblackob.setDirection(rightblackob.getXPosition() - rightBatX, rightblackob.getYPosition() - rightBatY);
    }

    //this methods handels the serve time controls
    private void serveControls() {
        if (servePlayer.equals("left") && gameplace.letterPressed('w')) {
            leftblackob.setXPosition(120);
            leftblackob.setYPosition(120);
            yellowBall.setXPosition(130);
            yellowBall.setYPosition(130);
        }

        if (servePlayer.equals("left") && gameplace.letterPressed('s')) {
            leftblackob.setXPosition(120);
            leftblackob.setYPosition(380);
            yellowBall.setXPosition(130);
            yellowBall.setYPosition(370);
        }

        if (servePlayer.equals("right") && gameplace.upPressed()) {
            rightblackob.setXPosition(680);
            rightblackob.setYPosition(120);
            yellowBall.setXPosition(670);
            yellowBall.setYPosition(130);
        }

        if (servePlayer.equals("right") && gameplace.downPressed()) {
            rightblackob.setXPosition(680);
            rightblackob.setYPosition(380);
            yellowBall.setXPosition(670);
            yellowBall.setYPosition(370);
        }
        if (gameplace.spacePressed()) {
            served = true;
        }
    }

    //this methods operates the mangnet collsion with the black objects(bat)
    private void magnetCollideCheck() {
        if (leftblackob.collidesWithMagnet(whiteop1)) {
            whiteop1.setXPosition(leftblackob.getXPosition());
            whiteop1.setYPosition(leftblackob.getYPosition());
            leftblackob.AdditionMagnets(1);
        }
        if (leftblackob.collidesWithMagnet(whiteop2)) {
            whiteop2.setXPosition(leftblackob.getXPosition());
            whiteop2.setYPosition(leftblackob.getYPosition());
            leftblackob.AdditionMagnets(2);
        }
        if (leftblackob.collidesWithMagnet(whiteop3)) {
            whiteop3.setXPosition(leftblackob.getXPosition());
            whiteop3.setYPosition(leftblackob.getYPosition());
            leftblackob.AdditionMagnets(3);
        }
        if (rightblackob.collidesWithMagnet(whiteop1)) {
            whiteop1.setXPosition(rightblackob.getXPosition());
            whiteop1.setYPosition(rightblackob.getYPosition());
            rightblackob.AdditionMagnets(1);
        }
        if (rightblackob.collidesWithMagnet(whiteop2)) {
            whiteop2.setXPosition(rightblackob.getXPosition());
            whiteop2.setYPosition(rightblackob.getYPosition());
            rightblackob.AdditionMagnets(2);
        }
        if (rightblackob.collidesWithMagnet(whiteop3)) {
            whiteop3.setXPosition(rightblackob.getXPosition());
            whiteop3.setYPosition(rightblackob.getYPosition());
            rightblackob.AdditionMagnets(3);
        }
        if (leftblackob.GettingMagnets() == 2) {
            servePlayer = "left";
            rightplayerscore++;
            RightplayInfo.setText(String.valueOf(rightplayerscore));
            placeServe();
        } else if (rightblackob.GettingMagnets() == 2) {
            servePlayer = "right";
            lefplayerscore++;
            LeftplayInfo.setText(String.valueOf(lefplayerscore));
            placeServe();
        }
    }

    //this methods inspects if one of the players have reached 10 points, if this is the case the player wins
    private void gameOverCheck() {
        if (lefplayerscore == 10 || rightplayerscore == 10) {
            while (true) {
                gameLoopLatency(1);

                if (gameplace.enterPressed()) {

                    lefplayerscore = 0;
                    rightplayerscore = 0;
                    LeftplayInfo.setText(String.valueOf(lefplayerscore));
                    RightplayInfo.setText(String.valueOf(rightplayerscore));
                    break;
                }
            }
        }
    }



    //this methods is used to ensure the game part in serving postion
    private void placeServe() {
        served = false;

        whiteop1.setXPosition(400);
        whiteop1.setYPosition(180);

        whiteop2.setXPosition(400);
        whiteop2.setYPosition(250);

        whiteop3.setXPosition(400);
        whiteop3.setYPosition(320);


        rightblackob.clear();
        leftblackob.clear();

        if (servePlayer.equals("left")) {
            yellowBall.setDirection(2, 2);
            leftblackob.setXPosition(120);
            leftblackob.setYPosition(120);
            yellowBall.setXPosition(130);
            yellowBall.setYPosition(130);
            rightblackob.setXPosition(500);
            rightblackob.setYPosition(250);
        } else {
            yellowBall.setDirection(-2, -2);
            rightblackob.setXPosition(680);
            rightblackob.setYPosition(380);
            yellowBall.setXPosition(670);
            yellowBall.setYPosition(370);
            leftblackob.setXPosition(300);
            leftblackob.setYPosition(250);
        }
    }
    //methos to update yellowBall position
    private void ballMove() {

        ballCollideCheck();

        yellowBall.move(yellowBall.getDx(), yellowBall.getDy());
    }

    //this methods starts all game parts
    private void init() {

        OutsideRectangle = new Rectangle(80, 80, 640, 340, "GREY");
        InsideRectangle = new Rectangle(100, 100, 600, 300, "BLUE");

        gameplace.addRectangle(OutsideRectangle);
        gameplace.addRectangle(InsideRectangle);

        OpeningText = new Text("Welcome to Klask v1!", 14, 100, 50, "WHITE");
        gameplace.addText(OpeningText);

        MiddleLine = new Line(400, 100, 400, 400, 1, "GREY");
        gameplace.addLine(MiddleLine);

        leftgoal = new Ball(140, 250, 30, "GREY");
        gameplace.addBall(leftgoal);

        rightgoal = new Ball(660, 250, 30, "GREY");
        gameplace.addBall(rightgoal);

        LeftplayInfo = new Text("0", 20, 50, 260, "WHITE");
        gameplace.addText(LeftplayInfo);

        RightplayInfo = new Text("0", 20, 740, 260, "WHITE");
        gameplace.addText(RightplayInfo);

        leftTopserve = new ServeArea(50, 50, 100, 100, 250, 120, "GREY", 5);
        gameplace.addSemiCircle(leftTopserve);

        rightTopserve = new ServeArea(650, 50, 100, 100, 280, -120, "GREY", 5);
        gameplace.addSemiCircle(rightTopserve);

        rightDownserve = new ServeArea(650, 350, 100, 100, 200, -120, "GREY", 5);
        gameplace.addSemiCircle(rightDownserve);

        leftDownserve = new ServeArea(50, 350, 100, 100, 350, 120, "GREY", 5);
        gameplace.addSemiCircle(leftDownserve);

        leftblackob = new Player(300, 250, 20, "BLACK");
        gameplace.addBat(leftblackob);

        rightblackob = new Player(500, 250, 20, "BLACK");
        gameplace.addBat(rightblackob);

        yellowBall = new Ball(130, 130, 10, "YELLOW");
        gameplace.addBall(yellowBall);

        whiteop1 = new Magnet(400, 180, 5, "WHITE");
        gameplace.addBall(whiteop1);

        whiteop2 = new Magnet(400, 250, 5, "WHITE");
        gameplace.addBall(whiteop2);

        whiteop3 = new Magnet(400, 320, 5, "WHITE");
        gameplace.addBall(whiteop3);
    }

    //Method operates the yellowBall collsion with black object(bat), mangnets and wall
    private void ballCollideCheck() {

        Ball tmpBall = new Ball(yellowBall.getXPosition(), yellowBall.getYPosition(), yellowBall.getSize(), yellowBall.getColour());
        tmpBall.move(yellowBall.getDx(), yellowBall.getDy());

        if (tmpBall.getXPosition() <= 100 + tmpBall.getSize() || tmpBall.getXPosition() >= 700 - tmpBall.getSize() ||
                tmpBall.getYPosition() <= 100 + tmpBall.getSize() || tmpBall.getYPosition() >= 400 - tmpBall.getSize()) {
            if (tmpBall.getXPosition() <= 100 + tmpBall.getSize() || tmpBall.getXPosition() >= 700 - tmpBall.getSize()) {
                yellowBall.setDirection(-yellowBall.getDx(), yellowBall.getDy());
            }
            if (tmpBall.getYPosition() <= 100 + tmpBall.getSize() || tmpBall.getYPosition() >= 400 - tmpBall.getSize()) {
                yellowBall.setDirection(yellowBall.getDx(), -yellowBall.getDy());
            }

            yellowBall.setDirection(yellowBall.getDx() * 0.95, yellowBall.getDy() * 0.95);
        } else if (tmpBall.collides(leftblackob) || tmpBall.collides(rightblackob)) {
            if (tmpBall.collides(leftblackob)) {
                yellowBall.setDirection(-yellowBall.getDx() + leftblackob.getDx(), -yellowBall.getDy() + leftblackob.getDy());
            } else {
                yellowBall.setDirection(-yellowBall.getDx() + rightblackob.getDx(), -yellowBall.getDy() + rightblackob.getDy());
            }
        } else if (yellowBall.collides(whiteop1) || yellowBall.collides(whiteop2) || yellowBall.collides(whiteop3)) {
            if (yellowBall.collides(whiteop1)) {
                whiteop1.move(yellowBall.getDx() * 2, yellowBall.getDy() * 2);
            } else if (yellowBall.collides(whiteop2)) {
                whiteop2.move(yellowBall.getDx() * 2, yellowBall.getDy() * 2);
            } else {
                whiteop3.move(yellowBall.getDx() * 2, yellowBall.getDy() * 2);
            }

            yellowBall.setDirection(-yellowBall.getDx(), -yellowBall.getDy());
        }
    }

}
