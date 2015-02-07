package model;

import java.awt.Image;

public abstract class Menu {
	private Background background;
	private Image menuImage;
	private int currentChoice;
	private int MaxChoices;

	public abstract void incrementCurrentChoice();

	public abstract void decrementCurrentChoice();

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public Image getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(Image menuImage) {
		this.menuImage = menuImage;
	}

	public int getCurrentChoice() {
		return currentChoice;
	}

	public void setCurrentChoice(int currentChoice) {
		this.currentChoice = currentChoice;
	}
	
	

	public int getMaxChoices() {
		return MaxChoices;
	}

	public void setMaxChoices(int maxChoices) {
		MaxChoices = maxChoices;
	}

	public void incrementChoice() {
		currentChoice++;
	}

	public void decrementChoice() {
		currentChoice--;
	}

}
