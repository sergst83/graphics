package gamestate;

import main.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {

    /**
     * Manages all states/levels of game
     */
    public static Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();

        states.push(new Level1State(this));
    }

    public boolean isGameOver() {
        boolean isGameOver = states.peek().isGameOver();
        if (isGameOver) {
            states.pop();
        }
        return isGameOver;
    }

    public void tick() {
        if (!states.isEmpty()) {
            states.peek().tick();
        }
    }

    public void draw(Graphics g) {
        if (!states.isEmpty()) {
            states.peek().draw(g);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 50));
            g.drawString("Game Over", GamePanel.WIDTH / 2 - 150, GamePanel.HEIGHT / 2);
        }
    }

    public void keyPressed(int k) {
        if (!states.isEmpty()) {
            states.peek().keyPressed(k);
        }
    }

    public void keyReleased(int k) {
        if (!states.isEmpty()) {
            states.peek().keyReleased(k);
        }
    }
}
