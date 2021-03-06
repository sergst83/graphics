package phisics;

import entities.Ball;
import entities.Brick;
import entities.Player;
import main.GamePanel;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Collision {

    private int bX, bY, bR;

    private boolean ballPaddle(Rectangle r, Point p) {
        return r.contains(p);
    }

    private boolean brickBall(Brick b, Point p) {
        return b.contains(p);
    }

    private boolean brickBall(Brick brick, Ball ball) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(ball.getX() - ball.getR(), ball.getY() - ball.getR(), 2 * ball.getR(), 2 * ball.getR());
        return ellipse2D.intersects(brick);
    }

    public void paddleBall(Player player, Ball ball) {
        bX = ball.getX();
        bY = ball.getY();
        bR = ball.getR();

        if (ballPaddle(player.getTopL(), new Point(bX, bY + bR))) {
            ball.setDy(ball.getDy() * -1);
            ball.setDx(-1);
        }

        if (ballPaddle(player.getTopC(), new Point(bX, bY + bR))) {
            ball.setDy(ball.getDy() * -1);
            ball.setDx(0);
        }

        if (ballPaddle(player.getTopR(), new Point(bX, bY + bR))) {
            ball.setDy(ball.getDy() * -1);
            ball.setDx(1);
        }

        if (ballPaddle(player.getSideL(), new Point(bX + bR, bY))) {
            ball.setDx(ball.getDx() * -1);
        }

        if (ballPaddle(player.getSideR(), new Point(bX - bR, bY))) {
            ball.setDx(ball.getDx() * -1);
        }
    }

    public void ballBrick(Ball ball, Brick brick) {
        bX = ball.getX();
        bY = ball.getY();
        bR = ball.getR();

        if (brickBall(brick, new Point(bX + bR, bY)) || brickBall(brick, new Point(bX - bR, bY))) {
            brick.setHealth(brick.getHealth() - 1);
            ball.setDx(ball.getDx() * -1);
        } else if (brickBall(brick, new Point(bX, bY + bR)) || brickBall(brick, new Point(bX, bY - bR))) {
            brick.setHealth(brick.getHealth() - 1);
            ball.setDy(ball.getDy() * -1);
        } else if (brickBall(brick, ball)) {
            brick.setHealth(brick.getHealth() - 1);
            ball.setDx(ball.getDx() * -1);
            ball.setDy(ball.getDy() * -1);
        }
    }

    public boolean ballIsDown(Ball ball) {
        bX = ball.getX();
        bY = ball.getY();
        bR = ball.getR();

        // ball is down
        return bY - bR >= GamePanel.HEIGHT;
    }
}
