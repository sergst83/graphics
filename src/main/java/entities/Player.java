package entities;

import lombok.Getter;
import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

@Getter
public class Player extends Rectangle {
    private static final long serialVersionUID = 1L;

    private boolean left = false;
    private boolean right = false;
    private int moveSpeed = 2;

    private Rectangle topL, topC, topR;
    private Rectangle sideL, sideR;

    public Player() {
        setBounds(GamePanel.WIDTH / 2 - 90, GamePanel.HEIGHT - 30, 180, 20);

        topL = new Rectangle(x, y - 1, width / 3, 1);
        topC = new Rectangle(x + width / 2, y - 1, width / 3, 1);
        topR = new Rectangle(x + 2 * (width / 3), y - 1, width / 3, 1);
        sideL = new Rectangle(x - 1, y, 1, height);
        sideR = new Rectangle(x + width, y, 1, height);
    }

    private void calculateEdges() {
        topL.setBounds(x, y, width / 3, 1);
        topC.setBounds(x + width / 2, y, width / 3, 1);
        topR.setBounds(x + 2 * (width / 3), y, width / 3, 1);
        sideL.setBounds(x - 1, y, 1, height);
        sideR.setBounds(x + width, y, 1, height);
    }

    public void tick() {
        if (x <= 0) {
            left = false;
        }

        if (x + width >= GamePanel.WIDTH) {
            right = false;
        }

        if (left) {
            x -= moveSpeed;
        }

        if (right) {
            x += moveSpeed;
        }

        calculateEdges();
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);

        // show parts of paddle/player
/*
        g.setColor(Color.BLUE);
        g.fillRect(topL.x, topL.y, topL.width, topL.height);
        g.setColor(Color.RED);
        g.fillRect(topC.x, topC.y, topC.width, topC.height);
        g.setColor(Color.ORANGE);
        g.fillRect(topR.x, topR.y, topR.width, topR.height);
        g.setColor(Color.CYAN);
        g.fillRect(sideL.x, sideL.y, sideL.width, sideL.height);
        g.setColor(Color.YELLOW);
        g.fillRect(sideR.x, sideR.y, sideR.width, sideR.height);
*/
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (k == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (k == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

}
