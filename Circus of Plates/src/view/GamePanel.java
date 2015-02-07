package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import state.StateManager;
import utilities.ControlPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	// private GameState gameController;
	// private MainMenuState mainMenuController;

	StateManager stateManager;
	private ArrayList<Integer> keys;
	// dimensions
	public static int WIDTH = ControlPanel.PANEL_WIDTH;
	public static int HEIGHT = ControlPanel.PANEL_HEIGHT;
	public static final int SCALE = 2;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private static Graphics2D g;

	public static Graphics2D getG() {
		return g;
	}

	// game state manager
	// private GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void init() throws IOException {
		try {
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
			// gameController = GameState.getInstance();
			// mainMenuController = MainMenuState.getInstance();

			stateManager = new StateManager();
			running = true;
			keys = new ArrayList<>();

			// gsm = new GameStateManager();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			init();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		long start;
		long elapsed;
		long wait;

		// game loop
		while (running) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {
		// gsm.update();
		// gameController.update();
		// mainMenuController.update();
		stateManager.update();
	}

	private void draw() {
		// gameController.draw();
		// mainMenuController.draw();
		stateManager.draw();
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key) {
	}

	public void keyPressed(KeyEvent key) {
		if (!keys.contains(new Integer(key.getKeyCode()))) {
			keys.add(new Integer(key.getKeyCode()));
		}
		// gameController.keyPressed(keys);
		// mainMenuController.keyPressed(keys);
		stateManager.keyPressed(keys);
	}

	public void keyReleased(KeyEvent key) {
		if (keys.contains(new Integer(key.getKeyCode()))) {
			keys.remove(new Integer(key.getKeyCode()));
		}
		stateManager.keyPressed(keys);
		// gameController.keyPressed(keys);
		// mainMenuController.keyPressed(keys);
	}

}
