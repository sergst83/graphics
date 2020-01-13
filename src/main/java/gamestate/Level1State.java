package gamestate;

import entities.Ball;
import entities.Brick;
import entities.Player;
import main.GamePanel;
import phisics.Collision;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Level1State extends GameState {

    private Player player;
    private Ball ball;
    private List<Brick> bricks;

    private Collision collision;

    public Level1State(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        player = new Player();
        ball = new Ball(GamePanel.WIDTH / 2 - 5, GamePanel.HEIGHT / 2 - 5, 5);
        bricks = new ArrayList<>();

        int[][] ids = new int[8][14];
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids[i].length; j++) {
                if (i <= 1) {
                    ids[i][j] = 4;
                }

                if (i <= 3 && i >= 2) {
                    ids[i][j] = 3;
                }

                if (i <= 5 && i >= 4) {
                    ids[i][j] = 2;
                }

                if (i >= 6) {
                    ids[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids[i].length; j++) {
                bricks.add(new Brick(j * 55 + 10, i * 15 + 20, ids[i][j]));
            }
        }
        
        collision = new Collision();
    }

    @Override
    public void tick() {
        player.tick();
        ball.tick();

        collision.paddleBall(player, ball);

        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            collision.ballBrick(ball, brick);

            if (brick.getHealth() <= 0) {
                bricks.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        player.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        player.keyReleased(k);
    }

    @Override
    public boolean isGameOver() {
        return collision != null && ball !=null && collision.ballIsDown(ball);
    }
}
