package main;

import gamestate.GameStateManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static Thread thread;

    private boolean isRunning = false;

    private int FPS = 60;
    private long targetTime = 1_000 / FPS;
    private int currentFPS;

    private GameStateManager gsm;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);

        start();
    }

    public void init() {
        gsm = new GameStateManager();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        long start;
        long elapced;
        long wait;

        while (isRunning) {
            start = System.currentTimeMillis();

            tick();
            repaint();

            elapced = System.currentTimeMillis() - start;
            wait = targetTime - elapced;

            if (wait < 0) {
                wait = 16;
            }

            currentFPS = (int) (1_000 / wait);

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        gsm.tick();
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("FPS:" + currentFPS, 2, 10);

        gsm.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        int k = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        gsm.keyPressed(k);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        gsm.keyReleased(k);
    }

}
