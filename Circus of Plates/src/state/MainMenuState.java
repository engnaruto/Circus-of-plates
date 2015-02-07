package state;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Game;
import model.MainMenu;
import model.SavedState;
import momento.Serialization;
import utilities.ControlPanel;
import control.BackgroundController;

public class MainMenuState implements State {
	private static BackgroundController backgroundController;
	private MainMenu mainMenu;
	private Serialization serialization;
	// private static MainMenuState instance;
	private StateManager stateManager;
	private Game game;

	public MainMenuState(StateManager stateManager) {
		backgroundController = BackgroundController.getInstance();
		mainMenu = MainMenu.getInstane();
		serialization = Serialization.getInstance();
		this.stateManager = stateManager;
		try {
			game = Game.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static MainMenuState getInstance() {
	// if (instance == null) {
	// instance = new MainMenuState();
	// }
	// return instance;
	// }

	public void update() {
		// System.out.println("control");
		backgroundController.update(mainMenu.getBackground());
	}

	public void draw() {
		backgroundController.draw(mainMenu.getBackground());
		ControlPanel.g.drawImage(mainMenu.getMenuImage(),
				ControlPanel.WIN_MENU_X, ControlPanel.WIN_MENU_Y, null);
		ControlPanel.g.setColor(Color.RED);
		ControlPanel.g.drawRect(ControlPanel.MAIN_MENU_SELECT_DX,
				ControlPanel.MAIN_MENU_SELECT_DY[mainMenu.getCurrentChoice()],
				ControlPanel.MAIN_MENU_SELECT_WIDTH,
				ControlPanel.MAIN_MENU_SELECT_HEIGHT);
	}

	private void select() {
		if (mainMenu.getCurrentChoice() == 0) {
			// System.out.println(game.countPlates());
			gameState();
		} else if (mainMenu.getCurrentChoice() == 1) {
			GameState.reset();
			SavedState savedState = serialization.load();
			game.load(savedState);
			gameState();
		} else if (mainMenu.getCurrentChoice() == 2) {
			System.exit(0);
		}
	}

	public void keyPressed(ArrayList<Integer> keys) {
		if (keys.contains(new Integer(KeyEvent.VK_ENTER))) {
			select();
		}

		if (keys.contains(new Integer(KeyEvent.VK_LEFT))) {
			mainMenu.incrementCurrentChoice();
		}

		if (keys.contains(new Integer(KeyEvent.VK_RIGHT))) {
			mainMenu.decrementCurrentChoice();
		}
		if (keys.contains(new Integer(KeyEvent.VK_DOWN))) {
			mainMenu.incrementCurrentChoice();
		}

		if (keys.contains(new Integer(KeyEvent.VK_UP))) {
			mainMenu.decrementCurrentChoice();
		}
	}

	@Override
	public void mainMenu() {
		System.out.println("MainMenu >>> MainMenu");
	}

	@Override
	public void gamePaused() {
		System.out.println("MainMenu >>> GamePaused");
	}

	@Override
	public void gameOver() {
		System.out.println("MainMenu >>> GameOver");
	}

	@Override
	public void gameState() {
		// System.out.println("GAME STATE!!!!");
		
		stateManager.setCurrentState(stateManager.getGameState());
	}

}
