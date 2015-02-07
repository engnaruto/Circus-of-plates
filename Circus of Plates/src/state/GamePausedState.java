package state;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Game;
import model.GamePausedMenu;
import model.Timer;
import momento.Serialization;
import utilities.ControlPanel;

public class GamePausedState implements State {

	// private static GamePausedState instance;

	private GamePausedMenu gamePausedMenu;
	private Serialization serialization;
	private StateManager stateManager;
	Game game;

	public GamePausedState(StateManager stateManager) {
		gamePausedMenu = GamePausedMenu.getInstane();
		serialization = Serialization.getInstance();
		try {
			game = Game.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.stateManager = stateManager;
	}

	@Override
	public void draw() {
		ControlPanel.g.drawImage(gamePausedMenu.getMenuImage(),
				ControlPanel.WIN_MENU_X, ControlPanel.WIN_MENU_Y, null);
		ControlPanel.g.setColor(Color.RED);
		ControlPanel.g.drawRect(ControlPanel.MAIN_MENU_SELECT_DX,
				ControlPanel.MAIN_MENU_SELECT_DY[gamePausedMenu
						.getCurrentChoice()],
				ControlPanel.MAIN_MENU_SELECT_WIDTH,
				ControlPanel.MAIN_MENU_SELECT_HEIGHT);
	}

	@Override
	public void update() {
	}

	private void select() {
		Timer.resetTime();
		if (gamePausedMenu.getCurrentChoice() == 0) {
			
			serialization.save(game.save());
			game.loadPlayerPic();
			System.out.println("Saved");
			stateManager.setCurrentState(stateManager.getGameState());
		} else if (gamePausedMenu.getCurrentChoice() == 1) {
			game.reset();
			stateManager.setCurrentState(stateManager.getMainMenu());
		} else if (gamePausedMenu.getCurrentChoice() == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(ArrayList<Integer> keys) {
		if (keys.contains(new Integer(KeyEvent.VK_ESCAPE))) {
			stateManager.setCurrentState(stateManager.getGameState());
		} else {

			if (keys.contains(new Integer(KeyEvent.VK_ENTER))) {
				select();
			}

			if (keys.contains(new Integer(KeyEvent.VK_LEFT))) {
				gamePausedMenu.incrementCurrentChoice();
			}

			if (keys.contains(new Integer(KeyEvent.VK_RIGHT))) {
				gamePausedMenu.decrementCurrentChoice();
			}
			if (keys.contains(new Integer(KeyEvent.VK_DOWN))) {
				gamePausedMenu.incrementCurrentChoice();
			}

			if (keys.contains(new Integer(KeyEvent.VK_UP))) {
				gamePausedMenu.decrementCurrentChoice();
			}

		}
	}

	@Override
	public void mainMenu() {
		GameState.reset();
		stateManager.setCurrentState(stateManager.getMainMenu());
	}

	@Override
	public void gamePaused() {
		System.out.println("GamePaused >>> GamePaused");
	}

	@Override
	public void gameOver() {
		System.out.println("GamePaused >>> GameOver");
	}

	@Override
	public void gameState() {
		System.out.println("GamePaused >>> GameState");
	}

	// public static GamePausedState getInstance() {
	// if (instance == null) {
	// instance = new GamePausedState();
	// }
	// return instance;
	// }

}
