package gamestate;

import gamestate.GameState;

import java.awt.*;
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

    public void tick() {
        if (states.size() > 0) {
            states.peek().tick();
        }
    }

    public void draw(Graphics g) {
        if (states.size() > 0) {
            states.peek().draw(g);
        }
    }

    public void keyPressed(int k) {
        if (states.size() > 0) {
            states.peek().keyPressed(k);
        }
    }

    public void keyReleased(int k) {
        if (states.size() > 0) {
            states.peek().keyReleased(k);
        }
    }
}
