package model;

import java.awt.Image;

import utilities.LinkHolder;
import utilities.ControlPanel;

public class GamePausedMenu extends Menu {
	private static int maxChoises = 3;

	private static GamePausedMenu instance;

	private GamePausedMenu() {
		setBackground(null);
		setMenuImage(LinkHolder.GAME_PAUSED.getScaledInstance(
				ControlPanel.WIN_MENU_WIDTH, ControlPanel.WIN_MENU_HEIGHT,
				Image.SCALE_SMOOTH));
		setCurrentChoice(0);
		setMaxChoices(maxChoises);
	}

	public static GamePausedMenu getInstane() {
		if (instance == null) {
			instance = new GamePausedMenu();
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
}
