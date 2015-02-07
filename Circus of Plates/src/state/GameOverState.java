package state;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.GameOverMenu;
import utilities.LinkHolder;
import utilities.ControlPanel;

public class GameOverState implements State {
	// private static GameOverState instance;

	private GameOverMenu gameOverMenu;
	private Image winnerImage;

	StateManager stateManager;

	public GameOverState(StateManager stateManager) {
		gameOverMenu = GameOverMenu.getInstane();
		this.stateManager = stateManager;
	}

	// public static GameOverState getInstance() {
	// if (instance == null) {
	// instance = new GameOverState();
	// }
	// return instance;
	// }

	// public void update(ArrayList<PlayerEntity> players) {
	// gameOverMenu.setScore1(players.get(0).getScore().getScore() + "");
	// gameOverMenu.setScore2(players.get(1).getScore().getScore() + "");
	// }

	@Override
	public void draw() {
		ControlPanel.g.drawImage(gameOverMenu.getMenuImage(),
				ControlPanel.WIN_MENU_X, ControlPanel.WIN_MENU_Y, null);
		int score1 = Integer.parseInt(gameOverMenu.getScore1());
		int score2 = Integer.parseInt(gameOverMenu.getScore2());
		
		if (score1 == score2) {
			winnerImage = LinkHolder.GAME_OVER_DRAW;
		} else if (score1 > score2) {
			winnerImage = LinkHolder.GAME_OVER_PLAYER1;
		} else {
			winnerImage = LinkHolder.GAME_OVER_PLAYER2;
		}

		ControlPanel.g.drawImage(winnerImage.getScaledInstance(
				ControlPanel.WIN_MENU_PLAYER_WIDTH,
				ControlPanel.WIN_MENU_PLAYER_HEIGHT, Image.SCALE_SMOOTH),
				ControlPanel.WIN_MENU_PLAYER_X, ControlPanel.WIN_MENU_PLAYER_Y,
				null);
		
		ControlPanel.g.setColor(Color.WHITE);
		
		ControlPanel.g.drawString(gameOverMenu.getScore1(),ControlPanel.WIN_MENU_SCORE_X, ControlPanel.WIN_MENU_SCORE1_Y);
		ControlPanel.g.drawString(gameOverMenu.getScore2(),ControlPanel.WIN_MENU_SCORE_X, ControlPanel.WIN_MENU_SCORE2_Y);
	}

	@Override
	public void update() {

	}

	private void select() {
		stateManager.setCurrentState(stateManager.getMainMenu());
	}

	@Override
	public void keyPressed(ArrayList<Integer> keys) {
		if (keys.contains(new Integer(KeyEvent.VK_ENTER))) {
			select();
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

}
