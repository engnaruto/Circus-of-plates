package model;

import java.awt.Image;

import utilities.LinkHolder;
import utilities.ControlPanel;

public class GameOverMenu extends Menu {

	private static int maxChoises = 3;

	private static GameOverMenu instance;
	
	private String player1, player2, score1, score2;

	private GameOverMenu() {
		setBackground(null);
		setMenuImage(LinkHolder.GAME_OVER.getScaledInstance(
				ControlPanel.WIN_MENU_WIDTH, ControlPanel.WIN_MENU_HEIGHT,
				Image.SCALE_SMOOTH));
		setCurrentChoice(0);
		setMaxChoices(maxChoises);
	}

	public static GameOverMenu getInstane() {
		if (instance == null) {
			instance = new GameOverMenu();
		}
		return instance;
	}

	@Override
	public void incrementCurrentChoice() {
		incrementChoice();
		if (getCurrentChoice() == getMaxChoices()) {
			setCurrentChoice(0);
		}
	}

	@Override
	public void decrementCurrentChoice() {
		decrementChoice();
		if (getCurrentChoice() == -1) {
			setCurrentChoice(getMaxChoices() - 1);
		}
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}
	
	

}
