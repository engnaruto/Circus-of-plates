package model;

import java.awt.Image;

import utilities.LinkHolder;
import utilities.ControlPanel;

public class MainMenu extends Menu {
	// private Background background;
	// private Image mainMenu;
	// private int currentChoice;
	private static MainMenu instance;
	private static int maxChoises = 3;

	private MainMenu() {
		// background = new Background(StaticHolder.MAIN_MENU_ANIMATION_DX,
		// StaticHolder.MAIN_MENU_ANIMATION_DY,
		// LinkHolder.MAIN_MENU_BACKGROUND.getScaledInstance(
		// StaticHolder.PANEL_WIDTH, StaticHolder.PANEL_HEIGHT,
		// Image.SCALE_SMOOTH),
		// LinkHolder.MAIN_MENU_ANIMATION.getScaledInstance(
		// StaticHolder.PANEL_WIDTH, StaticHolder.PANEL_HEIGHT,
		// Image.SCALE_SMOOTH));
		// mainMenu = LinkHolder.MAIN_MENU.getScaledInstance(
		// StaticHolder.WIN_MENU_WIDTH, StaticHolder.WIN_MENU_HEIGHT,
		// Image.SCALE_SMOOTH);

		setBackground(new Background(ControlPanel.MAIN_MENU_ANIMATION_DX,
				ControlPanel.MAIN_MENU_ANIMATION_DY,
				LinkHolder.MAIN_MENU_BACKGROUND.getScaledInstance(
						ControlPanel.PANEL_WIDTH, ControlPanel.PANEL_HEIGHT,
						Image.SCALE_SMOOTH),
				LinkHolder.MAIN_MENU_ANIMATION.getScaledInstance(
						ControlPanel.PANEL_WIDTH, ControlPanel.PANEL_HEIGHT,
						Image.SCALE_SMOOTH)));
		setMenuImage(LinkHolder.MAIN_MENU.getScaledInstance(
				ControlPanel.WIN_MENU_WIDTH, ControlPanel.WIN_MENU_HEIGHT,
				Image.SCALE_SMOOTH));
		setCurrentChoice(0);
		setMaxChoices(maxChoises);
	}

	public static MainMenu getInstane() {
		if (instance == null) {
			instance = new MainMenu();
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
