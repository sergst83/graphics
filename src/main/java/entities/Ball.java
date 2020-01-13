package entities;

import lombok.Getter;
import lombok.Setter;
import main.GamePanel;

import java.awt.*;

@Getter
@Setter
public class Ball {

    private int x, y, r;
    private int dx, dy;

    public Ball(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        dx = 2;
        dy = 2;
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
        System.out.println(String.format("x=%s, y=%s, r=%s", x, y, r));
    }
}
