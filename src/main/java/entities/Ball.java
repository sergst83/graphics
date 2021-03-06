package entities;

import lombok.Getter;
import lombok.Setter;
import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics;

@Getter
@Setter
public class Ball {

    private int x, y, r;
    private int dx, dy;

    public Ball(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        dx = 1;
        dy = 1;
    }

    public void tick() {
        if (x + r >= GamePanel.WIDTH || x - r <= 0) {
            dx *= -1;
        }

        if (y - r <= 0) {
            dy *= -1;
        }

        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
    }
}
